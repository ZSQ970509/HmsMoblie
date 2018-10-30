package com.hc.hmsmoblie.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.classic.adapter.BaseAdapterHelper;
import com.classic.adapter.CommonAdapter;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.bean.domain.TiltSensorAlarmBean;
import com.hc.hmsmoblie.bean.json.SensorLogJson;
import com.hc.hmsmoblie.bean.json.TiltSensorParaJson;
import com.hc.hmsmoblie.mvp.contact.DipRealTimeDataC;
import com.hc.hmsmoblie.mvp.presenter.DipRealTimeDataP;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.hc.hmsmoblie.utils.FormatUtils;
import com.hc.hmsmoblie.widget.AlarmDialog;
import com.hc.hmsmoblie.widget.CommonDialog;
import com.yc.yclibrary.exception.ApiException;

import java.text.DecimalFormat;
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

public class DipRealTimeDataActivityOld extends BaseMvpActivity<DipRealTimeDataP> implements DipRealTimeDataC.V {
    @BindView(R.id.tiltSensorTypeSp)
    Spinner tiltSensorTypeSp;
    @BindView(R.id.tvCharDaetails)
    TextView tvCharDaetails;
    @BindView(R.id.tvXDistance)
    TextView tvXDistance;
    @BindView(R.id.tvYDistance)
    TextView tvYDistance;
    @BindView(R.id.tvLaserDistance)
    TextView tvLaserDistance;
    @BindView(R.id.tvSecondaryAngleDifference)
    TextView tvSecondaryAngleDifference;
    @BindView(R.id.tvStageAngleDifference)
    TextView tvStageAngleDifference;
    @BindView(R.id.tvCumulativeAngleDifference)
    TextView tvCumulativeAngleDifference;
    @BindView(R.id.tvSecondarySettlementDisplacement)
    TextView tvSecondarySettlementDisplacement;
    @BindView(R.id.tvStageSettlementDisplacement)
    TextView tvStageSettlementDisplacement;
    @BindView(R.id.tvSecondarySettlementDisplacementState)
    TextView tvSecondarySettlementDisplacementState;
    @BindView(R.id.tvStageSettlementDisplacementState)
    TextView tvStageSettlementDisplacementState;
    @BindView(R.id.tvAccumulativeSettlementDisplacement)
    TextView tvAccumulativeSettlementDisplacement;
    @BindView(R.id.tvAccumulativeSettlementDisplacementState)
    TextView tvAccumulativeSettlementDisplacementState;
    @BindView(R.id.tvSideParallelismFloating)
    TextView tvSideParallelismFloating;
    @BindView(R.id.tvSideParallelismFloatingState)
    TextView tvSideParallelismFloatingState;
    @BindView(R.id.tvStageSideParallelismFloating)
    TextView tvStageSideParallelismFloating;
    @BindView(R.id.tvStageSideParallelismFloatingState)
    TextView tvStageSideParallelismFloatingState;
    @BindView(R.id.tvCumulativeSideParallelismFloating)
    TextView tvCumulativeSideParallelismFloating;
    @BindView(R.id.tvCumulativeSideParallelismFloatingState)
    TextView tvCumulativeSideParallelismFloatingState;

    @BindView(R.id.tvCreateTime)
    TextView tvCreateTime;
    @BindView(R.id.tvRefreshTime)
    TextView tvRefeshTime;
    String speedNum = "15";
    private static final String CAM_ID = "cam_id";
    private String mCamId;
    private String mParaID;
    private List<TiltSensorParaJson.ListBean> mParaIds = new ArrayList<TiltSensorParaJson.ListBean>();
    private CommonAdapter<String> mSpAdapter;
    Disposable mDisposableAlarm;
    NetObserver<HttpResponse<SensorLogJson>> responseNetObserver;
    private boolean mIsInit = false;
    private TiltSensorAlarmBean mTiltSensorAlarmBean;

    public static void newInstance(Activity activity, String camID) {
        Intent intent = new Intent(activity, DipRealTimeDataActivityOld.class);
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
        mCamId = getIntent().getStringExtra(CAM_ID);
//        mCamId = "1014603";
        initSpinner();
        getParaIds();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getLoopRequest(Integer.parseInt(speedNum));
    }

