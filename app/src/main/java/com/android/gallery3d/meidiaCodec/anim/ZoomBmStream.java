package com.android.gallery3d.meidiaCodec.anim;

import android.graphics.Bitmap;
import android.graphics.PointF;

import com.android.gallery3d.glrenderer.GLCanvas;

import java.util.Random;

/**
 * Created by linusyang on 16-12-10.
 */

public class ZoomBmStream extends BitmapStream {

    private static final float SCALE_SPEED = 0.20f;
    private static final float MOVE_SPEED = SCALE_SPEED;
    protected float mProgress;
    private final PointF mMovingVector;
    private Random mRandom = new Random();

    public ZoomBmStream(Bitmap bitmap, int rotation) {
        super(bitmap, rotation);
        mMovingVector = new PointF(
                MOVE_SPEED * mWidth * (mRandom.nextFloat() - 0.5f),
                MOVE_SPEED * mHeight * (mRandom.nextFloat() - 0.5f));
    }

    @Override
    public void apply(GLCanvas canvas) {
        int viewWidth = mWidth;
        int viewHeight = mHeight;

        float initScale = Math.min((float)
                viewWidth / mWidth, (float) viewHeight / mHeight);
        float scale = initScale * (1 + SCALE_SPEED * mProgress);

        float centerX = viewWidth / 2 + mMovingVector.x * mProgress;
        float centerY = viewHeight / 2 + mMovingVector.y * mProgress;
        canvas.translate(centerX, centerY);
        canvas.scale(scale, scale, 0);
        canvas.rotate(mRotation, 0, 0, 1);
        mCurrentTexture.draw(canvas, -mCurrentTexture.getWidth() / 2,
                -mCurrentTexture.getHeight() / 2);
    }

    @Override
    protected void onCalculate(float progress) {
        mProgress = progress;
    }
}
