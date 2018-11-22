package com.hc.hmsmoblie.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.classic.adapter.BaseAdapterHelper;
import com.classic.adapter.CommonAdapter;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.bean.domain.TiltSensorAlarmBean;
import com.hc.hmsmoblie.bean.json.TiltSensorAllJson;
import com.hc.hmsmoblie.bean.json.TiltSensorParaJson;
import com.hc.hmsmoblie.bean.json.TiltSensorStateJson;
import com.hc.hmsmoblie.bean.type.TiltSensorParaState;
import com.hc.hmsmoblie.mvp.contact.TiltSensorActivityC;
import com.hc.hmsmoblie.mvp.presenter.TiltSensorActivityP;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.hc.hmsmoblie.utils.BatAndSignalUtil;
import com.hc.hmsmoblie.utils.FormatUtils;
import com.hc.hmsmoblie.utils.TiltSensorStateUtils;
import com.hc.hmsmoblie.utils.chart.ChartLineView;
import com.hc.hmsmoblie.utils.chart.ChartUtils;
import com.hc.hmsmoblie.widget.AlarmDialog;
import com.hc.hmsmoblie.widget.CommonDialog;
import com.hc.hmsmoblie.widget.DeviceStateDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 *
 */

public class TiltSensorActivity extends BaseMvpActivity<TiltSensorActivityP> implements TiltSensorActivityC.V {
    private static final String CAM_ID = "cam_id";
    @BindView(R.id.tiltSensorSignalIv)
    ImageView mSignalIv;
    @BindView(R.id.tiltSensorElectricityIv)
    ImageView mElectricityIv;
    @BindView(R.id.tiltSensorParamTitleSp)
    Spinner mParamTitleSp;
    @BindView(R.id.tiltSensorTimeTv)
    TextView mTimeTv;
    @BindView(R.id.tiltSensorXTv)
    TextView mXTv;
    @BindView(R.id.tiltSensorXIv)
    ImageView mXIv;
    @BindView(R.id.tiltSensorHeightTv)
    TextView mHeightTv;
    @BindView(R.id.tiltSensorHeightIv)
    ImageView mHeightIv;
    @BindView(R.id.tiltSensorYIv)
    ImageView mYIv;
    @BindView(R.id.tiltSensorYTv)
    TextView mYTv;
    @BindView(R.id.tiltSensorItemTitleTv)
    TextView mItemTitleTv;
    @BindView(R.id.tiltSensorAlarmXIv)
    ImageView mAlarmXIv;
    @BindView(R.id.tiltSensorAlarmXTv)
    TextView mAlarmXTv;
    @BindView(R.id.tiltSensorAlarmYTv)
    TextView mAlarmYTv;
    @BindView(R.id.tiltSensorAlarmYIv)
    ImageView mAlarmYIv;
    @BindView(R.id.tiltSensorAlarmHeightIv)
    ImageView mAlarmHeightIv;
    @BindView(R.id.tiltSensorAlarmHeightTv)
    TextView mAlarmHeightTv;
    @BindView(R.id.tiltSensorAlarmSpaceIv)
    ImageView mAlarmSpaceIv;
    @BindView(R.id.tiltSensorAlarmSpaceTv)
    TextView mAlarmSpaceTv;
    @BindView(R.id.tiltSensorAlarmHFLeftIv)
    ImageView mAlarmHFLeftIv;
    @BindView(R.id.tiltSensorAlarmHFLeftTv)
    TextView mAlarmHFLeftTv;
    @BindView(R.id.tiltSensorAlarmHFRightIv)
    ImageView mAlarmHFRightIv;
    @BindView(R.id.tiltSensorAlarmHFRightTv)
    TextView mAlarmHFRightTv;
    @BindView(R.id.tiltSensorSettingIv)
    ImageView mSettingIv;
    @BindView(R.id.tiltSensorSatesIv)
    ImageView mSatesIv;
    @BindView(R.id.tiltSensorSwitchIv)
    ImageView mOnOffIv;
    @BindView(R.id.tiltSensorLeftIv)
    ImageView mLeftIv;
    @BindView(R.id.tiltSensorRightIv)
    ImageView mRightIv;
    @BindView(R.id.tiltSensorLineChart)
    ChartLineView mLineChart;
    @BindView(R.id.tiltSensorMoreTv)
    TextView mMoreTv;
    @BindView(R.id.tiltSensorHeightLineChart)
    ChartLineView mHeightLineChart;
    @BindView(R.id.tiltSensorHeightMoreTv)
    TextView mHeightMoreTv;
    private String mCamId;
    @TiltSensorParaState
    private String mParaState = TiltSensorParaState.OPEN;//监测点开启或者关闭状态
    private String mParaID;
    private String mSeq;//
    private List<TiltSensorParaJson.ListBean> mParaList = new ArrayList<>();//点位列表数据
    private CommonAdapter<TiltSensorParaJson.ListBean> mParamTitleAdapter;
    private int mRefreshTime = 15;//刷新数据的时间间隔
    private Disposable mDisposableRefresh;//用于循环刷新的暂停和取消
    private boolean mIsParaInit = false;//监测点列表数据是否完成初始化
    private NetObserver<HttpResponse<TiltSensorAllJson>> responseNetObserver;//用于取消前一个刷新的网络请求
    private TiltSensorAlarmBean mTiltSensorAlarmBean;//手机端临时预警设置数据

