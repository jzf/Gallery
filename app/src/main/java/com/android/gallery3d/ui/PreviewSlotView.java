package com.android.gallery3d.ui;

import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.graphics.Color;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.android.gallery3d.app.ActivityState;
import com.android.gallery3d.glrenderer.GLCanvas;

/**
 * Created by caihuiyang on 2016/10/3.
 */

public class PreviewSlotView extends  GLView{

    private static final String TAG = "PreviewSlotView";

    private final Layout  mLayout = new Layout();


    public PreviewSlotView(ActivityState activity, Spec spec) {
        setSlotSpec(spec);
    }


    public void setSlotSpec(Spec spec) {
        mLayout.setSpec(spec);
    }

    @Override
    protected void render(GLCanvas canvas) {
        super.render(canvas);
      //  canvas.setSize(mLayout.getWidth(), 800);
      canvas.fillRect(0 , 0, mLayout.getWidth(), mLayout.getHeight(), Color.RED);
    }


    @Override
    protected void onLayout(boolean changeSize, int left, int top, int right, int bottom) {
        super.onLayout(changeSize, left, top, right, bottom);
        int width = right - left;
        int height = bottom - top;
        Log.d(TAG, "width:"+ width +"=====height:"+height);
        mLayout.setSize(width, height);
    }

    @Override
    protected boolean onTouch(MotionEvent event) {
        System.out.println(event.getX()+"==="+event.getY());
        return true;
    }

    public static class Spec {
        public int slotWidth = -1;
        public int slotHeight = -1;
        public int slotGap = -1;
        public int mUnitCount;
        public boolean isAutoMatch;

    }


    public class Layout{

        private int mWidth;
        private int mHeight;

        private int mSlotCount;

        private Spec mSpec;

        private int mSlotWidth;
        private int mSlotHeight;
        private int mSlotGap;
        private int mUnitCount;

        public void setSize(int width, int height) {
            mWidth = width;
            mHeight = height;
        }

        public int getWidth() {
            return mWidth;
        }

        public int getHeight() {
            return mHeight;
        }

        public int getSlotWidth() {
            return mSlotWidth;
        }

        public int getSlotHeight;

        public void setSpec(Spec spec) {
            mSpec = spec;
            initLayoutParameters();
        }

        public boolean setSlotCount(int slotCount) {
            if (slotCount == mSlotCount) return false;
            mSlotCount = slotCount;
            return true;
        }

        private void initLayoutParameters() {
            if(mSpec == null) return;
            mSlotGap = mSpec.slotGap;
            mSlotHeight = mSpec.slotHeight;
            mUnitCount = mSpec.mUnitCount;
            int autoMath = mWidth / mUnitCount  - mSlotGap * mUnitCount;
            mWidth = mSpec.isAutoMatch ? autoMath : mSpec.slotWidth;

            System.out.println(mSlotGap+"====="+mSlotHeight+"===="+mSlotWidth+"====="+mUnitCount);
        }


    }
}
