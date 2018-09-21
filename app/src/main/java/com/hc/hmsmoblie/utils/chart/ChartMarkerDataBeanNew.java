package com.hc.hmsmoblie.utils.chart;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class ChartMarkerDataBeanNew {
    private String dataName = "";
    private List<Double> data = new ArrayList<>();

    public ChartMarkerDataBeanNew(String dataName, List<Double> data) {
        this.dataName = dataName;
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
}
