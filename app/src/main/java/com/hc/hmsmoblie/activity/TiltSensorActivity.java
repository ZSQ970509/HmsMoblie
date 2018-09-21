package com.hc.hmsmoblie.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.CheckBox;

import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.fragment.TiltSensorAbleFragment;
import com.hc.hmsmoblie.fragment.TiltSensorChartFragment;
import com.yc.yclibrary.base.YcAppCompatActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *  倾角页面
 */

public class TiltSensorActivity extends YcAppCompatActivity {
    @BindView(R.id.tiltSensorTableCb)
    CheckBox tiltSensorTableCb;
    @BindView(R.id.tiltSensorChartCb)
    CheckBox tiltSensorChartCb;
    private Fragment[] fragments;
    private int currentTabIndex;

    @Override
    protected int getLayoutId() {
        return R.layout.tilt_sensor_activity;
    }

    @Override
    protected void initView(Bundle bundle) {
        initFragment();
    }

    private void initFragment() {
        TiltSensorAbleFragment ableFragment = TiltSensorAbleFragment.newInstance();
        TiltSensorChartFragment chartFragment = TiltSensorChartFragment.newInstance();
        fragments = new Fragment[]{ableFragment,chartFragment};
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
                switchFragment( 0);
                break;
            case R.id.tiltSensorChartCb:
                switchFragment(1);
                break;
        }
    }
}
