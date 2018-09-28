package com.hc.hmsmoblie.utils.chart;

import android.graphics.Paint;

import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.renderer.LineChartRenderer;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * 该类暂时只能用于LineDataSet（处理间断的数据）
 */

public class EmptyLineChartRendererNew extends LineChartRenderer {
    private static final int MIN_NUM = -1;

    public EmptyLineChartRendererNew(LineChart lineChart) {
        this(lineChart, lineChart.getAnimator(), lineChart.getViewPortHandler());
    }

    public EmptyLineChartRendererNew(LineDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(chart, animator, viewPortHandler);
    }

    /**
     * 检测是否不符合要求的Entry（越界，小于或等于MIN_NUM 为false）
     *
     * @param index   下标
     * @param dataSet
     * @return true符合   false不符合
     */
    private boolean isEmptyEntry(int index, ILineDataSet dataSet) {
        return dataSet != null && index >= 0 && index < dataSet.getEntryCount() && dataSet.getEntryForIndex(index).getY() > MIN_NUM;
    }

    @Override
    protected void drawCubicBezier(ILineDataSet dataSet) {
        LineDataSet iLineDataSet = null;
        List<Entry> yVals = null;
        for (int i = 0; i <= dataSet.getEntryCount(); i++) {
            if (null == yVals) {
                yVals = new ArrayList<>();
            }
            if (isEmptyEntry(i, dataSet)) {
                yVals.add(dataSet.getEntryForIndex(i));
            } else if (!yVals.isEmpty()) {
                iLineDataSet = (LineDataSet) ((LineDataSet) dataSet).copy();
                iLineDataSet.clear();
                iLineDataSet.setValues(yVals);
                super.drawCubicBezier(iLineDataSet);
                yVals.clear();
            }
        }
    }
}
