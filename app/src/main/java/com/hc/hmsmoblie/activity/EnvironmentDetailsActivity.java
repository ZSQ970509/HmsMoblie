package com.hc.hmsmoblie.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.bean.json.EnvironmentDetailsJson;
import com.hc.hmsmoblie.bean.type.EnvironmentParaTypeEnum;
import com.hc.hmsmoblie.bean.type.EnvironmentTimeTypeEnum;
import com.hc.hmsmoblie.mvp.contact.EnvironmentDetailsC;
import com.hc.hmsmoblie.mvp.presenter.EnvironmentDetailsP;
import com.hc.hmsmoblie.utils.FormatUtils;
import com.hc.hmsmoblie.utils.TimePickerUtils;
import com.hc.hmsmoblie.utils.chart.ChartMarkerDataBean;
import com.hc.hmsmoblie.utils.chart.ChartMarkerView;
import com.hc.hmsmoblie.utils.chart.ChartUtils;
import com.orhanobut.logger.Logger;
import com.yc.yclibrary.exception.ApiException;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 环境详情页面
 */

public class EnvironmentDetailsActivity extends BaseMvpActivity<EnvironmentDetailsP> implements EnvironmentDetailsC.V {
    @BindView(R.id.tvDetailsName)
    TextView mTvName;
    @BindView(R.id.tvDetailsName2)
    TextView mTvName2;

    @BindView(R.id.tvComplianceRate)
    TextView mTvComplianceRate;
    @BindView(R.id.tvComplianceRate2)
    TextView mTvComplianceRate2;

    @BindView(R.id.tvDetailsTime)
    TextView mTvTime;
    @BindView(R.id.tvDetailsTime2)
    TextView mTvTime2;

    @BindView(R.id.btnDetailsTimeForDay)
    TextView mTvTimeForDay;
    @BindView(R.id.btnDetailsTimeForDay2)
    TextView mTvTimeForDay2;

    @BindView(R.id.btnDetailsTimeForMonth)
    TextView mTvTimeForMonth;
    @BindView(R.id.btnDetailsTimeForMonth2)
    TextView mTvTimeForMonth2;

    @BindView(R.id.btnDetailsTimeForYear)
    TextView mTvTimeForYear;
    @BindView(R.id.btnDetailsTimeForYear2)
    TextView mTvTimeForYear2;

    @BindView(R.id.lcDetails)
    LineChart mLineChart;
    @BindView(R.id.lcDetails2)
    LineChart mLineChart2;
    private EnvironmentTimeTypeEnum mTimeTypeEnum = EnvironmentTimeTypeEnum.Month;
    //    private EnvironmentParaTypeEnum mParaTypeEnum = EnvironmentParaTypeEnum.PM2点5;
    private String mCamId = "";
    private String mSeqId = "";
    private String mProId = "";

    private static final String CAM_ID = "cam_id";
    private static final String SEQ_ID = "seq_id";
    private static final String PRO_ID = "pro_id";
    private Date mSelectTimed = new Date();
//    private EnvironmentDetailsJson mChartData;

    public static void newInstance(Activity activity, String camId, String seqId, String proId) {
        Intent intent = new Intent(activity, EnvironmentDetailsActivity.class);
        intent.putExtra(CAM_ID, camId);
        intent.putExtra(SEQ_ID, seqId);
        intent.putExtra(PRO_ID, proId);
        activity.startActivity(intent);
    }

    @Override
    protected EnvironmentDetailsP loadPresenter() {
        return new EnvironmentDetailsP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.environment_details_activity;
    }

