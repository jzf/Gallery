package com.android.gallery3d.app;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.gallery3d.R;
import com.android.gallery3d.meidiaCodec.anim.ZoomBmStream;
import com.android.gallery3d.meidiaCodec.view.VideoView;
import com.android.gallery3d.ui.GLRootView;

/**
 * Created by linusyang on 16-12-1.
 */

public class VideoViewDome2 extends Activity implements VideoView.PlayStateListener , View.OnClickListener{

    private GLRootView mView;
    private VideoView mVideo;
    private Button btnPlay;
    private int bitmapIndex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_dome);
        mView = (GLRootView) findViewById(R.id.gl_root);
        btnPlay = (Button)findViewById(R.id.btn_play);
        mVideo = new VideoView(this);

        btnPlay.setOnClickListener(this);
        mView.setContentPane(mVideo);
        mVideo.setPlayStateListener(this);
    }

    private Bitmap getBitmap() {
        if(bitmapIndex == 5) {
            bitmapIndex = 1;
        }
        int bitmapId = getResources().getIdentifier("image"+bitmapIndex, "mipmap", getPackageName());
        BitmapFactory.Options mOptions = new BitmapFactory.Options();
        mOptions.inSampleSize = 5;
        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), bitmapId, mOptions);
        bitmapIndex++;
        return mBitmap;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play:
                handlerStartClick();
                break;
        }
    }

    private void handlerStartClick() {
        ZoomBmStream mZoomBmStream = new ZoomBmStream(getBitmap(), 0);
        mVideo.prepare(mZoomBmStream);
        mVideo.setDuration(3000);
        mVideo.start();
    }

    @Override
    public void onCompletion() {
        ZoomBmStream mZoomBmStream = new ZoomBmStream(getBitmap(), 0);
        mVideo.prepare(mZoomBmStream);
        mVideo.setDuration(3000);
        mVideo.start();
    }

}
