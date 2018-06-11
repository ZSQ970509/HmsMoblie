package com.hc.hmsmoblie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.mvp.contact.LoginC;
import com.hc.hmsmoblie.mvp.presenter.LoginP;
import com.yc.yclibrary.base.YcMvpAppCompatActivity;
import com.yc.yclibrary.mvp.BasePresenter;

import butterknife.OnClick;

/**
 *
 */

public class LoginActivity extends YcMvpAppCompatActivity<LoginP> implements LoginC.V {
    @Override
    protected LoginP loadPresenter() {
        return new LoginP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle bundle) {

    }

    @OnClick({R.id.btnLogin})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
        }
    }
}
