package com.android.gallery3d.mediaCore.anim;

import android.graphics.Bitmap;
import android.view.animation.Interpolator;

import com.android.gallery3d.common.Utils;
import com.android.gallery3d.glrenderer.BitmapTexture;

/**
 * Created by linusyang on 16-12-9.
 */

public abstract class BitmapStream  extends MediaStream {

    protected Interpolator mInterpolator;
    protected BitmapTexture mCurrentTexture;
    protected int mRotation;

    protected int bitmapWidth;
    protected int bitmapHeight;

    public BitmapStream(Bitmap bitmap , int rotation) {
        if(bitmap == null) throw new NullPointerException("bitmap == null");
        mRotation = rotation;
         mCurrentTexture = new BitmapTexture(bitmap);
        if (((rotation / 90) & 0x01) == 0) {
            bitmapWidth = bitmap.getWidth();
            bitmapHeight = bitmap.getHeight();
        } else {
            bitmapWidth = bitmap.getHeight();
            bitmapHeight = bitmap.getWidth();
        }
    }
}
