package com.hc.hmsmoblie.utils.chart;

import android.graphics.Paint;

import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.renderer.LineChartRenderer;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

/**
 *
 */

public class EmptyLineChartRenderer extends LineChartRenderer {
    private static final int MIN_NUM = -1;

    public EmptyLineChartRenderer(LineChart lineChart) {
        this(lineChart, lineChart.getAnimator(), lineChart.getViewPortHandler());
    }

    public EmptyLineChartRenderer(LineDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(chart, animator, viewPortHandler);
    }

    private void cubicto(Entry prev, Entry cur, Entry next, Entry prevPrev, float intensity, boolean isMove) {
        float phaseX = Math.max(0.f, Math.min(1.f, mAnimator.getPhaseX()));
        float phaseY = mAnimator.getPhaseY();
//        float intensity = dataSet.getCubicIntensity();
        float prevDx = (cur.getX() - prevPrev.getX()) * intensity;
        float prevDy = (cur.getY() - prevPrev.getY()) * intensity;
        float curDx = (next.getX() - prev.getX()) * intensity;
        float curDy = (next.getY() - prev.getY()) * intensity;
        if (isMove) {
            cubicPath.moveTo(prev.getX(), prev.getY() * phaseY);
        }
        cubicPath.cubicTo(prev.getX() + prevDx, (prev.getY() + prevDy) * phaseY,
                cur.getX() - curDx, (cur.getY() - curDy) * phaseY,
                cur.getX(), cur.getY() * phaseY);
    }

    /**
     * 检测是否不符合要求的Entry（越界，小于或等于MIN_NUM 为false）
     *
     * @param index   下标
     * @param dataSet
     * @return
     */
    private boolean isEmptyEntry(int index, ILineDataSet dataSet) {
        return dataSet != null && index < dataSet.getEntryCount() && dataSet.getEntryForIndex(index).getY() > MIN_NUM;
    }

    @Override
    protected void drawCubicBezier(ILineDataSet dataSet) {
        float phaseX = Math.max(0.f, Math.min(1.f, mAnimator.getPhaseX()));
        float phaseY = mAnimator.getPhaseY();
        Transformer trans = mChart.getTransformer(dataSet.getAxisDependency());
        mXBounds.set(mChart, dataSet);
        float intensity = dataSet.getCubicIntensity();
        cubicPath.reset();
        if (mXBounds.range >= 1) {
            final int firstIndex = mXBounds.min + 1;
            final int lastIndex = mXBounds.min + mXBounds.range;
            Entry prevPrev = dataSet.getEntryForIndex(Math.max(firstIndex - 3, 0));
            Entry prev = dataSet.getEntryForIndex(Math.max(firstIndex - 2, 0));
            Entry cur = dataSet.getEntryForIndex(Math.max(firstIndex - 1, 0));
            Entry next = cur;
            int prevIndex = Math.max(firstIndex - 2, 0);
            int curIndex = Math.max(firstIndex - 1, 0);
            int nextIndex = Math.max(firstIndex, 0);
            if (cur == null) return;
            boolean isNewDraw = true;
            for (int j = firstIndex - 1; j <= lastIndex; j++) {
                //判断是否是一段全新开始画的折线
                if (isNewDraw) {
                    if (isEmptyEntry(j, dataSet) && isEmptyEntry(j + 1, dataSet)) {
                        prevIndex = j;
                        curIndex = j + 1;
                        nextIndex = isEmptyEntry(j + 1, dataSet) ? j + 2 : j + 1;
                        prev = dataSet.getEntryForIndex(prevIndex);
                        prevPrev = prev;
                        cur = dataSet.getEntryForIndex(curIndex);
                        next = dataSet.getEntryForIndex(nextIndex);
                        j++;//使j为curIndex
                        cubicPath.moveTo(prev.getX(), prev.getY() * phaseY);
                        isNewDraw = false;
                        cubicto(prev, cur, next, prevPrev, intensity, false);
                    }
                } else {
                    if (isEmptyEntry(j, dataSet)) {
                        prevPrev = prev;
                        prevIndex = curIndex;
                        prev = cur;
                        cur = dataSet.getEntryForIndex(j);
                        nextIndex = isEmptyEntry(j + 1, dataSet) ? j + 1 : j;
                        next = dataSet.getEntryForIndex(nextIndex);
                        cubicto(prev, cur, next, prevPrev, intensity, false);
                    } else {
                        isNewDraw = true;
                    }
                }
            }
        }
        if (dataSet.isDrawFilledEnabled()) {
            cubicFillPath.reset();
            cubicFillPath.addPath(cubicPath);
            drawCubicFill(mBitmapCanvas, dataSet, cubicFillPath, trans, mXBounds);
        }
        mRenderPaint.setColor(dataSet.getColor());
        mRenderPaint.setStyle(Paint.Style.STROKE);
        trans.pathValueToPixel(cubicPath);
        mBitmapCanvas.drawPath(cubicPath, mRenderPaint);
        mRenderPaint.setPathEffect(null);
    }
}
