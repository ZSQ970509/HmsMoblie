package com.hc.hmsmoblie.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.Toast;

import com.classic.adapter.BaseAdapterHelper;
import com.classic.adapter.CommonAdapter;
import com.classic.adapter.CommonRecyclerAdapter;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.bean.domain.DipRealBean;
import com.hc.hmsmoblie.bean.domain.TiltSensorAlarmBean;
import com.hc.hmsmoblie.bean.domain.TiltSensorSettingBean;
import com.hc.hmsmoblie.bean.domain.TiltSensorSettingPostBean;
import com.hc.hmsmoblie.bean.json.SensorLogJson;
import com.hc.hmsmoblie.bean.json.SetAllMessageJson;
import com.hc.hmsmoblie.bean.json.TiltSensorParaJson;
import com.hc.hmsmoblie.bean.json.TiltSensorStateJson;
import com.hc.hmsmoblie.bean.type.TiltSensorParaState;
import com.hc.hmsmoblie.mvp.contact.DipRealTimeDataC;
import com.hc.hmsmoblie.mvp.presenter.DipRealTimeDataP;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.hc.hmsmoblie.utils.BatAndSignalUtil;
import com.hc.hmsmoblie.utils.FormatUtils;
import com.hc.hmsmoblie.widget.AlarmDialog;
import com.hc.hmsmoblie.widget.CommonDialog;
import com.hc.hmsmoblie.widget.TitleSenorSettingDialog;
import com.yc.yclibrary.exception.ApiException;

