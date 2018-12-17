package com.hc.hmsmoblie.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.classic.adapter.BaseAdapterHelper;
import com.classic.adapter.CommonAdapter;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.adapter.AllDeriverAdapter;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.bean.domain.SendMsgBean;
import com.hc.hmsmoblie.bean.domain.TiltSensorAlarmBean;
import com.hc.hmsmoblie.bean.domain.TiltSensorSettingBean;
import com.hc.hmsmoblie.bean.domain.TiltSensorSettingPostBean;
import com.hc.hmsmoblie.bean.json.TiltSensorAllJson;
import com.hc.hmsmoblie.bean.json.TiltSensorParaJson;
import com.hc.hmsmoblie.bean.json.TiltSensorSettingJson;
import com.hc.hmsmoblie.bean.json.TiltSensorStateJson;
import com.hc.hmsmoblie.bean.type.TiltSensorParaState;
import com.hc.hmsmoblie.mvp.contact.TiltSensorActivityC;
import com.hc.hmsmoblie.mvp.presenter.TiltSensorActivityP;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.hc.hmsmoblie.utils.BatAndSignalUtil;
import com.hc.hmsmoblie.utils.TiltSensorStateUtils;
import com.hc.hmsmoblie.utils.chart.ChartLineView;
import com.hc.hmsmoblie.utils.chart.ChartUtils;
import com.hc.hmsmoblie.widget.AlarmDialog;
import com.hc.hmsmoblie.widget.CommonDialog;
import com.hc.hmsmoblie.widget.DeviceStateDialog;
import com.hc.hmsmoblie.widget.TitleSenorSettingDialog;

