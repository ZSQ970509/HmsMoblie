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
import com.hc.hmsmoblie.bean.json.SensorLogJson;
import com.hc.hmsmoblie.bean.json.TiltSensorParaJson;
import com.hc.hmsmoblie.mvp.contact.DipRealTimeDataC;
import com.hc.hmsmoblie.mvp.presenter.DipRealTimeDataP;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.hc.hmsmoblie.utils.TimePickerUtils;
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

public class DipRealTimeDataActivity extends BaseMvpActivity<DipRealTimeDataP> implements DipRealTimeDataC.V {
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
    @BindView(R.id.tvAccumulativeSettlementDisplacement)
    TextView tvAccumulativeSettlementDisplacement;
    @BindView(R.id.tvLeftSideParallelismFloating)
    TextView tvLeftSideParallelismFloating;
    @BindView(R.id.tvRightSideParallelismFloating)
    TextView tvRightSideParallelismFloating;
    @BindView(R.id.tvCreateTime)
    TextView tvCreateTime;
    @BindView(R.id.tvRefeshTime)
    TextView tvRefeshTime;
    String speedNum = "15";
    private static final String CAM_ID = "cam_id";
    private String mCamId ;
    private String mParaID;
    private List<TiltSensorParaJson.ListBean> mParaIds = new ArrayList<TiltSensorParaJson.ListBean>();
    private CommonAdapter<String> mSpAdapter;
    Disposable mDisposableAlarm;
    NetObserver<HttpResponse<SensorLogJson>> responseNetObserver;
    private boolean mIsInit = false;
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
        mCamId = getIntent().getStringExtra(CAM_ID);
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
    private void getLoopRequest(int period){
        if(!mIsInit)return;
        if(TextUtils.isEmpty(mParaID)){
            showToast("paraID为空");
            return;
        }
        if (mDisposableAlarm != null)
            mDisposableAlarm.dispose();
        //initialDelay第一次执行间隔 period之后执行间隔
        mDisposableAlarm = Observable.interval(0, period, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong ->{
                    if(responseNetObserver!=null)
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
                    mPresenter.getTiltSensorLog(mCamId, mParaID, 1, 10, "", "",responseNetObserver);
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
    @OnClick({R.id.tvCharDaetails,R.id.tvRefeshTime})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvCharDaetails:
                TiltSensorActivity.newInstance(getActivity(), mCamId);
                break;
            case R.id.tvRefeshTime:
                speedDialogShow();
                break;
        }
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

                speedNum = sbSpeedDialog.getProgress()+1+"";
                getLoopRequest(Integer.parseInt(speedNum)+1);
                tvRefeshTime.setText("当前刷新间隔(s):"+speedNum);
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
        mIsInit= true;
        if (dataBean == null || dataBean.getList() == null || dataBean.getList().isEmpty()) {
            onGetGetTiltSensorParaFail("");
        }
        else{
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
        SensorLogJson.ListBean listBean = dataBean.getList().get(0);
        DecimalFormat df = new DecimalFormat("0.#");
        //x角度
        tvXDistance.setText(listBean.getOx()+"°");
        //y角度
        tvYDistance.setText(listBean.getOy()+"°");
        //激光距离
        tvLaserDistance.setText(df.format(listBean.getObd()/1000) + "");
        //当次角度差
        tvSecondaryAngleDifference.setText(listBean.getOldx() + "°," + listBean.getOldy()+"°");
        //阶段角度差
        tvStageAngleDifference.setText(listBean.getStagex() + "°," + listBean.getStagey()+"°");
        //累计角度差
        tvCumulativeAngleDifference.setText(listBean.getFirstOldx() + "°," + listBean.getFirstOldy()+"°");
        //当次沉降+坐标位移
        tvSecondarySettlementDisplacement.setText(df.format(listBean.getCdObd()*1000)
                + "(" + df.format(listBean.getObdOldx()* 1000) + "," + df.format(listBean.getObdOldy()* 1000)+")");
        //阶段沉降+坐标位移
        tvStageSettlementDisplacement.setText(df.format(listBean.getCdObdDiff() * 1000) + "("
                + df.format(listBean.getObdStagex() * 1000) + "," + df.format(listBean.getObdStagey() * 1000) + ","
                + df.format(listBean.getObdStagez() * 1000) + ")");
        //累计沉降+坐标位移
        tvAccumulativeSettlementDisplacement.setText(df.format(listBean.getCdObdAdd() * 1000) + "("
                + df.format(listBean.getObdFirstOldx() * 1000) + "," + df.format(listBean.getObdFirstOldy() * 1000) + ","
                + df.format(listBean.getObdFirstOldz() * 1000) + ")");
        //左端平行度浮动
        tvLeftSideParallelismFloating.setText((listBean.getFloatObdLeft()) + "");
        //右端平行度浮动
        tvRightSideParallelismFloating.setText((listBean.getFloatObdRight()) + "");
        //生成时间
        tvCreateTime.setText(listBean.getCreateTime());
    }

    @Override
    public void onGetTiltSensorLogFail(String msg) {

    }
}
