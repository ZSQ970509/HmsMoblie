package com.hc.hmsmoblie.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.classic.adapter.BaseAdapterHelper;
import com.classic.adapter.CommonAdapter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.bean.domain.TiltSensorBean;
import com.hc.hmsmoblie.bean.domain.TiltSensorBeanNew;
import com.hc.hmsmoblie.bean.json.TiltSensorChartJson;
import com.hc.hmsmoblie.bean.json.TiltSensorChartJsonNew;
import com.hc.hmsmoblie.bean.json.TiltSensorParaJson;
import com.hc.hmsmoblie.bean.type.TiltSensorType;
import com.hc.hmsmoblie.bean.type.TimeType;
import com.hc.hmsmoblie.mvp.contact.TiltSensorChartC;
import com.hc.hmsmoblie.mvp.presenter.TiltSensorChartP;
import com.hc.hmsmoblie.utils.FormatUtils;
import com.hc.hmsmoblie.utils.TimePickerUtils;
import com.hc.hmsmoblie.utils.YcDecoration;
import com.hc.hmsmoblie.utils.chart.ChartLegendAdapter;
import com.hc.hmsmoblie.utils.chart.ChartMarkerDataBeanNew;
import com.hc.hmsmoblie.utils.chart.ChartMarkerViewNew;
import com.hc.hmsmoblie.utils.chart.ChartUtils;
import com.yc.yclibrary.base.YcMvpLazyFragment;

