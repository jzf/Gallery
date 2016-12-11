package com.android.gallery3d.meidiaCodec.anim;


import com.android.gallery3d.glrenderer.GLCanvas;
import com.android.gallery3d.meidiaCodec.view.StateIs;
import com.android.gallery3d.meidiaCodec.view.VIPlayControl;

/**
 * Created by linusyang on 16-12-9.
 */

public abstract class MediaStream implements VIPlayControl , StateIs{

    protected static final long NO_ANIMATION = -2;
    protected long mStartTime = NO_ANIMATION;
    protected int  mPlayState = PLAY_STATE_STOP;

    protected long mDuration;
    protected long mCurrentDurationTime = 0;

    protected int mWidth;
    protected int mHeight;



    @Override
    public void start() {
        if(mPlayState == PLAY_STATE_STOP) {
            mStartTime = getCurrentTime();
            mPlayState = PLAY_STATE_START;
        } else if( mPlayState == PLAY_STATE_PAUSE ) {
            mStartTime = getCurrentTime() -  mCurrentDurationTime;
            mPlayState = PLAY_STATE_START;
            calculate(mStartTime);
        }
    }

    @Override
    public void prepare() {

    }

    @Override
    public void restart() {
        mStartTime = getCurrentTime();
        mPlayState = PLAY_STATE_START;
        calculate(mStartTime);
    }

    @Override
    public void pause() {
        if(mPlayState == PLAY_STATE_START) {
            mPlayState = PLAY_STATE_PAUSE;
        }
    }

    @Override
    public void seekTo(long durationT) {
        if(durationT > mDuration && durationT < 0) return;
        if(mPlayState == PLAY_STATE_START) {
            mStartTime = getCurrentTime() -  durationT;
        } else {
            mCurrentDurationTime = durationT;
            mStartTime = getCurrentTime() -  durationT;
            calculate(getCurrentTime());
        }

    }

    @Override
    public int getPlayState() {
        return mPlayState;
    }

    @Override
    public long getProgress() {
        return mCurrentDurationTime;
    }

    @Override
    public long getDuration() {
        return mDuration;
    }

    @Override
    public void setDuration(long duration) {
        this.mDuration = duration;
    }

    @Override
    public void stop() {
        if(mPlayState != PLAY_STATE_STOP) {
            mPlayState = PLAY_STATE_STOP;
            mStartTime = NO_ANIMATION;
            mCurrentDurationTime = 0;
        }
    }


    /**
     * @param canvas GLCanvas gives a convenient interface to draw using OpenGL.
     */
    public abstract void apply(GLCanvas canvas);

    public abstract boolean calculate(long currentTimeMillis);
    /**
     * @return the current time in milliseconds since January 1, 1970 00:00:00.0 UTC.
     */
    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    protected abstract void onCalculate(float progress);

}
