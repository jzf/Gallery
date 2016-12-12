package com.android.gallery3d.meidiaCodec.anim;

import android.graphics.Bitmap;
import android.view.animation.Interpolator;

import com.android.gallery3d.common.Utils;
import com.android.gallery3d.glrenderer.BitmapTexture;
import com.android.gallery3d.glrenderer.GLCanvas;

/**
 * Created by linusyang on 16-12-9.
 */

public abstract class BitmapStream  extends MediaStream {

    protected Interpolator mInterpolator;
    protected BitmapTexture mCurrentTexture;
    protected int mRotation;

    public BitmapStream(Bitmap bitmap , int rotation) {
        if(bitmap == null) throw new NullPointerException("bitmap == null");
        mRotation = rotation;
         mCurrentTexture = new BitmapTexture(bitmap);
        if (((rotation / 90) & 0x01) == 0) {
            mWidth = bitmap.getWidth();
            mHeight = bitmap.getHeight();
        } else {
            mWidth = bitmap.getHeight();
            mHeight = bitmap.getWidth();
        }
    }

    @Override
    public boolean calculate(long currentTimeMillis) {
        if(mPlayState == PLAY_STATE_STOP || mPlayState == PLAY_STATE_PAUSE) return false;
        int elapse = (int) (currentTimeMillis - mStartTime);
        mCurrentDurationTime = elapse > mDuration ? mDuration : elapse;
        float x = Utils.clamp((float) elapse / mDuration, 0f, 1f);
        Interpolator i = mInterpolator;
        onCalculate(i != null ? i.getInterpolation(x) : x);
        return true;
    }
}
