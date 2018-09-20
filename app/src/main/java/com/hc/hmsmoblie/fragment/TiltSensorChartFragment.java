package com.hc.hmsmoblie.fragment;

import com.hc.hmsmoblie.R;
import com.yc.yclibrary.base.YcLazyFragment;

/**
 *  倾角流水数据图表
 */

public class TiltSensorChartFragment extends YcLazyFragment {

    public static TiltSensorChartFragment newInstance(){
        return new TiltSensorChartFragment();
    }
    @Override
    public int getLayoutResId() {
        return R.layout.tilt_sensor_chart_fragent;
    }

    @Override
    public void initView() {
    }
}
