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
import com.hc.hmsmoblie.mvp.contact.TiltSensorActivityCOld;
import com.hc.hmsmoblie.mvp.presenter.TiltSensorActivityPOld;
import com.hc.hmsmoblie.widget.CommonDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 倾角页面
 */

public class TiltSensorActivityOld extends BaseMvpActivity<TiltSensorActivityPOld> implements TiltSensorActivityCOld.V {
    private static final String CAM_ID = "cam_id";
    private String mCamId;
    @BindView(R.id.tiltSensorTableCb)
    CheckBox tiltSensorTableCb;
    @BindView(R.id.tiltSensorChartCb)
    CheckBox tiltSensorChartCb;
    private Fragment[] fragments;
    private int currentTabIndex;

    public static void newInstance(Activity activity, String camID) {
        Intent intent = new Intent(activity, TiltSensorActivityOld.class);
        intent.putExtra(CAM_ID, camID);
        activity.startActivity(intent);
    }

    @Override
    protected TiltSensorActivityPOld loadPresenter() {
        return new TiltSensorActivityPOld();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.tilt_sensor_activity_old;
    }

    @Override
    protected void initView(Bundle bundle) {
        setToolBar("倾角数据");
        mCamId = getIntent().getStringExtra(CAM_ID);
        getParaIds();
    }

    private void getParaIds() {
//        mCamId = "1014603";
        mPresenter.getGetTiltSensorPara(mCamId);
    }

    private void initFragment(List<TiltSensorParaJson.ListBean> paraIds) {
        TiltSensorAbleFragment ableFragment = TiltSensorAbleFragment.newInstance(mCamId, paraIds);
        TiltSensorChartFragment chartFragment = TiltSensorChartFragment.newInstance(mCamId, paraIds);
        fragments = new Fragment[]{ableFragment, chartFragment};
        // 添加显示第一个fragment
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.tiltSensorFl, ableFragment)
                .show(ableFragment).commit();
    }

    /**
     * Fragment切换
     */
    private void switchFragment(int index) {
        switch (index) {
            case 0:
                tiltSensorTableCb.setChecked(true);
                tiltSensorChartCb.setChecked(false);
                break;
            case 1:
                tiltSensorTableCb.setChecked(false);
                tiltSensorChartCb.setChecked(true);
                break;
        }
        FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
        trx.hide(fragments[currentTabIndex]);
        if (!fragments[index].isAdded()) {
            trx.add(R.id.tiltSensorFl, fragments[index]);
        }
        trx.show(fragments[index]).commit();
        currentTabIndex = index;
    }

    @OnClick({R.id.tiltSensorTableCb, R.id.tiltSensorChartCb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tiltSensorTableCb:
                switchFragment(0);
                break;
            case R.id.tiltSensorChartCb:
                switchFragment(1);
                break;
        }
    }


    @Override
    public void onGetGetTiltSensorParaSuccess(TiltSensorParaJson dataBean) {
        if (dataBean == null || dataBean.getList() == null || dataBean.getList().isEmpty())
            onGetGetTiltSensorParaFail("");
        else
            initFragment(dataBean.getList());
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
}
