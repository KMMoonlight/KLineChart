package com.kmmoonlight.chartdemo.formatter;

import com.kmmoonlight.chartdemo.base.IDateTimeFormatter;
import com.kmmoonlight.chartdemo.utils.DateUtil;

import java.util.Date;

public class DateFormatter implements IDateTimeFormatter {

    @Override
    public String format(Date date) {

        if (date == null) {
            return "";
        }

        return DateUtil.dateFormat.format(date);
    }


}
