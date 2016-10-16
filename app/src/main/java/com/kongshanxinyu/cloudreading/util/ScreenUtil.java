package com.kongshanxinyu.cloudreading.util;

import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by Steve on 16/10/10.
 * E-mail: singleframe@aliyun.com
 */
public class ScreenUtil {

    public static int getScreenWidth(){
        DisplayMetrics metrics=ContextUtil.getAppContext().getResources().getDisplayMetrics();
        int width=metrics.widthPixels;
        return width;
    }

    public static int getScreenHeight(){
        DisplayMetrics metrics=ContextUtil.getAppContext().getResources().getDisplayMetrics();
        int height=metrics.heightPixels;
        return height;
    }

    public static float dp2px(int dp){
        float px=TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,ContextUtil.getAppContext().getResources().getDisplayMetrics());
        return px;
    }

}
