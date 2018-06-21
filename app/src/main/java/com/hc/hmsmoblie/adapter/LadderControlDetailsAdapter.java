package com.hc.hmsmoblie.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hc.hmsmoblie.fragment.LadderControlErrorFragment;
import com.hc.hmsmoblie.fragment.LadderControlOperationFragment;

/**
 *
 */

public class LadderControlDetailsAdapter extends FragmentPagerAdapter {
    private final String[] TITLES = new String[]{"操作记录", "错误日志"};
    private String mProId;
    private String mDeviceId;
    private Fragment[] fragments;

    public LadderControlDetailsAdapter(FragmentManager fm, String proId, String deviceId) {
        super(fm);
        fragments = new Fragment[TITLES.length];
        mDeviceId = deviceId;
        mProId = proId;
    }


    @Override
    public Fragment getItem(int position) {
        if (fragments[position] == null) {
            switch (position) {
                case 0:
                    fragments[position] = LadderControlOperationFragment.newInstance(mDeviceId);
                    break;
                case 1:
                    fragments[position] = LadderControlErrorFragment.newInstance(mProId);
                    break;
                default:
                    break;
            }
        }
        return fragments[position];
    }


    @Override
    public int getCount() {
        return TITLES.length;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }
}
