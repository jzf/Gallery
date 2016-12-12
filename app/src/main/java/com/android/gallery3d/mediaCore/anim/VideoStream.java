package com.android.gallery3d.mediaCore.anim;

import android.view.animation.Interpolator;

import com.android.gallery3d.common.Utils;
import com.android.gallery3d.glrenderer.GLCanvas;
import com.android.gallery3d.mediaCore.Utils.VideoScreenNail;

import java.io.File;

/**
 * Created by linusyang on 16-12-9.
 */

public class VideoStream extends MediaStream {

    private VideoScreenNail mVideoScreenNail;
    private File mVideoPath;

    public VideoStream(File videoPath) {
        mVideoScreenNail  = new VideoScreenNail();
        if(!videoPath.exists()) throw new RuntimeException("video not exists ");
        mVideoPath = videoPath;
    }

    @Override
    public void start() {
        if(mPlayState == PLAY_STATE_STOP) {
            mStartTime = getCurrentTime();
            mPlayState = PLAY_STATE_START;
            mVideoScreenNail.play(mVideoPath);
        } else if( mPlayState == PLAY_STATE_PAUSE ) {
            mStartTime = getCurrentTime() -  mCurrentDurationTime;
            mPlayState = PLAY_STATE_START;
            calculate(mStartTime);
        }
    }

    @Override
    public void apply(GLCanvas canvas) {
        mVideoScreenNail.draw(canvas, 0, 0, mWidth, mHeight );
    }

    @Override
    public boolean calculate(long currentTimeMillis) {
        if(mPlayState == PLAY_STATE_STOP || mPlayState == PLAY_STATE_PAUSE) return false;
        int elapse = (int) (currentTimeMillis - mStartTime);
        mCurrentDurationTime = elapse > mDuration ? mDuration : elapse;
        float x = Utils.clamp((float) elapse / mDuration, 0f, 1f);
        onCalculate( x);
        return false;
    }

    @Override
    protected void onCalculate(float progress) {

    }
}
