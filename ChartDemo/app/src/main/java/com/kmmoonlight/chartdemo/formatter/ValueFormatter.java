package com.kmmoonlight.chartdemo.formatter;

import com.kmmoonlight.chartdemo.base.IValueFormatter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ValueFormatter implements IValueFormatter {
    @Override
    public String format(float value) {
        return new BigDecimal(value).setScale(2, RoundingMode.DOWN).stripTrailingZeros().toPlainString();
    }
}