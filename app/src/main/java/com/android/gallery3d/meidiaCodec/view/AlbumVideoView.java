package com.android.gallery3d.meidiaCodec.view;

import android.content.Context;
import android.graphics.Rect;

import com.android.gallery3d.glrenderer.GLCanvas;
import com.android.gallery3d.meidiaCodec.Utils.Annotation;
import com.android.gallery3d.ui.GLView;

/**
 * Created by linusyang on 16-12-1.
 */

public class AlbumVideoView extends GLView implements VIPlayControl, OnNotifyChangeListener {

    private Context mContext;
    private Rect mWindowRect;

    public AlbumVideoView(Context context) {
        mContext = context;
    }

    @Override
    protected void onLayout(boolean changeSize, int left, int top, int right, int bottom) {
        super.onLayout(changeSize, left, top, right, bottom);
        mWindowRect = new Rect(left, top, right, bottom);
    }


    @Override
    protected void render(GLCanvas canvas) {
        super.render(canvas);
    }

    @Override
    @Annotation.IInterface("VIPlayControl")
    public void prepare() {

    }

    @Override
    @Annotation.IInterface("VIPlayControl")
    public void start() {

    }

    @Override
    @Annotation.IInterface("VIPlayControl")
    public void restart() {

    }

    @Override
    @Annotation.IInterface("VIPlayControl")
    public void pause() {

    }

    @Override
    @Annotation.IInterface("VIPlayControl")
    public void stop() {

    }

    @Override
    @Annotation.IInterface("VIPlayControl")
    public void seekTo(long durationT) {

    }

    @Override
    @Annotation.IInterface("VIPlayControl")
    public int getPlayState() {
        return 0;
    }

    @Override
    @Annotation.IInterface("VIPlayControl")
    public long getProgress() {
        return 0;
    }

    @Override
    @Annotation.IInterface("VIPlayControl")
    public long getDuration() {
        return 0;
    }

    @Override
    @Annotation.IInterface("OnNotifyChangeListener")
    public void doInvalidate() {
        invalidate();
    }
}
