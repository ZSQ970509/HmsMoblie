package com.hc.hmsmoblie.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.CheckBox;

import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.bean.json.LoginJson;
import com.hc.hmsmoblie.db.UserInfoPref;
import com.hc.hmsmoblie.mvp.contact.LoginC;
import com.hc.hmsmoblie.mvp.presenter.LoginP;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 */

public class LoginActivity extends BaseMvpActivity<LoginP> implements LoginC.V {
    @BindView(R.id.tvLoginUserAccount)
    TextInputEditText mUserAccountTv;
    @BindView(R.id.tvLoginUserPassword)
    TextInputEditText mUserPasswordTv;
    @BindView(R.id.login_save_password_cb)
    CheckBox mIsSavePasswordCb;
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
        mUserAccountTv.setText("admin");
        mUserPasswordTv.setText("hckj1234");
        if(UserInfoPref.getSavePassWord()){
            mUserAccountTv.setText(UserInfoPref.getUserAccount());
            mUserPasswordTv.setText(UserInfoPref.getUserPassword());
            mIsSavePasswordCb.setChecked(UserInfoPref.getSavePassWord());
        }
    }

    @Override
    public void onLoginSuccess(LoginJson loginJs) {
        UserInfoPref.setSavePassWord(mIsSavePasswordCb.isChecked());
        MainActivity.newInstance(getActivity());
    }

    @Override
    public void onLoginFail(String msg) {
        showToast(msg);
    }

    @OnClick({R.id.btnLogin,R.id.login_save_password_tv})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                mPresenter.login(mUserAccountTv.getText().toString(), mUserPasswordTv.getText().toString());
                break;
            case R.id.login_save_password_tv:
                mIsSavePasswordCb.setChecked(!mIsSavePasswordCb.isChecked());
                break;
        }
    }
}
