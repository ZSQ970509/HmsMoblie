package com.hc.hmsmoblie.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.classic.adapter.BaseAdapterHelper;
import com.classic.adapter.CommonAdapter;
import com.classic.adapter.CommonRecyclerAdapter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.bean.json.TiltSensorChartJson;
import com.hc.hmsmoblie.bean.json.TiltSensorParaJson;
import com.hc.hmsmoblie.bean.type.TimeType;
import com.hc.hmsmoblie.mvp.contact.TiltSensorChartC;
import com.hc.hmsmoblie.mvp.presenter.TiltSensorChartP;
import com.hc.hmsmoblie.utils.FormatUtils;
import com.hc.hmsmoblie.utils.TimePickerUtils;
import com.hc.hmsmoblie.utils.chart.ChartMarkerDataBeanNew;
import com.hc.hmsmoblie.utils.chart.ChartMarkerViewNew;
import com.hc.hmsmoblie.utils.chart.ChartUtils;
import com.yc.yclibrary.base.YcMvpLazyFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 倾角流水数据图表
 */

public class TiltSensorChartFragment extends YcMvpLazyFragment<TiltSensorChartP> implements TiltSensorChartC.V {

    @BindView(R.id.lineChart)
    LineChart lineChart;
    @BindView(R.id.tiltSensorChartTimeTv)
    TextView mTimeTv;
    @BindView(R.id.tiltSensorDayTv)
    TextView tiltSensorDayTv;
    @BindView(R.id.tiltSensorMonthTv)
    TextView tiltSensorMonthTv;
    @BindView(R.id.tiltSensorYearTv)
    TextView tiltSensorYearTv;
    @BindView(R.id.tiltSensorSp)
    Spinner mSp;
    @BindView(R.id.tiltSensorLegend)
    LinearLayout mLegendLL;
    private @TimeType
    int mTimeType = TimeType.DAY;
    private String[] preSelectTime = new String[3];
    private final String[] Name = new String[]{"X轴角度", "Y轴角度", "X轴阈值", "X轴阈值", "Y轴阈值", "Y轴阈值"};
    private final String[] TIME_FORMAT = new String[]{FormatUtils.FORMAT_TIME_YEAR, FormatUtils.FORMAT_TIME_MONTH, FormatUtils.FORMAT_TIME};//传给服务端的时间格式
    private CommonAdapter<TiltSensorParaJson.ListBean> mSpAdapter;
    private String mCamId = "";
    private List<TiltSensorParaJson.ListBean> mParaIds = new ArrayList<>();

    public static TiltSensorChartFragment newInstance(String camId, List<TiltSensorParaJson.ListBean> paraIds) {
        TiltSensorChartFragment fragment = new TiltSensorChartFragment();
        fragment.mCamId = camId;
        fragment.mParaIds = paraIds;
        return fragment;
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
        preSelectTime[0] = FormatUtils.calendarToString(Calendar.getInstance(), FormatUtils.FORMAT_TIME_YEAR).trim();
        preSelectTime[1] = FormatUtils.calendarToString(Calendar.getInstance(), FormatUtils.FORMAT_TIME_MONTH).trim();
        preSelectTime[2] = FormatUtils.calendarToString(Calendar.getInstance(), FormatUtils.FORMAT_TIME).trim();
        mTimeTv.setText(FormatUtils.calendarToString(Calendar.getInstance()).trim());
        mLegendLL.setVisibility(View.INVISIBLE);
        mSpAdapter = new CommonAdapter<TiltSensorParaJson.ListBean>(getActivity(), R.layout.item_common) {
            @Override
            public void onUpdate(BaseAdapterHelper helper, TiltSensorParaJson.ListBean item, int position) {
                helper.setText(R.id.itemCommonTv, item.getParaName());
            }
        };
        mSpAdapter.addAll(mParaIds);
        mSp.setAdapter(mSpAdapter);
        mSp.setSelection(0);
        ChartUtils.initLineChart(lineChart, this.getContext());
        getTiltSensorChart();
    }

    public void getTiltSensorChart() {
        mPresenter.getTiltSensorChart(mCamId, mSpAdapter.getItem(mSp.getSelectedItemPosition()).getParaID() + "", mTimeType + "", mTimeTv.getText().toString().trim());
    }

    @Override
    public void getTiltSensorChartSuccess(TiltSensorChartJson json) {
        refreshLineData(json.getData().get(0));
    }


