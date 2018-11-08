package com.hc.hmsmoblie.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.classic.adapter.BaseAdapterHelper;
import com.classic.adapter.CommonAdapter;
import com.classic.adapter.CommonRecyclerAdapter;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.bean.domain.DipRealBean;
import com.hc.hmsmoblie.bean.domain.TiltSensorAlarmBean;
import com.hc.hmsmoblie.bean.json.SensorLogJson;
import com.hc.hmsmoblie.bean.json.SetAllMessageJson;
import com.hc.hmsmoblie.bean.json.TiltSensorParaJson;
import com.hc.hmsmoblie.bean.json.TiltSensorStateJson;
import com.hc.hmsmoblie.bean.type.TiltSensorParaState;
import com.hc.hmsmoblie.mvp.contact.DipRealTimeDataC;
import com.hc.hmsmoblie.mvp.presenter.DipRealTimeDataP;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.hc.hmsmoblie.utils.FormatUtils;
import com.hc.hmsmoblie.widget.AlarmDialog;
import com.hc.hmsmoblie.widget.CommonDialog;
import com.yc.yclibrary.exception.ApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 倾角页面
 */

public class DipRealTimeDataActivity extends BaseMvpActivity<DipRealTimeDataP> implements DipRealTimeDataC.V {
    @BindView(R.id.ivActionbarLeft)
    ImageView ivActionbarLeft;
    @BindView(R.id.tvActionbarLeft)
    TextView tvActionbarLeft;
    @BindView(R.id.tvActionbarMid)
    TextView tvActionbarMid;
    @BindView(R.id.ivActionbarRight)
    ImageView ivActionbarRight;
    @BindView(R.id.tvActionbarRight)
    TextView tvActionbarRight;
    @BindView(R.id.LlActionbarRight)
    LinearLayout LlActionbarRight;
    @BindView(R.id.tiltSensorTypeSp)
    Spinner tiltSensorTypeSp;
    @BindView(R.id.tvCreateTime)
    TextView tvCreateTime;
    @BindView(R.id.tvRefreshTime)
    TextView tvRefreshTime;
    @BindView(R.id.tvAlarm)
    TextView tvAlarm;
    @BindView(R.id.tvTitleSensorCheck)
    TextView tvTitleSensorCheck;
    @BindView(R.id.tvTitleSensorSetting)
    TextView tvTitleSensorSetting;
    @BindView(R.id.rvData)
    RecyclerView rvData;
    @BindView(R.id.tiltSensorStateTv)
    TextView tiltSensorStateTv;
    private String mCamId;
    private String mSeq;
    private String mParaID;
    private List<TiltSensorParaJson.ListBean> mParaList = new ArrayList<>();
    private CommonAdapter<String> mSpAdapter;
    Disposable mDisposableAlarm;
    NetObserver<HttpResponse<SensorLogJson>> responseNetObserver;
    CommonRecyclerAdapter<DipRealBean> adapter;
    private boolean mIsInit = false;
    private TiltSensorAlarmBean mTiltSensorAlarmBean;
    private String[] mDipReal;
    private String[] mDipRealUnit;
    private SensorLogJson.ListBean mSensorLogListBean;
    private @TiltSensorParaState
    String mParaState = "1";//监测点的状态
    private String speedNum = "15";
    private static final String CAM_ID = "cam_id";

    public static void newInstance(Activity activity, String camID) {
        Intent intent = new Intent(activity, DipRealTimeDataActivity.class);
        intent.putExtra(CAM_ID, camID);
        activity.startActivity(intent);
    }

    @Override
    protected DipRealTimeDataP loadPresenter() {
        return new DipRealTimeDataP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dip_real_time_data_activity;
    }

