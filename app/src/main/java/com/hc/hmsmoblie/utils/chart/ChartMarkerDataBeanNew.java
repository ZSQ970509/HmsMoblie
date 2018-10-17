package com.hc.hmsmoblie.utils.chart;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class ChartMarkerDataBeanNew {
    private String dataName = "";//数据名
    private List<Double> data = new ArrayList<>();
    private String unit = "";//单位

    public ChartMarkerDataBeanNew(String dataName, List<Double> data) {
        this.dataName = dataName;
        this.data = data;
    }

    public ChartMarkerDataBeanNew(String dataName, List<Double> data, String unit) {
        this.dataName = dataName;
        this.unit = unit;
        this.data = data;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public List<Double> getData() {
        return data;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
