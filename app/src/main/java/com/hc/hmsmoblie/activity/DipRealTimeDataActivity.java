package com.hc.hmsmoblie.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.CheckBox;

import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.bean.json.TiltSensorParaJson;
import com.hc.hmsmoblie.fragment.TiltSensorAbleFragment;
import com.hc.hmsmoblie.fragment.TiltSensorChartFragment;
import com.hc.hmsmoblie.mvp.contact.DipRealTimeDataC;
import com.hc.hmsmoblie.mvp.contact.TiltSensorActivityC;
import com.hc.hmsmoblie.mvp.presenter.DipRealTimeDataP;
import com.hc.hmsmoblie.mvp.presenter.TiltSensorActivityP;
import com.hc.hmsmoblie.widget.CommonDialog;

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
    private static final String CAM_ID = "cam_id";
    private String mCamId = "1014603";
    Disposable mDisposableAlarm;

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

    }

    @Override
    protected void onResume() {
        super.onResume();
        getLoopRequest();
    }

    private void getLoopRequest(){
        if (mDisposableAlarm != null)
            mDisposableAlarm.dispose();
        //initialDelay第一次执行间隔 period之后执行间隔
        mDisposableAlarm = Observable.interval(0, 20, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> mPresenter.getGetTiltSensorPara(mCamId));

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

    }

    @Override
    public void onGetGetTiltSensorParaFail(String msg) {

    }
}
