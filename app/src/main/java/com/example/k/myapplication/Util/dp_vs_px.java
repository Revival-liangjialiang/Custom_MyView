package com.example.k.myapplication.Util;

import android.content.Context;
import android.util.Log;

/**
 * Created by k on 2016/7/28.
 */
public class dp_vs_px {
    Context mContext;
    public static int dp2px(float dp) {
        //获得当前屏幕1dp等于多少px
        final float scale = MyApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
    public static int sp2px(float sp) {
        final float scale = MyApplication.getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scale + 0.5f);
    }
}
