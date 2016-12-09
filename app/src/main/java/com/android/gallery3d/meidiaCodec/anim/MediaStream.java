package com.android.gallery3d.meidiaCodec.anim;


import com.android.gallery3d.glrenderer.GLCanvas;
import com.android.gallery3d.meidiaCodec.view.VIPlayControl;

/**
 * Created by linusyang on 16-12-9.
 */

public abstract class MediaStream implements VIPlayControl {


    protected long mStartTime;

    /**
     * @param canvas GLCanvas gives a convenient interface to draw using OpenGL.
     */
    public abstract void apply(GLCanvas canvas);

    public abstract boolean calculate(long currentTimeMillis);
    /**
     * @return the current time in milliseconds since January 1, 1970 00:00:00.0 UTC.
     */
    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

}
