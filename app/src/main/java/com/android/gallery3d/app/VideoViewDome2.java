package com.android.gallery3d.app;

import android.app.Activity;
import android.os.Bundle;

import com.android.gallery3d.R;
import com.android.gallery3d.meidiaCodec.view.VideoView;
import com.android.gallery3d.ui.GLRootView;

/**
 * Created by linusyang on 16-12-1.
 */

public class VideoViewDome2 extends Activity{

    private GLRootView mView;
    private VideoView mVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_dome);
        mView = (GLRootView) findViewById(R.id.gl_root);
        mVideo = new VideoView(this);
        mView.setContentPane(mVideo);
    }
}