    public static void newInstance(Activity activity, String camID) {
        Intent intent = new Intent(activity, TiltSensorActivity.class);
        intent.putExtra(CAM_ID, camID);
        activity.startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loopRefreshData();
    }

    @Override
    protected TiltSensorActivityP loadPresenter() {
        return new TiltSensorActivityP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.tilt_sensor_activity;
    }

    @Override
    protected void initView(Bundle bundle) {
        setToolBar("倾角数据");
        COLOR = new int[]{
                getResources().getColor(R.color.tiltSensorColorLineGreen),
                getResources().getColor(R.color.tiltSensorColorLineYellow),
                getResources().getColor(R.color.tiltSensorColorLineRed),
                getResources().getColor(R.color.tiltSensorColorLineBlue)};
        ChartUtils.initLineChart(mLineChart, getActivity());
        mLineChart.setBackgroundResource(R.color.colorWhite);
        ChartUtils.initLineChart(mHeightLineChart, getActivity());
        mHeightLineChart.setBackgroundResource(R.color.colorWhite);
        mTiltSensorAlarmBean = new TiltSensorAlarmBean();
        mCamId = getIntent().getStringExtra(CAM_ID);
        mParamTitleAdapter = new CommonAdapter<TiltSensorParaJson.ListBean>(getActivity(), R.layout.item_common) {
            @Override
            public void onUpdate(BaseAdapterHelper helper, TiltSensorParaJson.ListBean item, int position) {
                TextView textView = helper.getView(R.id.itemCommonTv);
                textView.setTextSize(20);
                textView.setText(item.getParaName());
                textView.setTextColor(Color.parseColor("#333333"));
            }
        };
        mParamTitleSp.setAdapter(mParamTitleAdapter);
        mParamTitleSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mParaID = mParaList.get(position).getParaID() + "";
                mSeq = mParaList.get(position).getSeq();
                mParaState = mParaList.get(position).getStates();
                refreshState();
                loopRefreshData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        mPresenter.getGetTiltSensorPara(mCamId);
    }

    @Override
    public void onGetTiltSensorParaSuccess(TiltSensorParaJson dataBean) {
        if (dataBean == null || dataBean.getList() == null || dataBean.getList().size() <= 0) {
            onGetTiltSensorParaFail("没有数据");
        } else {
            mParaList = dataBean.getList();
            mParamTitleAdapter.clear();
            mParamTitleAdapter.addAll(mParaList);
            mParaID = mParaList.get(0).getParaID() + "";
            mIsParaInit = true;
            mParamTitleSp.setSelection(0);
            mSeq = mParaList.get(0).getSeq();
            mPresenter.getTitAll(mParaList.get(mParamTitleSp.getSelectedItemPosition()).getParaID() + "", true);
        }
    }

    @Override
    public void onGetTiltSensorParaFail(String msg) {
        CommonDialog.newInstanceSingle(getActivity())
                .setMsg(msg)
                .setSingleBtnText("返回")
                .setSingleClick(v -> finish())
                .show();
    }


    /**
     * 刷新监测点开关状态
     */
    private void refreshState() {
        if (mParaState.equals(TiltSensorParaState.OPEN)) {
            mOnOffIv.setImageResource(R.drawable.ic_open);
        } else {
            mOnOffIv.setImageResource(R.drawable.ic_close);
        }
    }

