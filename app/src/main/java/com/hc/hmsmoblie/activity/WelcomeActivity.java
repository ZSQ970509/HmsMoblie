package com.hc.hmsmoblie.activity;

import android.content.Intent;
import android.os.Bundle;

import com.hc.hmsmoblie.R;
import com.yc.yclibrary.base.YcAppCompatActivity;

/**
 *  欢迎页面
 */

public class WelcomeActivity extends YcAppCompatActivity{
    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView(Bundle bundle) {
        startActivity(new Intent(getActivity(),LoginActivity.class));
    }
}