    private void refreshLineData(TiltSensorChartJson.DataBean dataBean) {
        if (dataBean == null || isEmpty(dataBean.getNewOx()) || isEmpty(dataBean.getNewOy()) || isEmpty(dataBean.getYuOxZ()) || isEmpty(dataBean.getYuOyZ()) || isEmpty(dataBean.getYuOxF()) || isEmpty(dataBean.getYuOyF())) {
            lineChart.clear();
            mLegendLL.setVisibility(View.INVISIBLE);
            return;
        }
        mLegendLL.setVisibility(View.VISIBLE);
        ChartUtils.EmptyLineDataSet setData1 = ChartUtils.getLineDataSet(lineChart, dataBean.getNewOx(), 0, getResources().getColor(R.color.tiltSensorColorLineRed), Name[0], LineDataSet.Mode.LINEAR);
        ChartUtils.EmptyLineDataSet setData2 = ChartUtils.getLineDataSet(lineChart, dataBean.getNewOy(), 1, getResources().getColor(R.color.tiltSensorColorLineYellow), Name[1], LineDataSet.Mode.LINEAR);
        ChartUtils.EmptyLineDataSet setData3 = ChartUtils.getLineDataSet(lineChart, dataBean.getYuOxZ(), 2, getResources().getColor(R.color.tiltSensorColorLineBlue), Name[2], LineDataSet.Mode.LINEAR,true);
        ChartUtils.EmptyLineDataSet setData4 = ChartUtils.getLineDataSet(lineChart, dataBean.getYuOyZ(), 3, getResources().getColor(R.color.tiltSensorColorLineBlue), Name[3], LineDataSet.Mode.LINEAR,true);
        ChartUtils.EmptyLineDataSet setData5 = ChartUtils.getLineDataSet(lineChart, dataBean.getYuOxF(), 4, getResources().getColor(R.color.tiltSensorColorLineGreen), Name[4], LineDataSet.Mode.LINEAR,true);
        ChartUtils.EmptyLineDataSet setData6 = ChartUtils.getLineDataSet(lineChart, dataBean.getYuOyF(), 5, getResources().getColor(R.color.tiltSensorColorLineGreen), Name[5], LineDataSet.Mode.LINEAR,true);


        lineChart.setData(new LineData(setData1, setData2, setData3, setData4, setData5, setData6));

        List<ChartMarkerDataBeanNew> markerData = new ArrayList<>();
        markerData.add(new ChartMarkerDataBeanNew(Name[0], dataBean.getNewOx()));
        markerData.add(new ChartMarkerDataBeanNew(Name[1], dataBean.getNewOy()));
        markerData.add(new ChartMarkerDataBeanNew(Name[2], dataBean.getYuOxZ()));
        markerData.add(new ChartMarkerDataBeanNew(Name[3], dataBean.getYuOyZ()));
        markerData.add(new ChartMarkerDataBeanNew(Name[4], dataBean.getYuOxF()));
        markerData.add(new ChartMarkerDataBeanNew(Name[5], dataBean.getYuOyF()));
        ChartMarkerViewNew chartMarkerViewNew = new ChartMarkerViewNew(getActivity(), markerData) {
            @Override
            public void onAdapterUpdate(BaseAdapterHelper helper, ChartMarkerDataBeanNew item, int xIndex) {
                helper.setText(R.id.itemChartMarkerTv, item.getDataName() + "：" + item.getData().get(xIndex) + " (度)");
            }
        };
        chartMarkerViewNew.setChartView(lineChart);
        lineChart.setMarker(chartMarkerViewNew);
        lineChart.getLegend().setEnabled(false);
        ChartUtils.setLeftYAxis(lineChart.getAxisLeft());
        ChartUtils.setXAxis(lineChart.getXAxis(), dataBean.getTimeName(), -60f);
        //刷新图表
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();
    }

    @Override
    public void getTiltSensorChartFail(String msg) {
    }


    private void showPickerView() {
        boolean isShowMonth = false;
        boolean isShowDay = false;
        switch (mTimeType) {
            case TimeType.DAY:
                isShowDay = true;
                isShowMonth = true;
                break;
            case TimeType.MONTH:
                isShowDay = false;
                isShowMonth = true;
                break;
            case TimeType.YEAR:
                isShowDay = false;
                isShowMonth = false;
                break;
        }
        TimePickerUtils.showPickerView(getActivity(), "选择时间", mTimeTv, isShowMonth, isShowDay, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                String time = FormatUtils.dateToString(date, TIME_FORMAT[mTimeType]).trim();
                mTimeTv.setText(time);
                preSelectTime[mTimeType] = time;
            }
        });
    }

    private void switchTimeType(final @TimeType int timeType) {
        mTimeType = timeType;
        mTimeTv.setText(preSelectTime[mTimeType].trim());
        switch (mTimeType) {
            case TimeType.DAY:
                tiltSensorDayTv.setBackgroundResource(R.drawable.day_on);
                tiltSensorMonthTv.setBackgroundResource(R.drawable.month_off);
                tiltSensorYearTv.setBackgroundResource(R.drawable.year_off);
                break;
            case TimeType.MONTH:
                tiltSensorDayTv.setBackgroundResource(R.drawable.day_off);
                tiltSensorMonthTv.setBackgroundResource(R.drawable.month_on);
                tiltSensorYearTv.setBackgroundResource(R.drawable.year_off);
                break;
            case TimeType.YEAR:
                tiltSensorDayTv.setBackgroundResource(R.drawable.day_off);
                tiltSensorMonthTv.setBackgroundResource(R.drawable.month_off);
                tiltSensorYearTv.setBackgroundResource(R.drawable.year_on);
                break;
        }
    }

    private boolean isEmpty(List<Double> data) {
        return data == null || data.size() <= 0;
    }

    @OnClick({R.id.tiltSensorDayTv, R.id.tiltSensorMonthTv, R.id.tiltSensorYearTv, R.id.tiltSensorSearchTv, R.id.tiltSensorChartTimeTv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tiltSensorDayTv:
                switchTimeType(TimeType.DAY);
                break;
            case R.id.tiltSensorMonthTv:
                switchTimeType(TimeType.MONTH);
                break;
            case R.id.tiltSensorYearTv:
                switchTimeType(TimeType.YEAR);
                break;
            case R.id.tiltSensorSearchTv:
                getTiltSensorChart();
                break;
            case R.id.tiltSensorChartTimeTv:
                showPickerView();
                break;
        }
    }
}
