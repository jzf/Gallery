package com.android.gallery3d.mediaCore.view;

/**
 * Created by linusyang on 16-12-8.
 */

public interface VIPlayControl {

    void prepare();
    void start();
    void restart();
    void pause();
    void stop();
    void seekTo(long durationT);
    int getPlayState();
    long getProgress();
    void setDuration(long duration);
    long getDuration();
}