    @Override
    protected void initView(Bundle bundle) {
        setToolBar("倾角数据");
        mTiltSensorAlarmBean = new TiltSensorAlarmBean();
        mDipReal = getResources().getStringArray(R.array.dipRealData);
        mDipRealUnit = getResources().getStringArray(R.array.dipRealDataUnit);
        mCamId = getIntent().getStringExtra(CAM_ID);
        tvRefreshTime.setText(getResources().getString(R.string.refreshTime) + "15s");
//        mCamId = "1014603";
        initSpinner();
        adapter = new CommonRecyclerAdapter<DipRealBean>(getActivity(), R.layout.dip_real_item) {
            @Override
            public void onUpdate(BaseAdapterHelper helper, DipRealBean item, int position) {
                if (item.isEmpty()) {
                    helper.setText(R.id.itemDipRealTv, mDipReal[position] + "：-");
                } else {
                    if (item.isAlarm() && mTiltSensorAlarmBean.isOpen()) {//超过阈值且开启告警
                        helper.setTextColor(R.id.itemDipRealTv, getResources().getColor(R.color.alarmColorBad));
                    } else {
                        helper.setTextColor(R.id.itemDipRealTv, getResources().getColor(R.color.alarmColorGray));
                    }
                    helper.setText(R.id.itemDipRealTv, mDipReal[position] + "：" + FormatUtils.stripTrailingZeros(item.getData()) + mDipRealUnit[position] + item.getSuffix());
                }
            }
        };
        rvData.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvData.setAdapter(adapter);
        getParaIds();
    }

