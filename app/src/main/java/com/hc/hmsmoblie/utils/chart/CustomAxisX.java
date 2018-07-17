package com.hc.hmsmoblie.utils.chart;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.List;

/**
 * 折线图里的自定义x轴
 */

public class CustomAxisX implements IAxisValueFormatter {
    private List<String> mValues;

    public CustomAxisX(List<String> values) {
        this.mValues = values;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        if (value >= mValues.size() || value < 0) {
            return "";
        } else {
            return mValues.get((int) value);
        }
    }
}
