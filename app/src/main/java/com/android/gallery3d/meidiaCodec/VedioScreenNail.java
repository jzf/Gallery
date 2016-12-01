package com.android.gallery3d.meidiaCodec;

import android.graphics.Color;
import android.graphics.SurfaceTexture;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;

import com.android.gallery3d.app.VideoDomeActivity;
import com.android.gallery3d.glrenderer.GLCanvas;
import com.android.gallery3d.glrenderer.GLPaint;
import com.android.gallery3d.ui.GLRoot;
import com.android.gallery3d.ui.SurfaceTextureScreenNail;

import java.io.File;
import java.io.IOException;

/**
 * Created by bruce.jiang on 2016/11/30.
 */

public class VedioScreenNail extends SurfaceTextureScreenNail implements MoviePlayer.PlayerFeedback {
    private static final String TAG = "VedioScreenNail";

    private MoviePlayer.PlayTask mPlayTask;
    private VideoDomeActivity mActivity;
    public VedioScreenNail(VideoDomeActivity activity) {
        this.mActivity = activity;
    }

    public void draw(GLCanvas canvas, int width, int height, int rotation, float progress) {

    }

    @Override
    public void noDraw() {

    }

    @Override
    public void recycle() {

    }

    @Override
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        invalidate();
    }

    protected void invalidate() {
        GLRoot root = mActivity.getGLRoot();
        if (root != null) root.requestRender();
    }
    @Override
    public void draw(GLCanvas canvas, int x, int y, int width, int height) {
        super.draw(canvas, x, y, width, height);
        GLPaint mPaint = new GLPaint();
        mPaint.setColor(Color.RED);
        canvas.drawRect(100,100, 600,600,mPaint);
        if (getSurfaceTexture() == null)
            super.acquireSurfaceTexture(canvas);

    }

    public void play(File file){
        if (mPlayTask != null) {
            Log.w(TAG, "movie already playing");
            return;
        }

        Log.d(TAG, "starting movie");
        SpeedControlCallback callback = new SpeedControlCallback();
        Surface surface = new Surface(getSurfaceTexture());

        // Don't leave the last frame of the previous video hanging on the screen.
        // Looks weird if the aspect ratio changes.
//        clearSurface(surface);

        MoviePlayer player = null;
        try {
            player = new MoviePlayer(
                    file, surface, callback);
        } catch (IOException ioe) {
            Log.e(TAG, "Unable to play movie", ioe);
            surface.release();
            return;
        }


        mPlayTask = new MoviePlayer.PlayTask(player, this);

        mPlayTask.execute();
    }

    @Override
    public void playbackStopped() {
        Log.i(TAG,"playback callback");
    }

    public void stopPlayback() {
        if (mPlayTask != null) {
            mPlayTask.requestStop();
        }
    }

}