    @Override
    protected void initView(Bundle bundle) {
        setToolBar("环境检测详情");
        mCamId = getIntent().getStringExtra(CAM_ID);
        mSeqId = getIntent().getStringExtra(SEQ_ID);
        mProId = getIntent().getStringExtra(PRO_ID);
//        mProId = "33193";
//        mCamId = "";
//        mSeqId = "1440-0028-sclw-3093";
//        mTime = "2018-06-01";
        ChartUtils.initLineChart(mLineChart, getActivity());
        ChartUtils.initLineChart(mLineChart2, getActivity());
        mTvTime.setText(FormatUtils.dateToString(mSelectTimed));
        mTvTime2.setText(FormatUtils.dateToString(mSelectTimed));
        refreshData(EnvironmentParaTypeEnum.PM2点5);
        refreshData(EnvironmentParaTypeEnum.PM10);
    }

    private void refreshData(EnvironmentParaTypeEnum paraTypeEnum) {
        mPresenter.getPara(mCamId, mSeqId, FormatUtils.dateToString(mSelectTimed), mProId, paraTypeEnum, mTimeTypeEnum.getType());
    }

    @Override
    public void onGetParaSuccess(EnvironmentDetailsJson detailsJson, EnvironmentParaTypeEnum paraTypeEnum) {
        LineChart lineChart;
        if (paraTypeEnum.equals(EnvironmentParaTypeEnum.PM2点5)) {
            mTvComplianceRate.setText("达标率：" + detailsJson.getRate() + "%");
            lineChart = mLineChart;
        } else {
            lineChart = mLineChart2;
            mTvComplianceRate2.setText("达标率：" + detailsJson.getRate() + "%");
        }
        setChartData(detailsJson, lineChart);
    }

    @Override
    public void onGetParaFail(ApiException apiException) {
        showToast(apiException.getMessage());
    }

    private void setChartData(EnvironmentDetailsJson chartData, LineChart lineChart) {
        if (chartData == null) {
            Logger.e("折线图的数据为空");
            lineChart.clear();
            return;
        }
        lineChart.getAxisLeft().setAxisMinimum(0f); //如果设置Y轴的最小值
        LineDataSet setData1 = ChartUtils.getLineDataSet(lineChart, chartData.getAvg(), 0, Color.parseColor("#7bb6eb"), "均值");
        LineDataSet setData2 = ChartUtils.getLineDataSet(lineChart, chartData.getMax(), 1, Color.parseColor("#444349"), "峰值");
        LineDataSet setData3 = ChartUtils.getLineDataSet(lineChart, chartData.getMin(), 2, Color.parseColor("#90ed7d"), "谷值");
        lineChart.setData(new LineData(setData1, setData2, setData3));
        ChartMarkerDataBean markerData = new ChartMarkerDataBean();
        markerData.setDataName1("均值");
        markerData.setDataName2("峰值");
        markerData.setDataName3("谷值");
        markerData.setData1(chartData.getAvg());
        markerData.setData2(chartData.getMax());
        markerData.setData3(chartData.getMin());
        lineChart.setMarker(new ChartMarkerView(getActivity(), markerData));
        ChartUtils.setLegend(lineChart.getLegend());
        ChartUtils.setLeftYAxis(lineChart.getAxisLeft());
        ChartUtils.setXAxis(lineChart.getXAxis(), getXAxisData());
        //刷新图表
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();
    }

