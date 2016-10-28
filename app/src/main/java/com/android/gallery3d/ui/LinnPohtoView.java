package com.android.gallery3d.ui;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Rect;
import android.location.GpsStatus.NmeaListener;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.Checkable;

import com.android.gallery3d.app.AbstractGalleryActivity;
import com.android.gallery3d.data.DrawData;
import com.android.gallery3d.glrenderer.GLCanvas;
import com.android.gallery3d.ui.SlotView.Layout;

public class LinnPohtoView extends GLView {

	private int[] cursor = { 0, 0, 0 };
	private final Layout mLayout = new Layout();

	final int DRAW_FLAG_TREE_EQUILONG = 0;
	final int DRAW_FLAG_TREE_UNEQUALLENGTH_RIGHT = 1;
	final int DRAW_FLAG_TREE_UNEQUALLENGTH_LEFT = 2;
	final int DRAW_FLAG_TWO_AEQUILATE = 3;
	final int DRAW_FLAG_TWO_EQUILONG = 4;
	final int DRAW_FLAG_TWO_NO_AEQUILATE_RIGHT = 5;
	final int DRAW_FLAG_TWO_NO_AEQUILATE_LEFT = 6;
	final int DRAW_FLAG_ONE = 7;

	private GestureDetector mGestureDetector;
	private ScrollerHelper mScroller;
	private boolean mDownInScrolling;
	private UserInteractionListener mUIListener;
	private final Paper mPaper = new Paper();

	public LinnPohtoView(AbstractGalleryActivity activity) {
		mGestureDetector = new GestureDetector(activity,
				new MyGestureListener());
		mScroller = new ScrollerHelper(activity);
		mLayout.setCount();
	}

	@Override
	protected void render(GLCanvas canvas) {
		super.render(canvas);
		long animTime = AnimationTime.get();
		boolean more = mScroller.advanceAnimation(animTime);
		// more |= mLayout.advanceAnimation(animTime);
		int oldX = mScrollX;
		updateScrollPosition(mScroller.getPosition(), false);
		boolean paperActive = false;
		canvas.translate(-mScrollX, -mScrollY);
		cursor[0] = 0;
		cursor[1] = 0;
		cursor[2] = 0;
		for (int i = 0; i < mLayout.mCount; i++) {
			canvas.save(GLCanvas.SAVE_FLAG_ALPHA | GLCanvas.SAVE_FLAG_MATRIX);
			Rect mRect = mLayout.getItemRect(i);
			int mode = mLayout.getData().get(i).drawMode;
			canvas.translate(mRect.left, mRect.top);
			mateMode(canvas, mode);
			canvas.restore();
		}
		canvas.translate(mScrollX, mScrollY);
		if (more) {
			invalidate();
		}
	}

	public void mateMode(GLCanvas canvas, int mode) {
		switch (mode) {
		case DRAW_FLAG_TREE_EQUILONG:
			for (int i = 0; i < 3; i++) {
				drawSymmetryThree(canvas, cursor);
			}
			break;

		case DRAW_FLAG_TREE_UNEQUALLENGTH_RIGHT:
			for (int i = 0; i < 3; i++) {
				drawAnomalyRightThree(canvas, cursor);
			}
			break;
		case DRAW_FLAG_TREE_UNEQUALLENGTH_LEFT:
			for (int i = 0; i < 3; i++) {
				drawAnomalyLeftThree(canvas, cursor);
			}
			break;
		case DRAW_FLAG_TWO_AEQUILATE:
		case DRAW_FLAG_TWO_EQUILONG:
			for (int i = 0; i < 2; i++) {
				drawSymmetryDouble(canvas, cursor);
			}
			break;
		case DRAW_FLAG_TWO_NO_AEQUILATE_RIGHT:
			for (int i = 0; i < 2; i++) {
				drawAnomalyRightDouble(canvas, cursor);
			}
			break;
		case  DRAW_FLAG_TWO_NO_AEQUILATE_LEFT:
			for (int i = 0; i < 2; i++) {
				drawAnomalyLeftDouble(canvas, cursor);
			}
			break;
		case DRAW_FLAG_ONE:
			for (int i = 0; i < 2; i++) {
				drawSymmetrOne(canvas, cursor);
			}
			break;
		}
	}

	// DRAW_FLAG_TREE_EQUILONG
	public void drawSymmetryThree(GLCanvas canvas, int[] cursor) {
		int index = chekout();
		canvas.fillRect((mLayout.rowWidth + 5) * index, 0, mLayout.rowWidth,
				mLayout.rowHeight, getRandomColor());
		cursor[index] = cursor[index] + mLayout.rowHeight;
	}