    public void initSpinner() {

        tiltSensorTypeSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                mParaID = mParaList.get(position).getParaID() + "";
                mSeq = mParaList.get(position).getSeq();
                mParaState = mParaList.get(position).getStates();
                refreshState();
                getLoopRequest(Integer.parseInt(speedNum));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    /**
     * 刷新监测点状态
     */
    private void refreshState() {
        if (mParaState.equals(TiltSensorParaState.OPEN)) {
            tiltSensorStateTv.setText("开");
        } else {
            tiltSensorStateTv.setText("关");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getLoopRequest(Integer.parseInt(speedNum));
    }

    private void getLoopRequest(int period) {
        if (!mIsInit) return;
        if (TextUtils.isEmpty(mParaID)) {
            showToast("paraID为空");
            return;
        }
        if (mDisposableAlarm != null)
            mDisposableAlarm.dispose();
        //initialDelay第一次执行间隔 period之后执行间隔
        mDisposableAlarm = Observable.interval(0, period, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    if (responseNetObserver != null)
                        responseNetObserver.cancel();
                    responseNetObserver = new NetObserver<HttpResponse<SensorLogJson>>() {
                        @Override
                        public void onSuccess(HttpResponse<SensorLogJson> sensorLogJson) {
                            hideLoading();
                            onGetTiltSensorLogSuccess(sensorLogJson.getData());
                        }

                        @Override
                        public void onFail(ApiException msg) {
                            hideLoading();
                            onGetTiltSensorLogFail(msg.getMessage());
                        }
                    };
                    if (aLong == 0) {
                        mPresenter.getTiltSensorLog(true, mCamId, mParaID, 1, 10, "", "", responseNetObserver);
                    } else {
                        mPresenter.getTiltSensorLog(false, mCamId, mParaID, 1, 10, "", "", responseNetObserver);
                    }
                });

    }

    private void getParaIds() {
        mPresenter.getGetTiltSensorPara(mCamId);
    }


    private void speedDialogShow() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View v = inflater.inflate(R.layout.dialog_seekbar_refech_time, null);
        Button btn_sure = (Button) v.findViewById(R.id.dialog_btn_sure);
        Button btn_cancel = (Button) v.findViewById(R.id.dialog_btn_cancel);
        SeekBar sbSpeedDialog = (SeekBar) v.findViewById(R.id.sb_Speed_Dialog);
        sbSpeedDialog.setProgress(Integer.parseInt(speedNum));
        TextView tvSpeedDialog = (TextView) v.findViewById(R.id.tv_Speed_Dialog);
        tvSpeedDialog.setText(getResources().getString(R.string.refreshTime) + (sbSpeedDialog.getProgress()) + "s");
        //builer.setView(v);//这里如果使用builer.setView(v)，自定义布局只会覆盖title和button之间的那部分
        final Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setContentView(v);//自定义布局应该在这里添加，要在dialog.show()的后面
        //dialog.getWindow().setGravity(Gravity.CENTER);//可以设置显示的位置
        btn_sure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                speedNum = sbSpeedDialog.getProgress() + 1 + "";
                getLoopRequest(Integer.parseInt(speedNum) + 1);
                tvRefreshTime.setText(getResources().getString(R.string.refreshTime) + speedNum + "s");
                dialog.dismiss();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialog.dismiss();

            }
        });
        sbSpeedDialog.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvSpeedDialog.setText(getResources().getString(R.string.refreshTime) + (sbSpeedDialog.getProgress() + 1) + "s");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposableAlarm != null) {
            mDisposableAlarm.dispose();
            mDisposableAlarm = null;
            mTiltSensorAlarmBean = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mDisposableAlarm != null) {
            mDisposableAlarm.dispose();
            mDisposableAlarm = null;
        }
    }

    @Override
    public void onGetGetTiltSensorParaSuccess(TiltSensorParaJson dataBean) {
        mIsInit = true;
        if (dataBean == null || dataBean.getList() == null || dataBean.getList().isEmpty()) {
            onGetGetTiltSensorParaFail("没有数据");
            mSeq = "";
        } else {
            mParaList = dataBean.getList();
            List<String> dataList = new ArrayList<String>();
            for (TiltSensorParaJson.ListBean jsonBean : mParaList) {
                dataList.add(jsonBean.getParaName());
            }
            mSpAdapter = new CommonAdapter<String>(getActivity(), R.layout.item_common) {
                @Override
                public void onUpdate(BaseAdapterHelper helper, String item, int position) {
                    helper.setText(R.id.itemCommonTv, item);
                }
            };
            mSpAdapter.addAll(dataList);
            tiltSensorTypeSp.setAdapter(mSpAdapter);
            tiltSensorTypeSp.setSelection(0);
            mParaID = dataList.get(0);
            mSeq = mParaList.get(0).getSeq();
            mParaState = mParaList.get(0).getStates();
            getLoopRequest(Integer.parseInt(speedNum));
        }
    }

    @Override
    public void onGetGetTiltSensorParaFail(String msg) {
        CommonDialog.newInstanceSingle(getActivity())
                .setTitle("提示")
                .setMsg("此设备暂无倾角数据！")
                .setSingleBtnText("确定")
                .setSingleClick(v -> finish())
                .show();
    }


    @Override
    public void onGetTiltSensorLogSuccess(SensorLogJson dataBean) {
        adapter.clear();
        //数据为空时候
        if (dataBean == null || dataBean.getList() == null || dataBean.getList().size() <= 0) {
            for (int i = 0; i < mDipReal.length; i++) {
                adapter.add(new DipRealBean(true));
            }
            tvCreateTime.setText("-");
            mSensorLogListBean = null;
            return;
        }

        mSensorLogListBean = dataBean.getList().get(0);
        //生成时间
        tvCreateTime.setText(mSensorLogListBean.getCreateTime());
        adapter.add(new DipRealBean(mSensorLogListBean.getOx()));  //x角度
        adapter.add(new DipRealBean(mSensorLogListBean.getOy()));  //y角度
        adapter.add(new DipRealBean(mSensorLogListBean.getObd()));  //激光距离
        adapter.add(new DipRealBean(mSensorLogListBean.getOldx()));  //X轴单次角度差
        adapter.add(new DipRealBean(mSensorLogListBean.getOldy()));  //Y轴单次角度差
        adapter.add(new DipRealBean(mSensorLogListBean.getFirstOldx(), mSensorLogListBean.getFirstOldx() > mTiltSensorAlarmBean.getAxisX()));  //X轴累计角度差
        adapter.add(new DipRealBean(mSensorLogListBean.getFirstOldy(), mSensorLogListBean.getFirstOldy() > mTiltSensorAlarmBean.getAxisY()));  //Y轴累计角度差

//        adapter.add(new DipRealBean(FormatUtils.roundOff(mSensorLogListBean.getObd() / 1000, 1)));  //激光距离
//        adapter.add(new DipRealBean(FormatUtils.roundOff(mSensorLogListBean.getOldx() / 1000, 1)));  //X轴单次角度差
//        adapter.add(new DipRealBean(FormatUtils.roundOff(mSensorLogListBean.getOldy() / 1000, 1)));  //Y轴单次角度差
//        adapter.add(new DipRealBean(FormatUtils.roundOff(mSensorLogListBean.getFirstOldx() / 1000, 1)));  //X轴累计角度差
//        adapter.add(new DipRealBean(FormatUtils.roundOff(mSensorLogListBean.getFirstOldy() / 1000, 1)));  //Y轴累计角度差
//        double cdObd = FormatUtils.roundOff(mSensorLogListBean.getCdObd(), 1);//单次沉降
//        adapter.add(new DipRealBean(cdObd, cdObd > mTiltSensorAlarmBean.getSettlement(), getStateSettlement(cdObd)));
//        double cdObdAdd = FormatUtils.roundOff(mSensorLogListBean.getCdObdAdd(), 1);//累计沉降
//        adapter.add(new DipRealBean(cdObdAdd, getStateSettlement(cdObdAdd)));
//
//        adapter.add(new DipRealBean(mSensorLogListBean.getHightObd()));//当次空间位移
//        adapter.add(new DipRealBean(mSensorLogListBean.getHightObdAdd()));//累计空间位移
//
//        double obdLeft = FormatUtils.roundOff(mSensorLogListBean.getObdLeft() * 1000, 1);//单次沉降
//        adapter.add(new DipRealBean(obdLeft, obdLeft > mTiltSensorAlarmBean.getHorizontalFloatingLeft(), getState(obdLeft)));
//        double obdRight = FormatUtils.roundOff(mSensorLogListBean.getObdRight() * 1000, 1);//单次沉降
//        adapter.add(new DipRealBean(obdRight, obdRight > mTiltSensorAlarmBean.getHorizontalFloatingRight(), getState(obdRight)));
//
//        double floatObdLef = FormatUtils.roundOff(mSensorLogListBean.getFloatObdLeft() * 1000, 1);//单次沉降
//        adapter.add(new DipRealBean(floatObdLef, getState(floatObdLef)));
//        double floatObdRight = FormatUtils.roundOff(mSensorLogListBean.getFloatObdRight() * 1000, 1);//单次沉降
//        adapter.add(new DipRealBean(floatObdRight, getState(floatObdRight)));
        double cdObd = mSensorLogListBean.getCdObd();//单次沉降
        adapter.add(new DipRealBean(cdObd, getStateSettlement(cdObd)));
        double cdObdAdd = mSensorLogListBean.getCdObdAdd();//累计沉降
        adapter.add(new DipRealBean(cdObdAdd, cdObdAdd > mTiltSensorAlarmBean.getSettlement(), getStateSettlement(cdObdAdd)));

        adapter.add(new DipRealBean(mSensorLogListBean.getHightObd()));//当次空间位移
        adapter.add(new DipRealBean(mSensorLogListBean.getHightObdAdd(), mSensorLogListBean.getHightObdAdd() > mTiltSensorAlarmBean.getSpace()));//累计空间位移

        double obdLeft = mSensorLogListBean.getObdLeft();//单次沉降左
        adapter.add(new DipRealBean(obdLeft, getState(obdLeft)));
        double obdRight = mSensorLogListBean.getObdRight();//单次沉降右
        adapter.add(new DipRealBean(obdRight, getState(obdRight)));

        double floatObdLef = mSensorLogListBean.getFloatObdLeft();//累计沉降左
        adapter.add(new DipRealBean(floatObdLef, floatObdLef > mTiltSensorAlarmBean.getHorizontalFloatingLeft(), getState(floatObdLef)));
        double floatObdRight = mSensorLogListBean.getFloatObdRight();//累计沉降右
        adapter.add(new DipRealBean(floatObdRight, floatObdRight > mTiltSensorAlarmBean.getHorizontalFloatingRight(), getState(floatObdRight)));
    }

    private String getStateSettlement(double value) {
        if (value > 0) {
            return "(下沉)";
        } else if (value < 0) {
            return "(上浮)";
        } else {
            return "";
        }
    }

    private String getState(double value) {
        if (value > 0) {
            return "(上浮)";
        } else if (value < 0) {
            return "(下沉)";
        } else {
            return "";
        }
    }

    @Override
    public void onGetTiltSensorLogFail(String msg) {
        showMsg(msg);
    }

    @Override
    public void onSetAllMessageSuccess(HttpResponse<String> dataBean) {
        switch (dataBean.getData()) {
            case TiltSensorParaState.CLOSE:
                mParaState = TiltSensorParaState.CLOSE;
                break;
            case TiltSensorParaState.OPEN:
                mParaState = TiltSensorParaState.OPEN;
                break;
            case TiltSensorParaState.RESET:
                mParaState = TiltSensorParaState.RESET;
                break;
        }
        mParaList.get(tiltSensorTypeSp.getSelectedItemPosition()).setStates(mParaState);//更新监测点列表里的数据
        refreshState();
        showMsg(dataBean.getMsg());
    }

    @Override
    public void onSetAllMessageFail(String msg) {
        showMsg(msg);
    }

    @Override
    public void getTiltSensorStateSuccess(TiltSensorStateJson tiltSensorStateJson) {
        Log.e("huxin",tiltSensorStateJson.toString());
        //电量
        tiltSensorStateJson.getDevices().get(0).getServices().get(2).getData().getBat();
        //信号强度
        tiltSensorStateJson.getDevices().get(0).getServices().get(2).getData().getSignal();
        //设备状态
        Integer.toBinaryString(tiltSensorStateJson.getDevices().get(0).getServices().get(2).getData().getState());
        //告警状态
        Integer.toBinaryString(tiltSensorStateJson.getDevices().get(0).getServices().get(2).getData().getWarn());
    }

    @Override
    public void getTiltSensorStateFail(String msg) {

    }

    @OnClick({R.id.tvCharDetails, R.id.tvRefreshTime, R.id.tvAlarm, R.id.tiltSensorStateTv, R.id.tvTitleSensorCheck, R.id.tvTitleSensorSetting})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvTitleSensorCheck:
               mPresenter.getTiltSensorState(mSeq);
                break;
            case R.id.tvTitleSensorSetting:
               // TiltSensorActivity.newInstance(getActivity(), mCamId);
                break;
            case R.id.tvCharDetails:
                TiltSensorActivity.newInstance(getActivity(), mCamId);
                break;
            case R.id.tvRefreshTime:
                speedDialogShow();
                break;
            case R.id.tvAlarm:
                if (!mTiltSensorAlarmBean.isAlreadySet() && mSensorLogListBean != null) {
                    mTiltSensorAlarmBean.setAxisX(mSensorLogListBean.getFirstOldx());
                    mTiltSensorAlarmBean.setAxisY(mSensorLogListBean.getFirstOldy());
                    mTiltSensorAlarmBean.setSettlement(mSensorLogListBean.getCdObdAdd());
                    mTiltSensorAlarmBean.setSpace(mSensorLogListBean.getHightObdAdd());
                    mTiltSensorAlarmBean.setHorizontalFloatingLeft(mSensorLogListBean.getFloatObdLeft());
                    mTiltSensorAlarmBean.setHorizontalFloatingRight(mSensorLogListBean.getFloatObdRight());
                }
                new AlarmDialog(getActivity())
                        .setAlarmData(mTiltSensorAlarmBean)
                        .setLeftClick(tiltSensorAlarmBean -> {
                            mTiltSensorAlarmBean = tiltSensorAlarmBean;
                            mTiltSensorAlarmBean.setAlreadySet(true);
                            getLoopRequest(Integer.parseInt(speedNum));
                        })
                        .show();
                break;
            case R.id.tiltSensorStateTv:
                if (mParaState.equals(TiltSensorParaState.OPEN)) {
                    mPresenter.setAllMessage(mParaID, mSeq, TiltSensorParaState.CLOSE + "");
                } else {
                    mPresenter.setAllMessage(mParaID, mSeq, TiltSensorParaState.OPEN + "");
                }
                break;
        }
    }

}