    private List<String> getXAxisData() {
        List<String> data = new ArrayList<>();
        switch (mTimeTypeEnum) {
            case Day:
                for (int i = 0; i < 24; i++) {
                    if (i < 10)
                        data.add("0" + i + ":00");
                    else
                        data.add("" + i + ":00");
                }
                break;
            case Month:
                int dayNum = TIME_DAY_NUM[mSelectTimed.getMonth()];
                if (mSelectTimed.getMonth() == 2) {
                    int year = mSelectTimed.getYear();
                    if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                        dayNum++;
                    }
                }
                for (int i = 1; i <= dayNum; i++) {
                    data.add("" + i + "日");
                }
                break;
            case Year:
                for (int i = 1; i <= 12; i++) {
                    data.add("" + i + "月");
                }
                break;
        }
        return data;
    }

    private void showPickerView(boolean isShowMonth, boolean isShowDay, String name, TextView tvTime, TextView tvName, TextView tvTimeForDay, TextView tvTimeForMonth, TextView tvTimeForYear, int type,EnvironmentParaTypeEnum paraTypeEnum, EnvironmentTimeTypeEnum timeTypeEnum) {
        TimePickerUtils.showPickerView(getActivity(), "选择时间", tvTime, isShowMonth, isShowDay, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                mSelectTimed = date;
                mTimeTypeEnum = timeTypeEnum;
                tvTime.setText(FormatUtils.dateToString(date, isShowMonth, isShowDay));
                tvName.setText(name);
                switch (type){
                    case 0:
                        tvTimeForDay.setBackgroundResource(R.drawable.day_on);
                        tvTimeForMonth.setBackgroundResource(R.drawable.month_off);
                        tvTimeForYear.setBackgroundResource(R.drawable.year_off);
                        break;
                    case 1:
                        tvTimeForDay.setBackgroundResource(R.drawable.day_off);
                        tvTimeForMonth.setBackgroundResource(R.drawable.month_on);
                        tvTimeForYear.setBackgroundResource(R.drawable.year_off);
                        break;
                    case 2:
                        tvTimeForDay.setBackgroundResource(R.drawable.day_off);
                        tvTimeForMonth.setBackgroundResource(R.drawable.month_off);
                        tvTimeForYear.setBackgroundResource(R.drawable.year_on);
                        break;
                }

                refreshData(paraTypeEnum);
            }
        });
    }

    private final int[] TIME_DAY_NUM = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    @OnClick({R.id.btnDetailsTimeForDay, R.id.btnDetailsTimeForMonth, R.id.btnDetailsTimeForYear, R.id.btnDetailsTimeForDay2, R.id.btnDetailsTimeForMonth2, R.id.btnDetailsTimeForYear2})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDetailsTimeForDay:
                showPickerView(true, true, "PM2.5日线图", mTvTime, mTvName, mTvTimeForDay, mTvTimeForMonth, mTvTimeForYear, 0, EnvironmentParaTypeEnum.PM2点5, EnvironmentTimeTypeEnum.Day);
                break;
            case R.id.btnDetailsTimeForMonth:
                showPickerView(true, false, "PM2.5月线图", mTvTime, mTvName, mTvTimeForDay, mTvTimeForMonth, mTvTimeForYear, 1, EnvironmentParaTypeEnum.PM2点5, EnvironmentTimeTypeEnum.Month);
                break;
            case R.id.btnDetailsTimeForYear:
                showPickerView(false, false, "PM2.5年线图", mTvTime, mTvName, mTvTimeForDay, mTvTimeForMonth, mTvTimeForYear, 2, EnvironmentParaTypeEnum.PM2点5, EnvironmentTimeTypeEnum.Year);
                break;
            case R.id.btnDetailsTimeForDay2:
                showPickerView(true, true, "PM10日线图", mTvTime2, mTvName2, mTvTimeForDay2, mTvTimeForMonth2, mTvTimeForYear2, 0, EnvironmentParaTypeEnum.PM10, EnvironmentTimeTypeEnum.Day);
                break;
            case R.id.btnDetailsTimeForMonth2:
                showPickerView(true, false, "PM10月线图", mTvTime2, mTvName2, mTvTimeForDay2, mTvTimeForMonth2, mTvTimeForYear2, 1, EnvironmentParaTypeEnum.PM10, EnvironmentTimeTypeEnum.Month);
                break;
            case R.id.btnDetailsTimeForYear2:
                showPickerView(false, false, "PM10年线图", mTvTime2, mTvName2, mTvTimeForDay2, mTvTimeForMonth2, mTvTimeForYear2, 2, EnvironmentParaTypeEnum.PM10, EnvironmentTimeTypeEnum.Year);
                break;
        }
    }

}