	// DRAW_FLAG_TREE_UNEQUALLENGTH_RIGHT
	public void drawAnomalyRightThree(GLCanvas canvas, int[] cursor) {
		int index = chekout();
		if (index == 0) {
			canvas.fillRect(0, 0, mLayout.doubleWidth, mLayout.rowHeight,
					getRandomColor());
			cursor[index] = cursor[index] + mLayout.rowHeight;
		} else {
			canvas.fillRect(mLayout.doubleWidth + 5,
					(mLayout.rowHeight / 2 + 2) * (index - 1),
					mLayout.doubleWidth, (mLayout.rowHeight - 2) / 2,
					getRandomColor());
			cursor[index] = cursor[index] + mLayout.rowHeight;
		}

	}

	// DRAW_FLAG_TREE_UNEQUALLENGTH_LEFT
	public void drawAnomalyLeftThree(GLCanvas canvas, int[] cursor) {
		int index = chekout();
		if (index == 0 || index == 1) {
			canvas.fillRect(0, (mLayout.rowHeight / 2 + 2) * index,
					mLayout.doubleWidth, (mLayout.rowHeight - 2) / 2,
					getRandomColor());
			cursor[index] = cursor[index] + mLayout.rowHeight;
		} else {
			canvas.fillRect(mLayout.doubleWidth + 5, 0, mLayout.doubleWidth,
					mLayout.rowHeight, getRandomColor());
			cursor[index] = cursor[index] + mLayout.rowHeight;
		}
	}

	// DRAW_FLAG_TWO_AEQUILATE DRAW_FLAG_TWO_EQUILONG
	public void drawSymmetryDouble(GLCanvas canvas, int[] cursor) {
		int index = chekout();
		canvas.fillRect((mLayout.doubleWidth + 5) * index, 0,
				mLayout.doubleWidth, mLayout.rowHeight, getRandomColor());
		cursor[index] = cursor[index] + mLayout.rowHeight;
		if (index == 1) {
			cursor[index + 1] = cursor[index + 1] + mLayout.rowHeight;
		}
	}

	// DRAW_FLAG_TWO_NO_AEQUILATE_RIGHT
	public void drawAnomalyRightDouble(GLCanvas canvas, int[] cursor) {
		int index = chekout();
		if (index == 0) {
			canvas.fillRect((mLayout.rowWidth + 5) * index, 0,
					mLayout.rowWidth * 2 - 5, mLayout.rowHeight,
					getRandomColor());
			cursor[index] = cursor[index] + mLayout.rowHeight;
			cursor[index + 1] = cursor[index + 1] + mLayout.rowHeight;
		} else if (index == 2) {
			canvas.fillRect(mLayout.rowWidth * index, 0, mLayout.rowWidth,
					mLayout.rowHeight, getRandomColor());
			cursor[index] = cursor[index] + mLayout.rowHeight;
		}
	}

	// DRAW_FLAG_TWO_NO_AEQUILATE_LEFT
	public void drawAnomalyLeftDouble(GLCanvas canvas, int[] cursor) {
		int index = chekout();
		if (index == 0) {
			canvas.fillRect(mLayout.rowWidth * index, 0, mLayout.rowWidth,
					mLayout.rowHeight, getRandomColor());
			cursor[index] = cursor[index] + mLayout.rowHeight;
		} else {
			canvas.fillRect(mLayout.rowWidth * index, 0, mLayout.rowWidth * 2,
					mLayout.rowHeight, getRandomColor());
			cursor[index] = cursor[index] + mLayout.rowHeight;
			cursor[index + 1] = cursor[index + 1] + mLayout.rowHeight;
		}
	}
  //DRAW_FLAG_ONE
	public void drawSymmetrOne(GLCanvas canvas, int[] cursor) {
		int index = chekout();
		if (index == 0) {
			canvas.fillRect(0, 0, mLayout.width, mLayout.rowHeight,
					getRandomColor());
			cursor[index] = cursor[index] + mLayout.rowHeight;
			cursor[index + 1] = cursor[index + 1] + mLayout.rowHeight;
			cursor[index + 2] = cursor[index + 2] + mLayout.rowHeight;
		}
	}

	public int getRandomColor() {
		Random random = new Random();
		return Color.rgb(random.nextInt(256), random.nextInt(256),
				random.nextInt(256));
	}

	public int chekout() {
		if (cursor[0] == cursor[1] && cursor[1] == cursor[2]) {
			return 0;
		}
		if (cursor[0] != cursor[1]) {
			return 1;
		}
		return 2;
	}

	private void updateScrollPosition(int position, boolean force) {
		if (!force && (position == mScrollY))
			return;
		mScrollY = position;
		mLayout.setScrollPosition(position);
		onScrollPositionChanged(position);
	}

	protected void onScrollPositionChanged(int newPosition) {
		int limit = mLayout.getScrollLimit();
		// mListener.onScrollPositionChanged(newPosition, limit);
	}

