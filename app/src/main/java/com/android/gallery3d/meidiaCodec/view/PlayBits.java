package com.android.gallery3d.meidiaCodec.view;

import android.graphics.Bitmap;

import com.android.gallery3d.glrenderer.GLCanvas;

/**
 * Created by linusyang on 16-12-8.
 */

public class PlayBits implements VIPlayControl ,StateIs{




    public interface OnNotifyChangeListener {
        void doInvalidate();
    }

    protected int mWidth;
    protected int mHeight;
    protected long mAnimationDuration;
    protected long mChangeDuration;

    private OnNotifyChangeListener mOnNotifyChangeListener;


    public void setOnNotifyChangeListener(OnNotifyChangeListener listener) {
        this.mOnNotifyChangeListener = listener;
    }

    public void setResolution(int width, int height) {
        this.mWidth = width;
        this.mHeight = height;
    }

    public  void onDraw(GLCanvas canvas) {

    }


    /**
     * @hide
     */
    @Override
    public void prepare() {

    }

    @Override
    public void start() {

    }

    @Override
    public void restart() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void seekTo(long durationT) {

    }

    @Override
    public int getPlayState() {
        return 0;
    }

    @Override
    public long getProgress() {
        return 0;
    }

    @Override
    public void setDuration(long duration) {

    }

    @Override
    public long getDuration() {
        return 0;
    }

}
