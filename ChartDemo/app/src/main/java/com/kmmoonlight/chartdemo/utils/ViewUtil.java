package com.kmmoonlight.chartdemo.utils;

import android.content.Context;

public class ViewUtil {

    public static int Dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dp * scale + 0.5f);
    }


    public static int Px2dp(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(px / scale + 0.5f);
    }

}
