package com.android.gallery3d.meidiaCodec.view;

import com.android.gallery3d.glrenderer.GLCanvas;

/**
 * Created by linusyang on 16-12-8.
 */

public abstract class PlayView {

    protected int mWidth;
    protected int mHeight;

    public void setResolution(int width, int height) {
        this.mWidth = width;
        this.mHeight = height;
    }

    public abstract void onDraw(GLCanvas canvas);



}
