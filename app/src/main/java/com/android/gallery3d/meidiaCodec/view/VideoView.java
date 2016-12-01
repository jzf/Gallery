package com.android.gallery3d.meidiaCodec.view;

import android.content.Context;

import com.android.gallery3d.glrenderer.GLCanvas;
import com.android.gallery3d.ui.GLView;

/**
 * Created by linusyang on 16-12-1.
 */

public class VideoView extends GLView {

    private Context mContext;

    public VideoView(Context context) {
        mContext = context;
    }

    @Override
    protected void onLayout(boolean changeSize, int left, int top, int right, int bottom) {
        super.onLayout(changeSize, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
    }

    @Override
    protected void render(GLCanvas canvas) {
        super.render(canvas);
    }
}
