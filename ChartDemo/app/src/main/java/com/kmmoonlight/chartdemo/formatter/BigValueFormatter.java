package com.kmmoonlight.chartdemo.formatter;

import com.kmmoonlight.chartdemo.base.IValueFormatter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigValueFormatter implements IValueFormatter {

    private int[] values = {10000, 1000000, 100000000};
    private String[] units = {"万", "百万", "亿"};

    @Override
    public String format(float value) {

        String unit = "";

        int i = values.length - 1;
        while (i >= 0) {
            if (value > values[i]) {
                value /= values[i];
                unit = units[i];
                break;
            }
            i--;
        }
        return (new BigDecimal(value + "").setScale(2, RoundingMode.DOWN).toPlainString()) + unit;
    }

}
