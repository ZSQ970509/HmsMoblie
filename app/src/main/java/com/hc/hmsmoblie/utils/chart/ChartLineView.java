package com.hc.hmsmoblie.utils.chart;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.github.mikephil.charting.charts.LineChart;

/**
 * 解决LineChart与滑动条冲突问题
 */

public class ChartLineView extends LineChart {
    private PointF downPoint = new PointF();//按下时的位置

    public ChartLineView(Context context) {
        super(context);
    }

    public ChartLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChartLineView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    public boolean onTouchEvent(MotionEvent evt) {
        switch (evt.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downPoint.x = evt.getX();
                downPoint.y = evt.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                double limitX = getWidth() / 2;
                double limitY = getHeight() / 2;
                double x = Math.abs(evt.getX() - downPoint.x);
                double y = Math.abs(evt.getY() - downPoint.y);
                if (x < limitX && y < limitY) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
        }
        return super.onTouchEvent(evt);
    }
}
