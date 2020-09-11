package com.kmmoonlight.chartdemo.chart;


import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.OverScroller;
import android.widget.RelativeLayout;

import androidx.core.view.GestureDetectorCompat;

//可以滚动和缩放的View
public abstract class ScrollAndScaleView extends RelativeLayout implements GestureDetector.OnGestureListener, ScaleGestureDetector.OnScaleGestureListener {

    protected int mScrollX = 0;

    protected GestureDetectorCompat mDetector;

    protected ScaleGestureDetector mScaleDetector;

    protected boolean isLongPress = false;

    private OverScroller mScroller;

    protected boolean mTouch = false;

    protected float mScaleX = 1;  //X轴缩放倍率

    protected float mScaleXMax = 2f; //X轴最大缩放倍率

    protected float mScaleXMin = 0.5f; //X轴最小缩放倍率

    private boolean mMultipleTouch = false;

    private boolean mScrollEnable = true; //允许滚动

    private boolean mScaleEnable = true; //允许缩放

    public ScrollAndScaleView(Context context) {
        super(context);
        init();
    }

    public ScrollAndScaleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScrollAndScaleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init(){
        setWillNotDraw(false);
        mDetector = new GestureDetectorCompat(getContext(), this);
        mScaleDetector = new ScaleGestureDetector(getContext(), this);
        mScroller = new OverScroller(getContext());
    }


    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }


    @Override
    public void onShowPress(MotionEvent e) {

    }


    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        //如果不是长按和多点触控，认为是滚动
        if (!isLongPress && !mMultipleTouch) {
            scrollBy(Math.round(distanceX), 0);
            return true;
        }
        return false;
    }


    @Override
    public void onLongPress(MotionEvent e) {
        isLongPress = true;
    }


    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        //触发滑动
        if (!isLongPress && mScrollEnable) {
            mScroller.fling(mScrollX, 0, Math.round(velocityX / mScaleX), 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0 );
        }
        return true;
    }


    @Override
    public void computeScroll() {
        //如果在滚动过程中， 手指触碰到屏幕， 滚动立即停止
        if (mScroller.computeScrollOffset()) {
            if (!mTouch) {
                scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            }else {
                mScroller.forceFinished(true);
            }
        }
    }


    @Override
    public void scrollBy(int x, int y) {
        scrollTo(mScrollX - Math.round(x / mScaleX), 0);
    }


    @Override
    public void scrollTo(int x, int y) {
        if (!mScrollEnable) {
            mScroller.forceFinished(true);
            return;
        }

        int oldX = mScrollX;
        mScrollX = x;

        //在最左边 或者 最右边时进行处理
        if (mScrollX < getMinScrollX()) {
            mScrollX = getMinScrollX();
            onRightSide();
            mScroller.forceFinished(true);
        }else if (mScrollX > getMaxScrollX()) {
            mScrollX = getMaxScrollX();
            onLeftSide();
            mScroller.forceFinished(true);
        }

        onScrollChanged(mScrollX, 0, oldX, 0);
        invalidate();
    }

    //缩放事件处理
    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        if (!mScaleEnable) {
            return false;
        }

        float oldScale = mScaleX;

        mScaleX *= detector.getScaleFactor();

        if (mScaleX < mScaleXMin) {
            mScaleX = mScaleXMin;
        }else if (mScaleX > mScaleXMax) {
            mScaleX = mScaleXMax;
        }else {
            onScaleChanged(mScaleX, oldScale);
        }

        return true;
    }



    protected void onScaleChanged(float scale, float oldScale) {
        invalidate();
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //多点触控发
        if (event.getPointerCount() > 1) {
            isLongPress = false;
        }

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                mTouch = true;
                x = event.getX();
                break;

            case MotionEvent.ACTION_MOVE:
                if (isLongPress) {
                    onLongPress(event);
                }
                break;

            case MotionEvent.ACTION_POINTER_UP:
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                if (x == event.getX()) {
                    if (isLongPress) {
                        isLongPress = false;
                    }
                }
                mTouch = false;
                invalidate();
                break;

            case MotionEvent.ACTION_CANCEL:
                isLongPress = false;
                mTouch = false;
                invalidate();
                break;
        }







        return super.onTouchEvent(event);
    }

    float x;


    public void setScrollEnable(boolean scrollEnable) {
        mScrollEnable = scrollEnable;
    }

    public void setScaleEnable(boolean scaleEnable) {
        mScaleEnable = scaleEnable;
    }


    @Override
    public float getScaleX() {
        return mScaleX;
    }

    public void setScaleXMax(float scaleXMax) {
        mScaleXMax = scaleXMax;
    }

    public void setScaleXMin(float scaleXMin) {
        mScaleXMin = scaleXMin;
    }

    public float getScaleXMax() {
        return mScaleXMax;
    }

    public float getScaleXMin() {
        return mScaleXMin;
    }

    public boolean isScaleEnable() {
        return mScaleEnable;
    }

    protected void checkAndFixScrollX() {
        if (mScrollX < getMinScrollX()) {
            mScrollX = getMinScrollX();
            mScroller.forceFinished(true);
        }else if (mScrollX > getMaxScrollX()) {
            mScrollX = getMaxScrollX();
            mScroller.forceFinished(true);
        }
    }

    public void setScrollX(int scrollX) {
        this.mScrollX = scrollX;
        scrollTo(scrollX, 0);
    }

    public boolean isTouch(){
        return mTouch;
    }

    public boolean isMultipleTouch() {
        return mMultipleTouch;
    }

    public boolean isScrollEnable() {
        return mScrollEnable;
    }

    public abstract int getMinScrollX();

    public abstract int getMaxScrollX();

    //滚动到最右边
    public abstract void onRightSide();

    //滚动到最左边
    public abstract void onLeftSide();
}
