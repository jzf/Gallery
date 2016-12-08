package com.android.gallery3d.meidiaCodec.entiry;

import android.graphics.Bitmap;

/**
 * Created by linusyang on 16-12-1.
 */
public class PlayItemInfo {

    //DataType
    public static final int DATA_TYPE_VIDEO = 1 << 0;
    public static final int DATA_TYPE_IMGE = 1 << 1;

    //animationType
    public static final int ANIMATION_TYPE_ZOOM_INOUT = 2 << 0;

    //changeType
    //TODO
    //public static final int


    /**data Type The use{@link #DATA_TYPE_IMGE , {@link #DATA_TYPE_VIDEO}}*/
    private int dataType;
    /**Data storage path*/
    private String dataPath;
    /**Cache bitmap*/
    private Bitmap mBitmap;
    /**animation*/
    private int animationType;
    private int changeAmType;
    private long animationDuration;
    private long changeAmDuration;
    private long momentTime;


    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getDataPath() {
        return dataPath;
    }

    public void setDataPath(String dataPath) {
        this.dataPath = dataPath;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
    }

    public long getAnimationDuration() {
        return animationDuration;
    }

    public void setAnimationDuration(long animationDuration) {
        this.animationDuration = animationDuration;
    }

    public long getChangeAmDuration() {
        return changeAmDuration;
    }

    public void setChangeAmDuration(long changeDuration) {
        this.changeAmDuration = changeDuration;
    }

    public long getMomentTime() {
        return momentTime;
    }

    public void setMomentTime(long momentTime) {
        this.momentTime = momentTime;
    }

    @Override
    public String toString() {
        return "PlayItemInfo{" +
                "dataType=" + dataType +
                ", dataPath='" + dataPath + '\'' +
                ", mBitmap=" + mBitmap +
                ", animationDuration=" + animationDuration +
                ", changeDuration=" + changeAmDuration +
                ", momentTime=" + momentTime +
                '}';
    }
}
