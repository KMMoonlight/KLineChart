package com.kmmoonlight.chartdemo.chart.adapter;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import com.kmmoonlight.chartdemo.base.IAdapter;

public abstract class BaseKLineChartAdapter implements IAdapter {

    private final DataSetObservable mDataSetObservable = new DataSetObservable();

    @Override
    public void notifyDataSetChanged() {
        if (getCount() > 0) {
            mDataSetObservable.notifyChanged();
        } else {
            mDataSetObservable.notifyInvalidated();
        }
    }


    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        mDataSetObservable.registerObserver(observer);
    }

    @Override
    public void removeDataSetObserver(DataSetObserver observer) {
        mDataSetObservable.unregisterObserver(observer);
    }
}
