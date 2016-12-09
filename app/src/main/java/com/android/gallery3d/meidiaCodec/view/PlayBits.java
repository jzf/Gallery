package com.android.gallery3d.meidiaCodec.view;

import android.graphics.Bitmap;

import com.android.gallery3d.glrenderer.GLCanvas;

/**
 * Created by linusyang on 16-12-8.
 */

public abstract class PlayBits implements VIPlayControl ,StateIs{


    private Bitmap mPreBitmap;

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

    public void setAnimationDuration(long animationDuration) {
        this.mAnimationDuration = animationDuration;
    }

    public void setChangeDuration(long changeDuration) {
        this.mChangeDuration = changeDuration;
    }

    public abstract void onDraw(GLCanvas canvas);


    public void setPreBitmap(Bitmap preBitmap) {
        this.mPreBitmap = preBitmap;
    }

    public Bitmap getPreBitmap() {
        return mPreBitmap;
    }

}