    private void getParaIds() {
        mPresenter.getGetTiltSensorPara(mCamId);
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

    public void initSpinner() {
        tiltSensorTypeSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                mParaID = mParaIds.get(position).getParaID() + "";
                getLoopRequest(Integer.parseInt(speedNum));
                Log.e("paraID1111", mParaID);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

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
        tvSpeedDialog.setText("当前刷新间隔(s)：" + (sbSpeedDialog.getProgress()));
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
                tvRefeshTime.setText("当前刷新间隔(s):" + speedNum);
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

                tvSpeedDialog.setText("当前秒数：" + (sbSpeedDialog.getProgress() + 1));
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
            onGetGetTiltSensorParaFail("");
        } else {
            mParaIds = dataBean.getList();
            List<String> dataList = new ArrayList<String>();
            for (TiltSensorParaJson.ListBean jsonBean : mParaIds) {
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
        if (dataBean.getList() == null || dataBean.getList().size() <= 0) {
            tvXDistance.setText("-");
            tvYDistance.setText("-");
            tvLaserDistance.setText("-");
            tvSecondaryAngleDifference.setText("-");
            tvStageAngleDifference.setText("-");
            tvCumulativeAngleDifference.setText("-");
            tvSecondarySettlementDisplacement.setText("-");
            tvStageSettlementDisplacement.setText("-");
            tvAccumulativeSettlementDisplacement.setText("-");
            tvSideParallelismFloating.setText("-");
            tvStageSideParallelismFloating.setText("-");
            tvCumulativeSideParallelismFloating.setText("-");
            tvCreateTime.setText("-");
            return;
        }
        SensorLogJson.ListBean listBean = dataBean.getList().get(0);
        DecimalFormat df = new DecimalFormat("0.#");

        //x角度
        tvXDistance.setText(listBean.getOx() + "°");
        setTvColor(listBean.getOx() >= mTiltSensorAlarmBean.getAxisX(), tvXDistance);
        //y角度
        tvYDistance.setText(listBean.getOy() + "°");
        setTvColor(listBean.getOy() >= mTiltSensorAlarmBean.getAxisY(), tvYDistance);
        //激光距离
        tvLaserDistance.setText(df.format(listBean.getObd() / 1000) + "");
        //单次角度差
        tvSecondaryAngleDifference.setText(listBean.getOldx() + "°," + listBean.getOldy() + "°");
        //阶段角度差
        tvStageAngleDifference.setText(listBean.getStagex() + "°," + listBean.getStagey() + "°");
        //累计角度差
        tvCumulativeAngleDifference.setText(listBean.getFirstOldx() + "°," + listBean.getFirstOldy() + "°");
        double cdObd = FormatUtils.roundOff(listBean.getCdObd(), 1);
        double obdOldx = FormatUtils.roundOff(listBean.getObdOldx(), 1);
        double obdOldy = FormatUtils.roundOff(listBean.getObdOldy(), 1);
        double obdOldz = FormatUtils.roundOff(listBean.getObdOldz(), 1);
        //单次沉降+坐标位移
        tvSecondarySettlementDisplacement.setText(cdObd + "(" + obdOldx + "," + obdOldy + "," + obdOldz + ")");
        tvSecondarySettlementDisplacementState.setText(getState(cdObd, obdOldx, obdOldx, obdOldy));
        setTvColor(listBean.getCdObd() >= mTiltSensorAlarmBean.getSettlement(), tvSecondarySettlementDisplacement, tvSecondarySettlementDisplacementState);
        //阶段沉降+坐标位移
        double cdObdDiff = FormatUtils.roundOff(listBean.getCdObdDiff(), 1);
        double obdStagex = FormatUtils.roundOff(listBean.getObdStagex(), 1);
        double obdStagey = FormatUtils.roundOff(listBean.getObdStagey(), 1);
        double obdStagez = FormatUtils.roundOff(listBean.getObdStagez(), 1);
        tvStageSettlementDisplacement.setText(cdObdDiff + "(" + obdStagex + "," + obdStagey + "," + obdStagez + ")");
        tvStageSettlementDisplacementState.setText(getState(cdObdDiff, obdStagex, obdStagey, obdStagez));
        //累计沉降+坐标位移
        double cdObdAdd = FormatUtils.roundOff(listBean.getCdObdAdd(), 1);
        double obdFirstOldx = FormatUtils.roundOff(listBean.getObdFirstOldx(), 1);
        double obdFirstOldey = FormatUtils.roundOff(listBean.getObdFirstOldy(), 1);
        double obdFirstOldz = FormatUtils.roundOff(listBean.getObdFirstOldz(), 1);
        tvAccumulativeSettlementDisplacement.setText(cdObdAdd + "(" + obdFirstOldx + "," + obdFirstOldey + "," + obdFirstOldz + ")");
        tvAccumulativeSettlementDisplacementState.setText(getState(cdObdAdd, obdFirstOldx, obdFirstOldey, obdFirstOldz));
        //单次水平度浮动
        double obdLeft = FormatUtils.roundOff(listBean.getObdLeft() * 1000, 1);
        double obdRight = FormatUtils.roundOff(listBean.getObdRight() * 1000, 1);
        tvSideParallelismFloating.setText(obdLeft + "," + obdRight);
        tvSideParallelismFloatingState.setText(getStateF(obdLeft, obdRight));
        setTvColor(obdLeft >= mTiltSensorAlarmBean.getHorizontalFloatingLeft() && obdRight >= mTiltSensorAlarmBean.getHorizontalFloatingRight()
                , tvSecondarySettlementDisplacement, tvSideParallelismFloatingState);
        //阶段水平度浮动
        double stageObdLeft = FormatUtils.roundOff(listBean.getStageObdLeft() * 1000, 1);
        double stageObdRight = FormatUtils.roundOff(listBean.getStageObdRight() * 1000, 1);
        tvStageSideParallelismFloatingState.setText(getStateF(stageObdLeft, stageObdRight));
        tvStageSideParallelismFloating.setText(stageObdLeft + "," + stageObdRight);
        //累计水平度浮动
        double floatObdLef = FormatUtils.roundOff(listBean.getFloatObdLeft() * 1000, 1);
        double floatObdRight = FormatUtils.roundOff(listBean.getFloatObdRight() * 1000, 1);
        tvCumulativeSideParallelismFloatingState.setText(getStateF(floatObdLef, floatObdRight));
        tvCumulativeSideParallelismFloating.setText(floatObdLef + "," + floatObdRight);
        //生成时间
        tvCreateTime.setText(listBean.getCreateTime());
    }

    private String getState(double s, double x, double y, double z) {
        String temp = "";
        if (s > 0) {
            temp += "下";
        } else if (s < 0) {
            temp += "上";
        } else {
            temp += "-";
        }
        if (x > 0) {
            temp += "(后,";
        } else if (x < 0) {
            temp += "(前,";
        } else {
            temp += "(-,";
        }
        if (y > 0) {
            temp += "右,";
        } else if (y < 0) {
            temp += "左,";
        } else {
            temp += "-,";
        }
        if (z > 0) {
            temp += "下)";
        } else if (z < 0) {
            temp += "上)";
        } else {
            temp += "-)";
        }
        return temp;
    }

    private String getStateF(double x, double y) {
        String temp = "";
        if (x > 0) {
            temp += "上,";
        } else if (x < 0) {
            temp += "下,";
        } else {
            temp += "-,";
        }
        if (y > 0) {
            temp += "上";
        } else if (y < 0) {
            temp += "下";
        } else {
            temp += "-";
        }
        return temp;
    }

    private void setTvColor(boolean isAlarm, TextView tv) {
        if (mTiltSensorAlarmBean.isOpen() && isAlarm) {
            tv.setTextColor(getResources().getColor(R.color.alarmColorBad));
        } else {
            tv.setTextColor(getResources().getColor(R.color.alarmColorGray));
        }
    }

    private void setTvColor(boolean isAlarm, TextView tv1, TextView tv2) {
        if (mTiltSensorAlarmBean.isOpen() && isAlarm) {
            tv1.setTextColor(getResources().getColor(R.color.alarmColorBad));
            tv2.setTextColor(getResources().getColor(R.color.alarmColorBad));
        } else {
            tv1.setTextColor(getResources().getColor(R.color.alarmColorGray));
            tv2.setTextColor(getResources().getColor(R.color.alarmColorGray));
        }
    }

    @Override
    public void onGetTiltSensorLogFail(String msg) {
    }

    @OnClick({R.id.tvCharDaetails, R.id.tvRefreshTime, R.id.tvAlarm})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvCharDaetails:
                TiltSensorActivity.newInstance(getActivity(), mCamId);
                break;
            case R.id.tvRefreshTime:
                speedDialogShow();
                break;
            case R.id.tvAlarm:
                new AlarmDialog(getActivity())
                        .setAlarmData(mTiltSensorAlarmBean)
                        .setLeftClick(tiltSensorAlarmBean -> {
                            mTiltSensorAlarmBean = tiltSensorAlarmBean;
                            getLoopRequest(Integer.parseInt(speedNum));
                        })
                        .show();
                break;
        }
    }
}
