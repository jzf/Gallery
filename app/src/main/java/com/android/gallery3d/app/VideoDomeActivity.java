package com.android.gallery3d.app;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.android.gallery3d.R;
import com.android.gallery3d.meidiaCodec.VedioScreenNail;
import com.android.gallery3d.ui.GLRootView;
import com.android.gallery3d.ui.VideoView;

import java.io.File;

/**
 * Created by bruce.jiang on 2016/11/30.
 */

public class VideoDomeActivity extends Activity {


    private GLRootView mView;
    private VideoView mVideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_dome);
        mView = (GLRootView) findViewById(R.id.gl_root);
        mVideo = new VideoView();
        VedioScreenNail screenNail = new VedioScreenNail(this);
        mVideo.setmScreenNail(screenNail);
        mView.setContentPane(mVideo);


        findViewById(R.id.btn_play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/DCIM/Camera/move.mp4";
                File file = new File(path);
                mVideo.play(file);
            }
        });

        findViewById(R.id.btn_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideo.stop();
            }
        });

    }

    public GLRootView getGLRoot() {
        return mView;
    }


}