import java.util.ArrayList;
import java.util.Arrays;
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
    @BindView(R.id.selectItemSp)
    Spinner selectItemSp;
    @BindView(R.id.tiltSensorChartLegendRv)
    RecyclerView mChartLegendRv;
    private @TimeType
    int mTimeType = TimeType.DAY;
    private String[] preSelectTime = new String[3];
    private final String[] TIME_FORMAT = new String[]{FormatUtils.FORMAT_TIME_YEAR, FormatUtils.FORMAT_TIME_MONTH, FormatUtils.FORMAT_TIME};//传给服务端的时间格式
    private CommonAdapter<TiltSensorParaJson.ListBean> mSpAdapter;
    private CommonAdapter<String> selectItemSpAdapter;
    private String mCamId = "";
    private List<TiltSensorParaJson.ListBean> mParaIds = new ArrayList<>();
    private ChartLegendAdapter mChartLegendAdapter;

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
//        mLegendLL.setVisibility(View.INVISIBLE);
        mChartLegendRv.setVisibility(View.INVISIBLE);
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
        initSelectItem();
        getTiltSensorChart();
    }

    public void getTiltSensorChart() {
        mPresenter.getTiltSensorChart(mCamId, mSpAdapter.getItem(mSp.getSelectedItemPosition()).getParaID() + "", mTimeType + "", mTimeTv.getText().toString().trim(), selectItemSp.getSelectedItemPosition());
    }

    @Override
    public void getTiltSensorChartSuccess(TiltSensorChartJsonNew json) {
        mAllData = json.getData();
        refreshLineData();
    }

    @Override
    public void getTiltSensorChartFail(String msg) {
        lineChart.setNoDataText(this.getString(R.string.view_empty));
    }

    private void initSelectItem() {
        selectItemSpAdapter = new CommonAdapter<String>(getActivity(), R.layout.item_common) {
            @Override
            public void onUpdate(BaseAdapterHelper helper, String item, int position) {
                helper.setText(R.id.itemCommonTv, item);
            }
        };
        selectItemSpAdapter.addAll(Arrays.asList(getResources().getStringArray(R.array.selectItemSpData)));
        selectItemSp.setAdapter(selectItemSpAdapter);
        selectItemSp.setSelection(0);
//        selectItemSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////                Observable.just(0)
////                        .subscribeOn(Schedulers.newThread())
////                        .observeOn(AndroidSchedulers.mainThread())
////                        .subscribe(t -> {
////                            showLoading("加载中...");
//                refreshLineData();
////                            hideLoading();
////                        });
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//       });
    }

    private TiltSensorChartJsonNew.DataBeanX mAllData;

    private void refreshLineData() {
        if (isEmpty(mAllData)) {
            showEmptyChart();
            return;
        }
        lineChart.setNoDataText(this.getString(R.string.view_loading));
        mChartLegendRv.setVisibility(View.VISIBLE);
        //选择的数据类型
        @TiltSensorType
        int type = selectItemSp.getSelectedItemPosition();
        TiltSensorBeanNew tiltSensorBean = new TiltSensorBeanNew(mAllData);
        List<TiltSensorBeanNew.DataBean> tiltSensorDatas = tiltSensorBean.getData(type);//获取要展示的数据
        LineData lineData = new LineData();
        LineDataSet setData;

        mChartLegendAdapter = new ChartLegendAdapter(getActivity());
        mChartLegendAdapter.setAllData(tiltSensorDatas);
        mChartLegendRv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mChartLegendRv.setAdapter(mChartLegendAdapter);
        //初始化图表里的LineDataSet(线)数据
        List<ChartMarkerDataBeanNew> markerData = new ArrayList<>();
        int lineNum = tiltSensorDatas.size();//图表里线的数量
        int emptyLineNum = 0;//图表里线数据为空的数量
        for (int i = 0; i < lineNum; i++) {
            if (tiltSensorDatas.get(i).getmData() == null || tiltSensorDatas.get(i).getmData().size() <= 0) {
                emptyLineNum++;
                continue;
            }
            setData = ChartUtils.getLineDataSet(lineChart, tiltSensorDatas.get(i).getmData(),
                    i, getResources().getColor(tiltSensorDatas.get(i).getmColorReId()),
                    tiltSensorDatas.get(i).getmName(), LineDataSet.Mode.LINEAR, tiltSensorDatas.get(i).getmIsDottedLine());
            lineData.addDataSet(setData);
            markerData.add(new ChartMarkerDataBeanNew(tiltSensorDatas.get(i).getmName(), tiltSensorDatas.get(i).getmData(), tiltSensorDatas.get(i).getUnit()));
        }
        if (emptyLineNum == lineNum) {//所有线的数据为空则将图表显示为没有数据
            showEmptyChart();
            return;
        }
//        Log.e("单条数据量", "" + mAllData.getNewOy().size());
        lineChart.setData(lineData);
        //单击图表后的显示的对话框
        List<String> xAisData = mAllData.getTimeName();//x轴上面的数据
        ChartMarkerViewNew chartMarkerViewNew = new ChartMarkerViewNew(getActivity(), markerData, xAisData) {
            @Override
            public void onAdapterUpdate(BaseAdapterHelper helper, ChartMarkerDataBeanNew item, int xIndex) {
                helper.setText(R.id.itemChartMarkerTv, item.getDataName() + "：" + item.getData().get(xIndex) + " (" + item.getUnit() + ")");
            }
        };
        chartMarkerViewNew.setChartView(lineChart);
        lineChart.setMarker(chartMarkerViewNew);
        lineChart.getLegend().setEnabled(false);
        ChartUtils.setLeftYAxis(lineChart.getAxisLeft());
        ChartUtils.setXAxis(lineChart.getXAxis(), xAisData, -60f);
        //刷新图表
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();
        lineChart.setVisibleXRangeMaximum(45);
    }

    /**
     * 将图表设置成没有数据
     */
    private void showEmptyChart() {
        lineChart.clear();
        mChartLegendRv.setVisibility(View.INVISIBLE);
        lineChart.setNoDataText(this.getString(R.string.view_empty));
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
        TimePickerUtils.showPickerView(getActivity(), "选择时间", mTimeTv.getText().toString().trim(), isShowMonth, isShowDay, new TimePickerView.OnTimeSelectListener() {
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

    private boolean isEmpty(TiltSensorChartJsonNew.DataBeanX data) {
        if (data == null || data.getData() == null || data.getData().size() <= 0 || data.getData().get(0).getTiltSensorData() == null) {
            return true;
        } else {
            return false;
        }
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
