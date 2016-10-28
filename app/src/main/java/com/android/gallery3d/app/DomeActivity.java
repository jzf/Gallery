package com.android.gallery3d.app;


import com.android.gallery3d.glrenderer.GLCanvas;
import com.android.gallery3d.ui.GLView;
import com.android.gallery3d.ui.PreviewSlotView;

import android.os.Bundle;

public class DomeActivity  extends ActivityState{
	private GLView mLinnPohtoView;


	private final GLView mRootPane = new GLView() {

		@Override
		protected void onLayout(
				boolean changed, int left, int top, int right, int bottom) {

			int slotViewTop = mActivity.getGalleryActionBar().getHeight();
			int slotViewBottom = bottom - top;
			int slotViewRight = right - left;

			mLinnPohtoView.layout(left, top , right, bottom);
		}

		@Override
		protected void render(GLCanvas canvas) {
			super.render(canvas);
		}
	};

	@Override
	protected void onCreate(Bundle data, Bundle storedState) {
		super.onCreate(data, storedState);
		PreviewSlotView.Spec mSpec = new PreviewSlotView.Spec();
		mSpec.isAutoMatch = true;
		mSpec.slotGap = 10;
		mSpec.mUnitCount = 15;
		mSpec.slotHeight = 100;
		mLinnPohtoView = new PreviewSlotView(this, mSpec);
		mRootPane.addComponent(mLinnPohtoView);
		setContentPane(mRootPane);
	}
  
}
