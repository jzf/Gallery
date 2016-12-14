package com.android.gallery3d.mediaCore.anim;

import com.android.gallery3d.glrenderer.GLCanvas;

/**
 * Created by linus.yang on 2016/12/14.
 */

public abstract class ComboStream extends MediaStream {

    private MediaStream mPreStream;
    private MediaStream mCurrentStream;

    public ComboStream(MediaStream mediaStream) {
        this.mCurrentStream = mediaStream;
    }

    @Override
    protected void onCalculate(float progress) {

    }
}
