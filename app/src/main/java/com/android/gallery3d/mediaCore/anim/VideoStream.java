package com.android.gallery3d.mediaCore.anim;

import android.app.Activity;
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

    public VideoStream(File videoPath, Activity mActivity) {
        if(!videoPath.exists()) throw new RuntimeException("video not exists ");
        mVideoScreenNail  = new VideoScreenNail(videoPath, mActivity);
        mVideoPath = videoPath;
    }


    @Override
    public void apply(GLCanvas canvas) {
        mVideoScreenNail.draw(canvas, 0, 0, mWidth, mHeight );
    }



    @Override
    protected void onCalculate(float progress) {

    }
}