    /**
     * 后台刷新数据
     */
    private void loopRefreshData() {
        if (!mIsParaInit) return;
        if (TextUtils.isEmpty(mParaID)) {
            showToast("paraID为空");
            return;
        }
        if (mDisposableRefresh != null)
            mDisposableRefresh.dispose();
        //initialDelay第一次执行间隔 period之后执行间隔
        mDisposableRefresh = Observable.interval(0, mRefreshTime, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    if (responseNetObserver != null)
                        responseNetObserver.cancel();
                    if (aLong == 0) {
                        responseNetObserver = mPresenter.getTitAll(mParaID, true);
                    } else {
                        responseNetObserver = mPresenter.getTitAll(mParaID, false);
                    }
                });
    }

    private TiltSensorAllJson.DatTiltSensorBean mData;//状态数据
    private List<TiltSensorAllJson.DatTiltSensorListBean> mDataChartAngle;//倾角图表数据
    private List<TiltSensorAllJson.DatTiltSensorListTimeBean> mDataChartHeight;//高度图表数据
    private int[] COLOR;

    @Override
    public void onGetTitAllSuccess(TiltSensorAllJson tiltSensorJson) {
        mData = null;
        mDataChartAngle = null;
        mDataChartHeight = null;
        if (tiltSensorJson != null) {
            if (tiltSensorJson.getDat_TiltSensor() != null && tiltSensorJson.getDat_TiltSensor().size() > 0) {
                mData = tiltSensorJson.getDat_TiltSensor().get(0);
            }
            if (tiltSensorJson.getDat_TiltSensorList() != null && tiltSensorJson.getDat_TiltSensorList().size() > 0) {
                mDataChartAngle = tiltSensorJson.getDat_TiltSensorList();
            }
            if (tiltSensorJson.getDat_TiltSensorList_time() != null && tiltSensorJson.getDat_TiltSensorList_time().size() > 0) {
                mDataChartHeight = tiltSensorJson.getDat_TiltSensorList_time();
            }
        }
        refreshDataUI();
        refreshLineAngle();
        refreshLineHeight();
    }

    private void refreshLineAngle() {
        if (mDataChartAngle == null) {
            mLineChart.setNoDataText(this.getString(R.string.view_empty));
            mLineChart.clear();
            return;
        }
        List<String> dataMarkerX = new ArrayList<>();//x轴上面的数据
        List<String> dataX = new ArrayList<>();//x轴上面的数据
        List<List<Double>> dataY = new ArrayList<>();//y轴上面的数据
        dataY.add(new ArrayList<>());
        dataY.add(new ArrayList<>());
        dataY.add(new ArrayList<>());
        dataY.add(new ArrayList<>());
        for (int i = 0; i < mDataChartAngle.size(); i++) {
            String x = mDataChartAngle.get(i).getCreateTime();
            dataMarkerX.add(x);
            dataX.add(x.substring(x.length() - 9));
            dataY.get(0).add(mDataChartAngle.get(i).getFirstOldx());
            dataY.get(1).add(mDataChartAngle.get(i).getFirstOldy());
            dataY.get(2).add(mDataChartAngle.get(i).getPx());
            dataY.get(3).add(mDataChartAngle.get(i).getPy());
        }
        ChartUtils.setData(getActivity(), mLineChart, new String[]{"X轴累计角度差", "y轴累计角度差", "x轴阈值", "y轴阈值"}, COLOR, dataMarkerX, dataX, dataY, Arrays.asList("度", "度", "度", "度"));
    }

    private void refreshLineHeight() {
        if (mDataChartHeight == null) {
            mHeightLineChart.setNoDataText(this.getString(R.string.view_empty));
            mHeightLineChart.clear();
            return;
        }
        List<String> dataMarkerX = new ArrayList<>();//x轴上面的数据
        List<String> dataX = new ArrayList<>();//x轴上面的数据
        List<Double> dataY = new ArrayList<>();//y轴上面的数据
        for (int i = 0; i < mDataChartHeight.size(); i++) {
            String x = mDataChartHeight.get(i).getCreateTime();
            dataMarkerX.add(x);
            dataX.add(x.substring(x.length() - 9));
            dataY.add(mDataChartHeight.get(i).getObd());
        }
        ChartUtils.setData(getActivity(), mHeightLineChart, new String[]{"高度"}, COLOR, dataMarkerX, dataX, Arrays.asList(dataY), Arrays.asList("mm"));
    }


    private void refreshDataUI() {
        if (mData == null) {
            mTimeTv.setText("时间：-");
            mXTv.setText("X轴：-");
            mXIv.setVisibility(View.INVISIBLE);
            mYTv.setText("Y轴：-");
            mYIv.setVisibility(View.INVISIBLE);
            mHeightTv.setText("高度：-");
            mHeightIv.setVisibility(View.INVISIBLE);
            mAlarmXTv.setText("X轴：-");
            mAlarmXIv.setVisibility(View.INVISIBLE);
            mAlarmYTv.setText("Y轴：-");
            mAlarmYIv.setVisibility(View.INVISIBLE);
            mAlarmHeightTv.setText("高度：-");
            mAlarmHeightIv.setVisibility(View.INVISIBLE);
            mAlarmSpaceTv.setText("空间位移：-");
            mAlarmSpaceIv.setVisibility(View.INVISIBLE);
            mAlarmHFLeftTv.setText("左端浮动：-");
            mAlarmHFLeftIv.setVisibility(View.INVISIBLE);
            mAlarmHFRightTv.setText("右端浮动：-");
            mAlarmHFRightIv.setVisibility(View.INVISIBLE);

        } else {
            mTimeTv.setText("时间：" + mData.getCreateTime());
            //设置监测点开关状态
            if (mData.getStates().equals(TiltSensorParaState.OPEN)) {
                mParaState = TiltSensorParaState.OPEN;
            } else {
                mParaState = TiltSensorParaState.CLOSE;
            }
            mParaList.get(mParamTitleSp.getSelectedItemPosition()).setStates(mParaState);
            refreshState();
            //电量
            mElectricityIv.setImageResource(BatAndSignalUtil.batLevel(mData.getVo()));
            //信号强度
            mSignalIv.setImageResource(BatAndSignalUtil.signalLevel(mData.getCam_singnal()));

            mXTv.setText("X轴：" + TiltSensorStateUtils.formatX(mData.getOx()) + FormatUtils.stripTrailingZeros(Math.abs(mData.getOx())) + "°");
            mXIv.setImageResource(TiltSensorStateUtils.getState(mData.getOldx()));
            mXIv.setVisibility(View.VISIBLE);

            mYTv.setText("Y轴：" + TiltSensorStateUtils.formatX(mData.getOy()) + FormatUtils.stripTrailingZeros(Math.abs(mData.getOy())) + "°");
            mYIv.setImageResource(TiltSensorStateUtils.getState(mData.getOldy()));
            mYIv.setVisibility(View.VISIBLE);

            mHeightTv.setText("高度：" + FormatUtils.stripTrailingZeros(mData.getObd()) + "mm");
            mHeightIv.setImageResource(TiltSensorStateUtils.getState(mData.getCdObd()));
            mHeightIv.setVisibility(View.VISIBLE);

            mAlarmXIv.setVisibility(isShowAlarm(mTiltSensorAlarmBean.isOpen(), mData.getFirstOldx(), mTiltSensorAlarmBean.getAxisX()));
            mAlarmXTv.setText("X轴：" + TiltSensorStateUtils.formatX(mData.getFirstOldx()) + FormatUtils.stripTrailingZeros(Math.abs(mData.getFirstOldx())) + "°");

            mAlarmYIv.setVisibility(isShowAlarm(mTiltSensorAlarmBean.isOpen(), mData.getFirstOldy(), mTiltSensorAlarmBean.getAxisY()));
            mAlarmYTv.setText("Y轴：" + TiltSensorStateUtils.formatX(mData.getFirstOldy()) + FormatUtils.stripTrailingZeros(Math.abs(mData.getFirstOldy())) + "°");

            //累计高度即累计沉降位移
            mAlarmHeightIv.setVisibility(isShowAlarm(mTiltSensorAlarmBean.isOpen(), mData.getCdObdAdd(), mTiltSensorAlarmBean.getSettlement()));
            mAlarmHeightTv.setText("沉降：" + TiltSensorStateUtils.formatSettlement(mData.getCdObdAdd()) + FormatUtils.stripTrailingZeros(Math.abs(mData.getCdObdAdd())) + "mm");

            mAlarmSpaceIv.setVisibility(isShowAlarm(mTiltSensorAlarmBean.isOpen(), mData.getHightObdAdd(), mTiltSensorAlarmBean.getSpace()));
            mAlarmSpaceTv.setText("空间位移：" + FormatUtils.stripTrailingZeros(mData.getHightObdAdd()) + "mm");

            mAlarmHFLeftIv.setVisibility(isShowAlarm(mTiltSensorAlarmBean.isOpen(), mData.getFloatObdLeft(), mTiltSensorAlarmBean.getHorizontalFloatingLeft()));
            mAlarmHFLeftTv.setText("左端浮动：" + TiltSensorStateUtils.formatSettlement(mData.getFloatObdLeft()) + FormatUtils.stripTrailingZeros(Math.abs(mData.getFloatObdLeft())) + "mm");

            mAlarmHFRightIv.setVisibility(isShowAlarm(mTiltSensorAlarmBean.isOpen(), mData.getFloatObdRight(), mTiltSensorAlarmBean.getHorizontalFloatingRight()));
            mAlarmHFRightTv.setText("右端浮动：" + TiltSensorStateUtils.formatSettlement(mData.getFloatObdRight()) + FormatUtils.stripTrailingZeros(Math.abs(mData.getFloatObdRight())) + "mm");
        }
    }

    @Override
    public void onGetTitAllFail(String tiltSensorJson) {

    }

    @Override
    public void onSetAllMessage(@TiltSensorParaState String isOnOff) {//监测点开关回调
        mParaState = isOnOff;
        mParaList.get(mParamTitleSp.getSelectedItemPosition()).setStates(mParaState);
        refreshState();
    }

    @Override
    public void onGetDeviceState(TiltSensorStateJson tiltSensorStateJson) {//获取设备状态（华为接口）
        DeviceStateDialog.newInstance(getActivity())
                .setData(tiltSensorStateJson)
                .show();
    }

    @Override
    public void onGetDeviceSetting(TiltSensorStateJson tiltSensorStateJson) {

    }

    private int isShowAlarm(boolean isOpen, double value, double alarmValue) {
        if (isOpen && Math.abs(value) > Math.abs(alarmValue))
            return View.VISIBLE;
        else
            return View.INVISIBLE;
    }

    @Override
    protected void onStop() {
        if (mDisposableRefresh != null) {
            mDisposableRefresh.dispose();
            mDisposableRefresh = null;
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (mDisposableRefresh != null) {
            mDisposableRefresh.dispose();
            mDisposableRefresh = null;
        }
        super.onDestroy();
    }

    @OnClick({R.id.tiltSensorSettingIv, R.id.tiltSensorSatesIv, R.id.tiltSensorSwitchIv, R.id.tiltSensorLeftIv,
            R.id.tiltSensorRightIv, R.id.tiltSensorMoreTv, R.id.tiltSensorHeightMoreTv, R.id.tiltSensorAlarmIv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tiltSensorSettingIv:
                mPresenter.setTiltSensorState(mSeq);
                break;
            case R.id.tiltSensorSatesIv:
                mPresenter.getTiltSensorState(mSeq);
                break;
            case R.id.tiltSensorSwitchIv:
                if (mParaState.equals(TiltSensorParaState.OPEN)) {
                    mPresenter.setAllMessage(mParaID, mSeq, TiltSensorParaState.CLOSE + "");
                } else {
                    mPresenter.setAllMessage(mParaID, mSeq, TiltSensorParaState.OPEN + "");
                }
                break;
            case R.id.tiltSensorLeftIv:
                int currentSelectPosition1 = mParamTitleSp.getSelectedItemPosition();
                if (currentSelectPosition1 == 0) {
                    showMsg("已经是第一个了!");
                } else {
                    mParamTitleSp.setSelection(currentSelectPosition1 - 1);
                }
                break;
            case R.id.tiltSensorRightIv:
                int currentSelectPosition2 = mParamTitleSp.getSelectedItemPosition();
                if (currentSelectPosition2 >= mParamTitleAdapter.getCount() - 1) {
                    showMsg("已经是最后一个了!");
                } else {
                    mParamTitleSp.setSelection(currentSelectPosition2 + 1);
                }
                break;
            case R.id.tiltSensorAlarmIv:
                if (!mTiltSensorAlarmBean.isAlreadySet() && mData != null) {
                    mTiltSensorAlarmBean.setAxisX(mData.getFirstOldx());
                    mTiltSensorAlarmBean.setAxisY(mData.getFirstOldy());
                    mTiltSensorAlarmBean.setSettlement(mData.getCdObdAdd());
                    mTiltSensorAlarmBean.setSpace(mData.getHightObdAdd());
                    mTiltSensorAlarmBean.setHorizontalFloatingLeft(mData.getFloatObdLeft());
                    mTiltSensorAlarmBean.setHorizontalFloatingRight(mData.getFloatObdRight());
                }
                new AlarmDialog(getActivity())
                        .setAlarmData(mTiltSensorAlarmBean)
                        .setLeftClick(tiltSensorAlarmBean -> {
                            mTiltSensorAlarmBean = tiltSensorAlarmBean;
                            mTiltSensorAlarmBean.setAlreadySet(true);
                            loopRefreshData();
                        })
                        .show();
                break;
            case R.id.tiltSensorMoreTv:
                TiltSensorActivityOld.newInstance(getActivity(), mCamId);
                break;
            case R.id.tiltSensorHeightMoreTv:
                TiltSensorActivityOld.newInstance(getActivity(), mCamId);
                break;
        }
    }
}