import org.json.simple.JSONObject;

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
    @BindView(R.id.tiltSensorAlarmDistanceXIv)
    ImageView mAlarmDistanceXIv;
    @BindView(R.id.tiltSensorAlarmDistanceXTv)
    TextView mAlarmDistanceXTv;
    @BindView(R.id.tiltSensorAlarmDistanceYIv)
    ImageView mAlarmDistanceYIv;
    @BindView(R.id.tiltSensorAlarmDistanceYTv)
    TextView mAlarmDistanceYTv;
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
    ImageView mSwitchIv;
    @BindView(R.id.tiltSensorLeftIv)
    ImageView mLeftIv;
    @BindView(R.id.tiltSensorRightIv)
    ImageView mRightIv;
    @BindView(R.id.tiltSensorLineChart)
    ChartLineView mLineChart;
    @BindView(R.id.tiltSensorHeightLineChart)
    ChartLineView mHeightLineChart;
    @BindView(R.id.tiltSensorDistanceLineChart)
    ChartLineView mDistanceLineChart;
    @BindView(R.id.tiltSensorDeflectionTv)
    TextView mDeflectionTv;

    private String mCamId;
    @TiltSensorParaState
    private String mParaState = TiltSensorParaState.UNKNOWN;//监测点开启或者关闭状态
    private String mParaID;
    private String mSeq;//
    private List<TiltSensorParaJson.ListBean> mParaList = new ArrayList<>();//点位列表数据
    private CommonAdapter<TiltSensorParaJson.ListBean> mParamTitleAdapter;
    private int mRefreshTime = 15;//刷新数据的时间间隔
    private Disposable mDisposableRefresh;//用于循环刷新的暂停和取消
    private boolean mIsParaInit = false;//监测点列表数据是否完成初始化
    private NetObserver<HttpResponse<TiltSensorAllJson>> responseNetObserver;//用于取消前一个刷新的网络请求
    private TiltSensorAlarmBean mTiltSensorAlarmBean;//手机端临时预警设置数据
    private TiltSensorSettingBean mTiltSensorSettingBean;//倾角设备配置属性
    TitleSenorSettingDialog TitleSenorSettingDialog;//配置倾角数据窗口
    ArrayList<SendMsgBean> isSendList = new ArrayList<>();//配置倾角数据是否成功
    int isSendNum = 0;//选中的倾角设备个叔

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
        mTiltSensorSettingBean = new TiltSensorSettingBean();
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
                if (mParaList.size() <= 0) {
                    return;
                }
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
        mParaList.get(mParamTitleSp.getSelectedItemPosition()).setStates(mParaState);
        //因为开关为操作按钮，所以显示图标跟状态相反
        if (mParaState.equals(TiltSensorParaState.OPEN)) {
            mSwitchIv.setImageResource(R.drawable.ic_close);
        } else if (mParaState.equals(TiltSensorParaState.UNKNOWN)) {
            mSwitchIv.setImageResource(R.drawable.ic_open233);
        } else {
            mSwitchIv.setImageResource(R.drawable.ic_open);
        }
    }

    /**
     * 后台刷新数据
     */
    private synchronized void loopRefreshData() {
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
    private List<TiltSensorAllJson.DatTiltSensorListTimeBean> mDataChartHeight;//沉降图表数据
    private List<TiltSensorAllJson.DatTiltSensorListStageBean> mDataChartDistance;//位移图表数据
    private int[] COLOR;

    @Override
    public void onGetTitAllSuccess(TiltSensorAllJson tiltSensorJson) {
        mData = null;
        mDataChartAngle = null;
        mDataChartHeight = null;
        mDataChartDistance = null;
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
            if (tiltSensorJson.getDat_TiltSensorList_Stage() != null && tiltSensorJson.getDat_TiltSensorList_Stage().size() > 0) {
                mDataChartDistance = tiltSensorJson.getDat_TiltSensorList_Stage();
            }
        }
        refreshDataUI();
        refreshLineAngle();
        refreshLineHeight();
        refreshDistance();
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
        ChartUtils.setData(getActivity(), mHeightLineChart, new String[]{"沉降"}, COLOR, dataMarkerX, dataX, Arrays.asList(dataY), Arrays.asList("mm"));
    }

    private void refreshDistance() {
        if (mDataChartDistance == null) {
            mDistanceLineChart.setNoDataText(this.getString(R.string.view_empty));
            mDistanceLineChart.clear();
            return;
        }
        List<String> dataMarkerX = new ArrayList<>();//x轴上面的数据
        List<String> dataX = new ArrayList<>();//x轴上面的数据
        List<List<Double>> dataY = new ArrayList<>();//y轴上面的数据
        dataY.add(new ArrayList<>());
        dataY.add(new ArrayList<>());
        dataY.add(new ArrayList<>());
        for (int i = 0; i < mDataChartDistance.size(); i++) {
            String x = mDataChartDistance.get(i).getCreateTime();
            dataMarkerX.add(x);
            dataX.add(x.substring(x.length() - 9));
            dataY.get(0).add(mDataChartDistance.get(i).getObdFirstOldx());
            dataY.get(1).add(mDataChartDistance.get(i).getObdFirstOldy());
            dataY.get(2).add(mDataChartDistance.get(i).getObdFirstOldz());
        }
        ChartUtils.setData(getActivity(), mDistanceLineChart, new String[]{"X轴位移", "y轴位移", "x轴位移"}, COLOR, dataMarkerX, dataX, dataY, Arrays.asList("mm", "mm", "mm"));
    }

    private void refreshDataUI() {
        if (mData == null) {
            mTimeTv.setText(getResources().getString(R.string.empty_time));
            //电量
            mElectricityIv.setImageResource(R.drawable.ic_battery233);
            //信号强度
            mSignalIv.setImageResource(R.drawable.ic_signl233);
            mParaState = TiltSensorParaState.UNKNOWN;
            refreshState();
            mXTv.setText("X轴：-");
            mXIv.setVisibility(View.INVISIBLE);
            mYTv.setText("Y轴：-");
            mYIv.setVisibility(View.INVISIBLE);
            mHeightTv.setText("高度：-");
            mHeightIv.setVisibility(View.INVISIBLE);
            mDeflectionTv.setText("挠度：-");
            mAlarmXTv.setText("X轴：-");
            mAlarmXIv.setVisibility(View.INVISIBLE);
            mAlarmYTv.setText("Y轴：-");
            mAlarmYIv.setVisibility(View.INVISIBLE);
            mAlarmHeightTv.setText("沉降：-");
            mAlarmHeightIv.setVisibility(View.INVISIBLE);
            mAlarmDistanceXTv.setText("X轴位移：-");
            mAlarmDistanceXIv.setVisibility(View.INVISIBLE);
            mAlarmDistanceYTv.setText("Y轴位移：-");
            mAlarmDistanceYIv.setVisibility(View.INVISIBLE);
            mAlarmSpaceTv.setText("空间位移：-");
            mAlarmSpaceIv.setVisibility(View.INVISIBLE);
            mAlarmHFLeftTv.setText("左端浮动：-");
            mAlarmHFLeftIv.setVisibility(View.INVISIBLE);
            mAlarmHFRightTv.setText("右端浮动：-");
            mAlarmHFRightIv.setVisibility(View.INVISIBLE);

        } else {
            mTimeTv.setText("时间：" + mData.getCreateTime());
            //设置监测点开关状态
            if (mData.getStates().equals(TiltSensorParaState.UNKNOWN)) {
                mParaState = TiltSensorParaState.UNKNOWN;
            } else if (mData.getStates().equals(TiltSensorParaState.OPEN)) {
                mParaState = TiltSensorParaState.OPEN;
            } else {
                mParaState = TiltSensorParaState.CLOSE;
            }
            refreshState();
            //电量
            mElectricityIv.setImageResource(BatAndSignalUtil.batLevel(mData.getVo()));
            //信号强度
            mSignalIv.setImageResource(BatAndSignalUtil.signalLevel(mData.getCam_singnal()));

//            mXTv.setText("X轴：" + TiltSensorStateUtils.formatX(mData.getOx()) + FormatUtils.stripTrailingZeros(Math.abs(mData.getOx())) + "°");
            mXTv.setText("X轴：" + TiltSensorStateUtils.formatX(mData.getOx()) + TiltSensorStateUtils.getFormAdsData(mData.getOx(), "°"));
            if (mData.getOx() == 0 && mData.getOldx() == 0) {
                mXIv.setVisibility(View.INVISIBLE);
            } else {
                mXIv.setImageResource(TiltSensorStateUtils.getState(mData.getOldx()));
                mXIv.setVisibility(View.VISIBLE);
            }


//            mYTv.setText("Y轴：" + TiltSensorStateUtils.formatY(mData.getOy()) + FormatUtils.stripTrailingZeros(Math.abs(mData.getOy())) + "°");
            mYTv.setText("Y轴：" + TiltSensorStateUtils.formatY(mData.getOy()) + TiltSensorStateUtils.getFormAdsData(mData.getOy(), "°"));

            if (mData.getOy() == 0 && mData.getOldy() == 0) {
                mYIv.setVisibility(View.INVISIBLE);
            } else {
                mYIv.setImageResource(TiltSensorStateUtils.getState(mData.getOldy()));
                mYIv.setVisibility(View.VISIBLE);
            }
//            mHeightTv.setText("高度：" + FormatUtils.stripTrailingZeros(mData.getObd()) + "mm");
            mHeightTv.setText("高度：" + TiltSensorStateUtils.getFormData(mData.getObd(), "mm"));

            if (mData.getObd() == 0 && mData.getCdObd() == 0) {
                mHeightIv.setVisibility(View.INVISIBLE);
            } else {
                mHeightIv.setImageResource(TiltSensorStateUtils.getState(mData.getCdObd()));
                mHeightIv.setVisibility(View.VISIBLE);
            }
            mDeflectionTv.setText("挠度：" + TiltSensorStateUtils.getFormData(mData.getDeflection(), "mm"));
//            mAlarmXIv.setVisibility(isShowAlarm(mTiltSensorAlarmBean.isOpen(), mData.getFirstOldx(), mTiltSensorAlarmBean.getAxisX()));
            mAlarmXIv.setVisibility(isShowAlarm(mTiltSensorAlarmBean.isOpen(), mData.getFirstOldx(), mTiltSensorAlarmBean.getAxisX()));
//            mAlarmXTv.setText("X轴：" + TiltSensorStateUtils.formatX(mData.getFirstOldx()) + FormatUtils.stripTrailingZeros(Math.abs(mData.getFirstOldx())) + "°");
            mAlarmXTv.setText("X轴：" + TiltSensorStateUtils.formatX(mData.getFirstOldx()) + TiltSensorStateUtils.getFormAdsData(mData.getFirstOldx(), "°"));

            mAlarmYIv.setVisibility(isShowAlarm(mTiltSensorAlarmBean.isOpen(), mData.getFirstOldy(), mTiltSensorAlarmBean.getAxisY()));
//            mAlarmYTv.setText("Y轴：" + TiltSensorStateUtils.formatY(mData.getFirstOldy()) + FormatUtils.stripTrailingZeros(Math.abs(mData.getFirstOldy())) + "°");
            mAlarmYTv.setText("Y轴：" + TiltSensorStateUtils.formatY(mData.getFirstOldy()) + TiltSensorStateUtils.getFormAdsData(mData.getFirstOldy(), "°"));

            //沉降
            mAlarmHeightIv.setVisibility(isShowAlarm(mTiltSensorAlarmBean.isOpen(), mData.getObdFirstOldz(), mTiltSensorAlarmBean.getSettlement()));
//            mAlarmHeightTv.setText("沉降：" + TiltSensorStateUtils.formatSettlement(mData.getCdObdAdd()) + FormatUtils.stripTrailingZeros(Math.abs(mData.getCdObdAdd())) + "mm");
            mAlarmHeightTv.setText("沉降：" + TiltSensorStateUtils.formatSpaceZ(mData.getObdFirstOldz()) + TiltSensorStateUtils.getFormAdsData(mData.getObdFirstOldz(), "mm"));
            //空间X轴位移
            mAlarmDistanceXIv.setVisibility(isShowAlarm(mTiltSensorAlarmBean.isOpen(), mData.getObdFirstOldx(), mTiltSensorAlarmBean.getSettlement()));
            mAlarmDistanceXTv.setText("X轴位移：" + TiltSensorStateUtils.formatSpaceX(mData.getObdFirstOldx()) + TiltSensorStateUtils.getFormAdsData(mData.getObdFirstOldx(), "mm"));
            //空间Y轴位移
            mAlarmDistanceYIv.setVisibility(isShowAlarm(mTiltSensorAlarmBean.isOpen(), mData.getObdFirstOldy(), mTiltSensorAlarmBean.getSettlement()));
            mAlarmDistanceYTv.setText("Y轴位移：" + TiltSensorStateUtils.formatSpaceY(mData.getObdFirstOldy()) + TiltSensorStateUtils.getFormAdsData(mData.getObdFirstOldy(), "mm"));

            mAlarmSpaceIv.setVisibility(isShowAlarm(mTiltSensorAlarmBean.isOpen(), mData.getHightObdAdd(), mTiltSensorAlarmBean.getSpace()));
//            mAlarmSpaceTv.setText("空间位移：" + FormatUtils.stripTrailingZeros(mData.getHightObdAdd()) + "mm");
            mAlarmSpaceTv.setText("空间位移：" + TiltSensorStateUtils.getFormData(mData.getHightObdAdd(), "mm"));

            mAlarmHFLeftIv.setVisibility(isShowAlarm(mTiltSensorAlarmBean.isOpen(), mData.getFloatObdLeft(), mTiltSensorAlarmBean.getHorizontalFloatingLeft()));
//            mAlarmHFLeftTv.setText("左端浮动：" + TiltSensorStateUtils.formatSettlement(mData.getFloatObdLeft()) + FormatUtils.stripTrailingZeros(Math.abs(mData.getFloatObdLeft())) + "mm");
            mAlarmHFLeftTv.setText("左端浮动：" + TiltSensorStateUtils.formatSettlement(mData.getFloatObdLeft()) + TiltSensorStateUtils.getFormAdsData(mData.getFloatObdLeft(), "mm"));

            mAlarmHFRightIv.setVisibility(isShowAlarm(mTiltSensorAlarmBean.isOpen(), mData.getFloatObdRight(), mTiltSensorAlarmBean.getHorizontalFloatingRight()));
//            mAlarmHFRightTv.setText("右端浮动：" + TiltSensorStateUtils.formatSettlement(mData.getFloatObdRight()) + FormatUtils.stripTrailingZeros(Math.abs(mData.getFloatObdRight())) + "mm");
            mAlarmHFRightTv.setText("右端浮动：" + TiltSensorStateUtils.formatSettlement(mData.getFloatObdRight()) + TiltSensorStateUtils.getFormAdsData(mData.getFloatObdRight(), "mm"));
        }
    }

    @Override
    public void onGetTitAllFail(String tiltSensorJson) {
        mData = null;
        mDataChartAngle = null;
        mDataChartHeight = null;
        refreshDataUI();
        refreshLineAngle();
        refreshLineHeight();
    }

    @Override
    public void onSetAllMessageSuccess(@TiltSensorParaState String isOnOff) {//监测点开关回调
        mParaState = isOnOff;
        if (isOnOff.equals(TiltSensorParaState.OPEN)) {
            showMsg("开启监测点成功");
        } else if (isOnOff.equals(TiltSensorParaState.CLOSE)) {
            showMsg("关闭监测点成功");
        }
        mParaList.get(mParamTitleSp.getSelectedItemPosition()).setStates(mParaState);
        refreshState();
    }

    @Override
    public void onGetDeviceStateSuccess(TiltSensorStateJson tiltSensorStateJson) {//获取设备状态（华为接口）
        DeviceStateDialog.newInstance(getActivity())
                .setData(tiltSensorStateJson)
                .show();
    }

    @Override
    public void onGetDeviceSettingSuccess(TiltSensorStateJson tiltSensorStateJson) {
        if (tiltSensorStateJson.getTotalCount() != 0) {
            List<TiltSensorStateJson.DevicesBean.ServicesBean> servicesBean = tiltSensorStateJson.getDevices().get(0).getServices();
            for (TiltSensorStateJson.DevicesBean.ServicesBean servicesBeans : servicesBean) {
                if (servicesBeans.getServiceId().equals("Setting")) {
                    mTiltSensorSettingBean.setAxisX(servicesBeans.getData().getSlope_Thres_X() / 10000.0);
                    mTiltSensorSettingBean.setAxisY(servicesBeans.getData().getSlope_Thres_Y() / 10000.0);
                    mTiltSensorSettingBean.setRptPer(servicesBeans.getData().getRptPer());
                    mTiltSensorSettingBean.setRptPer_warn(servicesBeans.getData().getRptPer_warn());
                    mTiltSensorSettingBean.setCacheTime(2880);
                }
                if (servicesBeans.getServiceId().equals("CurVal")) {
                    if (servicesBeans.getData().getState() != 0 && Integer.toBinaryString(servicesBeans.getData().getState()).length() >= 7) {
                        String boolHeadOpen = Integer.toBinaryString(servicesBeans.getData().getState()).substring(0, 1);
                        if (boolHeadOpen.equals("0")) {
                            mTiltSensorSettingBean.setDevState(0);
                        } else if (boolHeadOpen.equals("1")) {
                            mTiltSensorSettingBean.setDevState(1);
                        }
                    } else {
                        mTiltSensorSettingBean.setDevState(0);
                    }
                }
            }
            TitleSenorSettingDialog = new TitleSenorSettingDialog(getActivity());
            TitleSenorSettingDialog.setAlarmData(mTiltSensorSettingBean)
                    .setLeftClick(tiltSensorAlarmBean -> {
                        if (tiltSensorAlarmBean != null) {
                            setSendData(tiltSensorAlarmBean, true);

                        } else {
                            showToast("配置的数据不能为空！");
                        }
                    })
                    .setMiddleClick(tiltSensorAlarmBean -> {
                        if (tiltSensorAlarmBean != null) {
                            setSendData(tiltSensorAlarmBean, false);
                        } else {
                            showToast("配置的数据不能为空！");
                        }
                    })
                    .show();
        } else {
            showToast("暂无数据！");
        }
    }

    @Override
    public void setIotDeviceInfoSuccess(boolean xy, boolean reportTime, boolean Switch) {
        if (xy && reportTime && Switch) {
            showToast("配置已发送");
        } else {
            StringBuilder toastStr = new StringBuilder();
            if (!xy) {
                toastStr.append("x,y轴的倾角差阈值配置失败，");
            }
            if (!reportTime) {
                toastStr.append("数据上报时长配置失败，");
            }
            if (!Switch) {
                toastStr.append("传感器状态更改失败，");
            }
            showToast(toastStr.append("请重试！").toString());

        }
    }

    @Override
    public void setAllIotDeviceInfoSuccess(TiltSensorSettingJson tiltSensorSettingJson1, TiltSensorSettingJson tiltSensorSettingJson2
            , TiltSensorSettingJson tiltSensorSettingJson3, String seq) {
        boolean isXYSend = tiltSensorSettingJson1.getStatus() != null && tiltSensorSettingJson1.getStatus().equals("PENDING");
        boolean isReportTimeSend = tiltSensorSettingJson1.getStatus() != null && tiltSensorSettingJson1.getStatus().equals("PENDING");
        boolean isSwitchSend = tiltSensorSettingJson1.getStatus() != null && tiltSensorSettingJson1.getStatus().equals("PENDING");
        setOpenAndCloseSuccess(new SendMsgBean(isXYSend, isReportTimeSend, isSwitchSend, seq));

    }

    private void titleSensorAllDialogShow(TiltSensorSettingPostBean settingPostBean_XY, TiltSensorSettingPostBean setting_ReportTime
            , TiltSensorSettingPostBean setting_Switch) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CommonDialogStyle);
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.dip_title_sensor_all, null);
        TextView btn_open = (TextView) view.findViewById(R.id.alarmOpenTv);
        TextView btn_cancel = (TextView) view.findViewById(R.id.alarmRightTv);

        RecyclerView rv_Deriver = view.findViewById(R.id.alarmRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_Deriver.setLayoutManager(layoutManager);
        AllDeriverAdapter allDeriverAdapter = new AllDeriverAdapter(R.layout.item_check_box, mParaList);
        rv_Deriver.setAdapter(allDeriverAdapter);
        view.findViewById(R.id.itemDeriverCB).setOnClickListener(v -> allDeriverAdapter.checkAll(((CheckBox) v).isChecked()));
        final Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setContentView(view);//自定义布局应该在这里添加，要在dialog.show()的后面
        //dialog.getWindow().setGravity(Gravity.CENTER);//可以设置显示的位置
        btn_open.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                List<Boolean> checkedList = allDeriverAdapter.getCheckeds();
                List<Observable<TiltSensorSettingJson>> observableList = new ArrayList<>();
                Observable<TiltSensorSettingJson> observable;
                isSendList.clear();
                isSendNum = 0;
                for (int i = 0; i < checkedList.size(); i++) {
                    if (checkedList.get(i)) {
                        isSendNum++;
                    }
                }
                if (isSendNum == 0) {
                    showToast("未选中设备!");
                    return;
                }
                showLoading("正在加载中...");
                for (int i = 0; i < checkedList.size(); i++) {
                    if (checkedList.get(i)) {
                        settingPostBean_XY.setDeviceId(mParaList.get(i).getSeq());
                        setting_ReportTime.setDeviceId(mParaList.get(i).getSeq());
                        setting_Switch.setDeviceId(mParaList.get(i).getSeq());
                        mPresenter.setAllIotDeviceInfo(settingPostBean_XY, setting_ReportTime, setting_Switch, mParaList.get(i).getSeq());
                    }
                }
                TitleSenorSettingDialog.dismiss();
                dialog.dismiss();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    public synchronized void setOpenAndCloseSuccess(SendMsgBean sendMsgBean) {
        isSendList.add(sendMsgBean);
        if (isSendList.size() == isSendNum) {
            StringBuilder srt = new StringBuilder("配置已发送\n");
            for (int i = 0; i < isSendList.size(); i++) {
                if (isSendList.get(i).isXYSend() && isSendList.get(i).isReportTimeSend() && isSendList.get(i).isSwitchSend()) {
                    continue;
                } else {
                    srt.append("设备:" + isSendList.get(i).getSeq());
                }
                if (!isSendList.get(i).isXYSend()) {
                    srt.append("x,y轴的倾角差阈值,");
                }
                if (!isSendList.get(i).isReportTimeSend()) {
                    srt.append("数据上报时长,");
                }
                if (!isSendList.get(i).isSwitchSend()) {
                    srt.append("传感器状态更改,");
                }
                srt.append("配置失败。\n");
            }
            hideLoading();
            CommonDialog.newInstanceSingle(getActivity())
                    .setTitle("提示")
                    .setMsg(srt.toString())
                    .show();
        }
    }

    private int isShowAlarm(boolean isOpen, double value, double alarmValue) {
        if (isOpen && Math.abs(value) > Math.abs(alarmValue))
            return View.VISIBLE;
        else
            return View.INVISIBLE;
    }

    public void setSendData(TiltSensorSettingBean tiltSensorAlarmBean, boolean type) {
        //设置xy阈值
        JSONObject result_XY = new JSONObject();

        result_XY.put("Slope_Thres_x", tiltSensorAlarmBean.getAxisX());
        result_XY.put("Slope_Thres_y", tiltSensorAlarmBean.getAxisY());
        TiltSensorSettingPostBean settingPostBean_XY = new TiltSensorSettingPostBean(mSeq, "Setting"
                , "SET_SLOPE_THRES", result_XY.toJSONString(), tiltSensorAlarmBean.getCacheTime());
        //设置上报时间
        JSONObject result_Report_Time = new JSONObject();
        result_Report_Time.put("RptPer", tiltSensorAlarmBean.getRptPer());
        result_Report_Time.put("RptPer_warn", tiltSensorAlarmBean.getRptPer_warn());
        TiltSensorSettingPostBean setting_ReportTime = new TiltSensorSettingPostBean(mSeq, "Setting"
                , "SET_REPORT_PER", result_Report_Time.toJSONString(), tiltSensorAlarmBean.getCacheTime());

        //设置设备开关
        JSONObject result_InitVal = new JSONObject();
        switch (tiltSensorAlarmBean.getDevState()) {
            case 0:
                result_InitVal.put("cmd", 0);
                break;
            case 1:
                result_InitVal.put("cmd", 128);
                break;
            case 2:
                result_InitVal.put("cmd", 1);
                break;

        }
        TiltSensorSettingPostBean setting_Switch = new TiltSensorSettingPostBean(mSeq, "InitVal"
                , "OPERAT_CMD", result_InitVal.toJSONString(), tiltSensorAlarmBean.getCacheTime());

        if (type) {
            mPresenter.setIotDeviceInfo(settingPostBean_XY, setting_ReportTime, setting_Switch);
        } else {
            titleSensorAllDialogShow(settingPostBean_XY, setting_ReportTime, setting_Switch);
        }
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
            R.id.tiltSensorRightIv, R.id.tiltSensorMoreTv, R.id.tiltSensorHeightMoreTv, R.id.tiltSensorDistanceMoreTv, R.id.tiltSensorAlarmIv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tiltSensorSettingIv:
                mPresenter.setTiltSensorState(mSeq);
                break;
            case R.id.tiltSensorSatesIv:
                mPresenter.getTiltSensorState(mSeq);
                break;
            case R.id.tiltSensorSwitchIv:
                if (mParaState.equals(TiltSensorParaState.UNKNOWN)) {
                    showMsg("没有数据");
                } else {
                    CommonDialog.newInstance(getActivity())
                            .setMsg("是否" + (mParaState.equals(TiltSensorParaState.OPEN) ? "关闭" :"开启" ) + "监测点")
                            .setLeftClick(v -> {
                                if (mParaState.equals(TiltSensorParaState.OPEN)) {
                                    mPresenter.setAllMessage(mParaID, mSeq, TiltSensorParaState.CLOSE + "");
                                } else {
                                    mPresenter.setAllMessage(mParaID, mSeq, TiltSensorParaState.OPEN + "");
                                }
                            }).show();
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
                    mTiltSensorAlarmBean.setSettlement(mData.getObdFirstOldz());
                    mTiltSensorAlarmBean.setDistanceX(mData.getObdFirstOldx());
                    mTiltSensorAlarmBean.setDistanceY(mData.getObdFirstOldy());
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
                String time1 = mTimeTv.getText().toString();
                if (TextUtils.isEmpty(time1) || time1.equals(getResources().getString(R.string.empty_time))) {
                    TiltSensorChartActivity.newInstance(getActivity(), mCamId, mParaList, mParamTitleSp.getSelectedItemPosition(), false, "");
                } else {
                    TiltSensorChartActivity.newInstance(getActivity(), mCamId, mParaList, mParamTitleSp.getSelectedItemPosition(), false, time1.substring(3, 13));
                }
                break;
            case R.id.tiltSensorDistanceMoreTv:
            case R.id.tiltSensorHeightMoreTv:
                String time2 = mTimeTv.getText().toString();
                if (TextUtils.isEmpty(time2) || time2.equals(getResources().getString(R.string.empty_time))) {
                    TiltSensorChartActivity.newInstance(getActivity(), mCamId, mParaList, mParamTitleSp.getSelectedItemPosition(), true, "");
                } else {
                    TiltSensorChartActivity.newInstance(getActivity(), mCamId, mParaList, mParamTitleSp.getSelectedItemPosition(), true, time2.substring(3, 13));
                }
                break;

        }
    }
}
