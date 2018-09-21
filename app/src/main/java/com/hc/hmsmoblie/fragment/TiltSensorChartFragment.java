package com.hc.hmsmoblie.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.bean.json.TiltSensorChartJson;
import com.hc.hmsmoblie.mvp.contact.TiltSensorChartC;
import com.hc.hmsmoblie.mvp.presenter.TiltSensorChartP;
import com.hc.hmsmoblie.utils.chart.ChartMarkerDataBean;
import com.hc.hmsmoblie.utils.chart.ChartMarkerDataBeanNew;
import com.hc.hmsmoblie.utils.chart.ChartMarkerView;
import com.hc.hmsmoblie.utils.chart.ChartMarkerViewNew;
import com.hc.hmsmoblie.utils.chart.ChartUtils;
import com.yc.yclibrary.base.YcLazyFragment;
import com.yc.yclibrary.base.YcMvpLazyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 倾角流水数据图表
 */

public class TiltSensorChartFragment extends YcMvpLazyFragment<TiltSensorChartP> implements TiltSensorChartC.V {

    @BindView(R.id.lineChart)
    LineChart lineChart;

    public static TiltSensorChartFragment newInstance() {
        return new TiltSensorChartFragment();
    }

    @Override
    protected TiltSensorChartP loadPresenter() {
        return new TiltSensorChartP();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.tilt_sensor_chart_fragent;
    }

    @Override
    public void initView() {
        ChartUtils.initLineChart(lineChart, this.getContext());
        getTiltSensorChart();
    }

    public void getTiltSensorChart() {
        mPresenter.getTiltSensorChart("1014603", "", "0", "2018");
    }


    @Override
    public void getTiltSensorChartSuccess(TiltSensorChartJson json) {
        refreshLineData(json.getData());
    }

    private void refreshLineData(TiltSensorChartJson.DataBean dataBean) {
        ChartUtils.EmptyLineDataSet setData1 = ChartUtils.getLineDataSet(lineChart, dataBean.getNewOx(), 0, Color.parseColor("#3F51B5"), "1", LineDataSet.Mode.LINEAR);
        ChartUtils.EmptyLineDataSet setData2 = ChartUtils.getLineDataSet(lineChart, dataBean.getNewOy(), 1, Color.parseColor("#317bf6"), "2", LineDataSet.Mode.LINEAR);
        ChartUtils.EmptyLineDataSet setData3 = ChartUtils.getLineDataSet(lineChart, dataBean.getYuOxZ(), 0, Color.parseColor("#F44336"), "3", LineDataSet.Mode.LINEAR);
        ChartUtils.EmptyLineDataSet setData4 = ChartUtils.getLineDataSet(lineChart, dataBean.getYuOyZ(), 1, Color.parseColor("#FF4081"), "4", LineDataSet.Mode.LINEAR);
        ChartUtils.EmptyLineDataSet setData5 = ChartUtils.getLineDataSet(lineChart, dataBean.getYuOxF(), 0, Color.parseColor("#8388FF"), "5", LineDataSet.Mode.LINEAR);
        ChartUtils.EmptyLineDataSet setData6 = ChartUtils.getLineDataSet(lineChart, dataBean.getYuOyF(), 1, Color.parseColor("#FF3F00"), "6", LineDataSet.Mode.LINEAR);


        lineChart.setData(new LineData(setData1, setData2, setData3, setData4, setData5, setData6));

        List<ChartMarkerDataBeanNew> markerData = new ArrayList<>();
        markerData.add(new ChartMarkerDataBeanNew("", dataBean.getNewOx()));
        markerData.add(new ChartMarkerDataBeanNew("", dataBean.getNewOy()));
        markerData.add(new ChartMarkerDataBeanNew("", dataBean.getYuOxZ()));
        markerData.add(new ChartMarkerDataBeanNew("", dataBean.getYuOyZ()));
        markerData.add(new ChartMarkerDataBeanNew("", dataBean.getYuOxF()));
        markerData.add(new ChartMarkerDataBeanNew("", dataBean.getYuOyF()));
        lineChart.setMarker(new ChartMarkerViewNew(getActivity(), markerData));
        ChartUtils.setLegend(lineChart.getLegend());
        ChartUtils.setLeftYAxis(lineChart.getAxisLeft());
        ChartUtils.setXAxis(lineChart.getXAxis(), dataBean.getTimeName());
        //刷新图表
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();
    }

    @Override
    public void getTiltSensorChartFail(String msg) {

    }
}
