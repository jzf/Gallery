package com.android.gallery3d.mediaCore.anim;

import com.android.gallery3d.glrenderer.GLCanvas;

/**
 * Created by linusyang on 16-12-9.
 */

public class VideoStream extends MediaStream {

    @Override
    public void prepare() {

    }

    @Override
    public void start() {

    }

    @Override
    public void restart() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void seekTo(long durationT) {

    }

    @Override
    public int getPlayState() {
        return 0;
    }

    @Override
    public long getProgress() {
        return 0;
    }

    @Override
    public long getDuration() {
        return 0;
    }

    @Override
    public void apply(GLCanvas canvas) {

    }

    @Override
    public boolean calculate(long currentTimeMillis) {
        return false;
    }

    @Override
    protected void onCalculate(float progress) {

    }
}
