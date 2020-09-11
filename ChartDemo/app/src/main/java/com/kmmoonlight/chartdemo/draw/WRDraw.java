package com.kmmoonlight.chartdemo.draw;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.kmmoonlight.chartdemo.base.IChartDraw;
import com.kmmoonlight.chartdemo.base.IValueFormatter;
import com.kmmoonlight.chartdemo.chart.BaseKLineChartView;
import com.kmmoonlight.chartdemo.formatter.ValueFormatter;
import com.kmmoonlight.chartdemo.repo.IWR;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class WRDraw implements IChartDraw<IWR> {

    private Paint mRPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public WRDraw(BaseKLineChartView view) {
    }

    @Override
    public void drawTranslated(@Nullable IWR lastPoint, @NonNull IWR curPoint, float lastX, float curX, @NonNull Canvas canvas, @NonNull BaseKLineChartView view, int position) {
        if (lastPoint.getR() != -10) {
            view.drawChildLine(canvas, mRPaint, lastX, lastPoint.getR(), curX, curPoint.getR());
        }
    }

    @Override
    public void drawText(@NonNull Canvas canvas, @NonNull BaseKLineChartView view, int position, float x, float y) {
        IWR point = (IWR) view.getItem(position);
        if (point.getR() != -10) {
            String text = "WR(14):";
            canvas.drawText(text, x, y, view.getTextPaint());
            x += view.getTextPaint().measureText(text);
            text = view.formatValue(point.getR()) + " ";
            canvas.drawText(text, x, y, mRPaint);
        }
    }

    @Override
    public float getMaxValue(IWR point) {
        return point.getR();
    }

    @Override
    public float getMinValue(IWR point) {
        return point.getR();
    }

    @Override
    public IValueFormatter getValueFormatter() {
        return new ValueFormatter();
    }

    /**
     * 设置%R颜色
     */
    public void setRColor(int color) {
        mRPaint.setColor(color);
    }

    /**
     * 设置曲线宽度
     */
    public void setLineWidth(float width) {
        mRPaint.setStrokeWidth(width);
    }

    /**
     * 设置文字大小
     */
    public void setTextSize(float textSize) {
        mRPaint.setTextSize(textSize);
    }
}