import org.json.simple.JSONObject;

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
    private TiltSensorSettingBean mTiltSensorSettingBean;
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
        mTiltSensorSettingBean = new TiltSensorSettingBean();
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
    private void titleSensorCheckDialogShow(TiltSensorStateJson tiltSensorStateJson) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View v = inflater.inflate(R.layout.dip_title_sensor_see, null);
        TextView btn_sure = (TextView) v.findViewById(R.id.dialog_btn_sure);
        TextView tvDriveBat = (TextView) v.findViewById(R.id.tvDriveBat);
        TextView tvDriveSignal = (TextView) v.findViewById(R.id.tvDriveSignal);
        TextView tvAutoDrive = (TextView) v.findViewById(R.id.tvAutoDrive);
        TextView tvHeadDrive = (TextView) v.findViewById(R.id.tvHeadDrive);
        TextView tvBatState = (TextView) v.findViewById(R.id.tvBatState);
        List<TiltSensorStateJson.DevicesBean.ServicesBean> servicesBean = tiltSensorStateJson.getDevices().get(0).getServices();
        for (TiltSensorStateJson.DevicesBean.ServicesBean servicesBeans :servicesBean) {
            if (servicesBeans.getServiceId().equals("CurVal")) {
                //电量
                tvDriveBat.setText("设备电量："+ BatAndSignalUtil.changeBatPercentage(servicesBeans.getData().getBat()/10.0));
                //信号强度
                tvDriveSignal.setText("信号强度："+BatAndSignalUtil.changeSignalPercentage(-servicesBeans.getData().getSignal()));
                //设备状态
                if(servicesBeans.getData().getState() != 0){
                    String boolAutoOpen =  Integer.toBinaryString(servicesBeans.getData().getState()).substring(1,2);
                    if(boolAutoOpen.equals("0") ){
                        tvAutoDrive.setText("自动开关传感器状态：开");
                    }else if(boolAutoOpen.equals("1")){
                        tvAutoDrive.setText("自动开关传感器状态：关");
                    }
                }else{
                    tvAutoDrive.setText("自动开关传感器状态：开");
                }
                if(servicesBeans.getData().getState() != 0 && Integer.toBinaryString(servicesBeans.getData().getState()).length() >= 7){
                    String boolHeadOpen = Integer.toBinaryString(servicesBeans.getData().getState()).substring(0,1);
                    if(boolHeadOpen.equals("0") ){
                        tvHeadDrive.setText("手动开关传感器状态：开");
                    }else if(boolHeadOpen.equals("1") ){
                        tvHeadDrive.setText("手动开关传感器状态：关");
                    }
                }else{
                    tvHeadDrive.setText("手动开关传感器状态：开");
                }

                //告警状态
                if(servicesBeans.getData().getWarn() != 0 && Integer.toBinaryString(servicesBeans.getData().getWarn()).length() >= 7){
                    String boolBatState = Integer.toBinaryString(servicesBeans.getData().getWarn()).substring(011);
                    if(boolBatState.equals("0") ){
                        tvBatState.setText("电池状态:电池电量充足");
                    }else if(boolBatState.equals("1")){
                        tvBatState.setText("电池状态:电池电量过低");
                    }
                }else{
                    tvBatState.setText("电池状态:电池电量充足");
                }
            }
        }

        final Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setContentView(v);//自定义布局应该在这里添加，要在dialog.show()的后面
        //dialog.getWindow().setGravity(Gravity.CENTER);//可以设置显示的位置
        btn_sure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
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
        if (tiltSensorStateJson.getTotalCount() != 0) {
            titleSensorCheckDialogShow(tiltSensorStateJson);
        }else{
            showToast("暂无数据！");
        }

    }

    @Override
    public void getTiltSensorStateSettingSuccess(TiltSensorStateJson tiltSensorStateJson) {
        if (tiltSensorStateJson.getTotalCount() != 0) {
            List<TiltSensorStateJson.DevicesBean.ServicesBean> servicesBean = tiltSensorStateJson.getDevices().get(0).getServices();
            for (TiltSensorStateJson.DevicesBean.ServicesBean servicesBeans :servicesBean){
                if( servicesBeans.getServiceId().equals("Setting")){
                    mTiltSensorSettingBean.setAxisX(servicesBeans.getData().getSlope_Thres_X()/10000.0);
                    mTiltSensorSettingBean.setAxisY(servicesBeans.getData().getSlope_Thres_Y()/10000.0);
                    mTiltSensorSettingBean.setRptPer(servicesBeans.getData().getRptPer());
                    mTiltSensorSettingBean.setRptPer_warn(servicesBeans.getData().getRptPer_warn());
                    mTiltSensorSettingBean.setCacheTime(servicesBeans.getData().getRptPer()+100);
                }
                if( servicesBeans.getServiceId().equals("CurVal")){
                    if(servicesBeans.getData().getState() != 0 && Integer.toBinaryString(servicesBeans.getData().getState()).length() >= 7){
                        String boolHeadOpen = Integer.toBinaryString(servicesBeans.getData().getState()).substring(0,1);
                        if(boolHeadOpen.equals("0") ){
                            mTiltSensorSettingBean.setDevState(0);
                        }else if(boolHeadOpen.equals("1") ){
                            mTiltSensorSettingBean.setDevState(1);
                        }
                    }else{
                        mTiltSensorSettingBean.setDevState(0);
                    }
                }
            }
            new TitleSenorSettingDialog(getActivity())
                    .setAlarmData(mTiltSensorSettingBean)
                    .setLeftClick(tiltSensorAlarmBean -> {
                        if(tiltSensorAlarmBean != null) {
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
                            mPresenter.setIotDeviceInfo(settingPostBean_XY, setting_ReportTime, setting_Switch);
                        }else{
                            showToast("配置的数据不能为空！");
                        }
                    })
                    .show();
        }else{
            showToast("暂无数据！");
        }
    }


    @Override
    public void getTiltSensorStateFail(String msg) {

    }

    @Override
    public void setIotDeviceInfoSuccess(boolean xy, boolean reportTime, boolean Switch) {
        if(xy && reportTime && Switch){
            showToast("配置已发送");
        }else{
            StringBuilder toastStr = new StringBuilder();
            if(!xy){
                toastStr.append("x,y轴的倾角差阈值配置失败，");
            }
            if(!reportTime){
                toastStr.append("数据上报时长配置失败，");
            }
            if(!Switch){
                toastStr.append("传感器状态更改失败，");
            }
            showToast(toastStr.append("请重试！").toString());

        }
    }

    @OnClick({R.id.tvCharDetails, R.id.tvRefreshTime, R.id.tvAlarm, R.id.tiltSensorStateTv, R.id.tvTitleSensorCheck, R.id.tvTitleSensorSetting})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvTitleSensorCheck:
                mPresenter.getTiltSensorState(mSeq,false);
                break;
            case R.id.tvTitleSensorSetting:
                mPresenter.getTiltSensorState(mSeq,true);
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
