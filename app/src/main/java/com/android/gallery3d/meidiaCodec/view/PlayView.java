package com.android.gallery3d.meidiaCodec.view;

import com.android.gallery3d.glrenderer.GLCanvas;

/**
 * Created by linusyang on 16-12-8.
 */

public abstract class PlayView {

    public interface OnNotifyChangeListener {
        void doInvalidate();
    }

    protected int mWidth;
    protected int mHeight;

    private OnNotifyChangeListener mOnNotifyChangeListener;

    public void setOnNotifyChangeListener(OnNotifyChangeListener listener) {
        this.mOnNotifyChangeListener = listener;
    }

    public void setResolution(int width, int height) {
        this.mWidth = width;
        this.mHeight = height;
    }

    public abstract void onDraw(GLCanvas canvas);



}
