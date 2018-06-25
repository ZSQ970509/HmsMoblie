package com.hc.hmsmoblie.bean.json;

import java.util.List;

/**
 *
 */

public class EnvironmentDetailsJson {

    /**
     * max : [5,5,5,5,14,7,102,43,155,124,67,3,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1]
     * avg : [3,3,3,3,4,4,9,6,27,9,5,3,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1]
     * min : [2,2,1,2,2,3,4,2,4,4,2,3,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1]
     * Rate : 100
     */

    private int Rate;
    private List<Double> max;
    private List<Double> avg;
    private List<Double> min;

    public int getRate() {
        return Rate;
    }

    public void setRate(int Rate) {
        this.Rate = Rate;
    }

    public List<Double> getMax() {
        return max;
    }

    public void setMax(List<Double> max) {
        this.max = max;
    }

    public List<Double> getAvg() {
        return avg;
    }

    public void setAvg(List<Double> avg) {
        this.avg = avg;
    }

    public List<Double> getMin() {
        return min;
    }

    public void setMin(List<Double> min) {
        this.min = min;
    }
}
