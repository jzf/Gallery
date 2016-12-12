package com.android.gallery3d.mediaCore.Utils;

/**
 * Created by linus.yang on 2016/12/12.
 */

public class Utils {

    public static void  checkNull(Object obj , String str) {
        if(obj == null) throw new NullPointerException(str);
    }
}