	@Override
	protected boolean onTouch(MotionEvent event) {
		if (mUIListener != null)
			mUIListener.onUserInteraction();
		mGestureDetector.onTouchEvent(event);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mDownInScrolling = !mScroller.isFinished();
			mScroller.forceFinished();
			break;
		case MotionEvent.ACTION_UP:
			mPaper.onRelease();
			invalidate();
			break;
		}
		return true;
	}

	private class MyGestureListener implements OnGestureListener {

		private boolean isDown;

		// We call the listener's onDown() when our onShowPress() is called and
		// call the listener's onUp() when we receive any further event.
		@Override
		public void onShowPress(MotionEvent e) {
			// GLRoot root = getGLRoot();
			// root.lockRenderThread();
			// try {
			// if (isDown) return;
			// int index = mLayout.getSlotIndexByPosition(e.getX(), e.getY());
			// if (index != INDEX_NONE) {
			// isDown = true;
			// mListener.onDown(index);

			// }
			// } finally {
			// root.unlockRenderThread();
			// }
		}

		private void cancelDown(boolean byLongPress) {
			if (!isDown)
				return;
			isDown = false;
			// mListener.onUp(byLongPress);
		}

		@Override
		public boolean onDown(MotionEvent e) {
			return false;
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			cancelDown(false);
			int scrollLimit = mLayout.getScrollLimit();
			if (scrollLimit == 0)
				return false;
			float velocity = velocityY;
			mScroller.fling((int) -velocity, 0, scrollLimit);
			if (mUIListener != null)
				mUIListener.onUserInteractionBegin();
			invalidate();
			return true;
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			cancelDown(false);
			float distance = distanceY;
			int overDistance = mScroller.startScroll(Math.round(distance), 0,
					mLayout.getScrollLimit());
			invalidate();
			return true;
		}

		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			// cancelDown(false);
			// if (mDownInScrolling) return true;
			// int index = mLayout.getSlotIndexByPosition(e.getX(), e.getY());
			// if (index != INDEX_NONE) mListener.onSingleTapUp(index);
			return true;
		}

		@Override
		public void onLongPress(MotionEvent e) {
			// cancelDown(true);
			// if (mDownInScrolling) return;
			// lockRendering();
			// try {
			// int index = mLayout.getSlotIndexByPosition(e.getX(), e.getY());
			// if (index != INDEX_NONE) mListener.onLongTap(index);
			// } finally {
			// unlockRendering();
			// }
		}

	}

	public class Layout {
		public int width = 1080;
		public int height = 1920;
		public int slotGap = 5;
		public int doubleWidth = width / 2;
		public int rowWidth = width / 3;
		public int rowHeight = height / 4;
		public int mScrollPosition;

		ArrayList<DrawData> mDatas = new ArrayList<DrawData>();

		public int mCount;

		private ArrayList<Rect> mRects = new ArrayList<Rect>();

		public void setScrollPosition(int position) {
			if (mScrollPosition == position)
				return;
			mScrollPosition = position;
		}

		public void setCount() {
			mDatas.add(new DrawData(DRAW_FLAG_TREE_EQUILONG, 3));
			mDatas.add(new DrawData(DRAW_FLAG_TREE_UNEQUALLENGTH_RIGHT, 3));
			mDatas.add(new DrawData(DRAW_FLAG_TREE_UNEQUALLENGTH_LEFT, 3));
			mDatas.add(new DrawData(DRAW_FLAG_TWO_AEQUILATE, 2));
			mDatas.add(new DrawData(DRAW_FLAG_TWO_EQUILONG, 2));
			mDatas.add(new DrawData(DRAW_FLAG_TWO_NO_AEQUILATE_RIGHT, 2));
			mDatas.add(new DrawData(DRAW_FLAG_TWO_NO_AEQUILATE_LEFT, 2));
			mDatas.add(new DrawData(DRAW_FLAG_ONE, 1));
			mCount = mDatas.size();
		}

		public ArrayList<DrawData> getData() {
			return mDatas;
		}

		public Rect getItemRect(int position) {
			if (mRects.size() <= position) {
				measure();
			}
			return mRects.get(position);
		}

		public void measure() {
			if (mRects.size() > 0) {
				mRects.clear();
			}
			int totalHeight = 0;
			for (int i = 0; i < mCount; i++) {
				Rect rect = new Rect(0, totalHeight, width, rowHeight);
				mRects.add(rect);
				totalHeight += rowHeight + 5;
			}
		}

		public int getScrollLimit() {
			// int limit = WIDE ? mContentLength - mWidth : mContentLength -
			// mHeight;
			// return limit <= 0 ? 0 : limit;
			return 200000;
		}
	}
}
