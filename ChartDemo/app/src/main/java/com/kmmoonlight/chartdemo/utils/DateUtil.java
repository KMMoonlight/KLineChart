package com.kmmoonlight.chartdemo.utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateUtil {

    public static SimpleDateFormat longTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm" , Locale.getDefault());

    public static SimpleDateFormat shortTimeFormat = new SimpleDateFormat("HH:mm" , Locale.getDefault());

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd" , Locale.getDefault());

}
