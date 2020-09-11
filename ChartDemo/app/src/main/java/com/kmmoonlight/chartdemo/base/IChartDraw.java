package com.kmmoonlight.chartdemo.base;


import android.graphics.Canvas;
import com.kmmoonlight.chartdemo.chart.BaseKLineChartView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

//根据传入的数据实体类型 来绘制不同的图
public interface IChartDraw<T> {

    void drawTranslated(@Nullable T lastPoint, @NonNull T curPoint, float lastX, float curX,@NonNull Canvas canvas,@NonNull BaseKLineChartView view, int position);

    void drawText(@NonNull Canvas canvas, @NonNull BaseKLineChartView view, int position, float x, float y);

    float getMaxValue(T point);

    float getMinValue(T point);

    IValueFormatter getValueFormatter();
}
