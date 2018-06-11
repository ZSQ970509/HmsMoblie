package com.hc.hmsmoblie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;

import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.bean.json.LoginJs;
import com.hc.hmsmoblie.mvp.contact.LoginC;
import com.hc.hmsmoblie.mvp.presenter.LoginP;
import com.yc.yclibrary.base.YcMvpAppCompatActivity;
import com.yc.yclibrary.mvp.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 */

public class LoginActivity extends YcMvpAppCompatActivity<LoginP> implements LoginC.V {
    @BindView(R.id.tvLoginUserName)
    TextInputEditText mUserNameTv;
    @BindView(R.id.tvLoginUserPassword)
    TextInputEditText mUserPasswordTv;

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

    @Override
    public void onLoginSuccess(LoginJs loginJs) {
        MainActivity.newInstance(getActivity());
    }

    @Override
    public void onLoginFail(String msg) {
        showToast(msg);
    }

    @OnClick({R.id.btnLogin})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                mPresenter.login(mUserNameTv.getText().toString(), mUserPasswordTv.getText().toString());
                break;
        }
    }
}
