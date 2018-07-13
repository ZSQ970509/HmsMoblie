package com.hc.hmsmoblie.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.CheckBox;

import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.bean.json.LoginJson;
import com.hc.hmsmoblie.bean.json.UpdateVersionJson;
import com.hc.hmsmoblie.db.UserInfoPref;
import com.hc.hmsmoblie.mvp.contact.LoginC;
import com.hc.hmsmoblie.mvp.presenter.LoginP;
import com.hc.hmsmoblie.utils.PhoneSystemUtils;
import com.hc.hmsmoblie.widget.CommonDialog;
import com.yc.yclibrary.utils.ActivityUtils;

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

    public static void newInstance(Activity activity) {
        activity.startActivity(new Intent(activity, LoginActivity.class));
    }

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
        ActivityUtils.INSTANCE.finishOthersActivity(LoginActivity.class);
        mPresenter.updatedVersion();
//        mUserAccountTv.setText("admin");
//        mUserPasswordTv.setText("hckj1234");
        boolean isSavePassword = UserInfoPref.getSavePassWord();
        mIsSavePasswordCb.setChecked(isSavePassword);
        if (isSavePassword) {
            mUserAccountTv.setText(UserInfoPref.getUserAccount());
            mUserPasswordTv.setText(UserInfoPref.getUserPassword());
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

    @Override
    public void onUpdatedVersionSuccess(UpdateVersionJson updateVersionJson) {
        if (updateVersionJson.getUpdateVersionCode() > PhoneSystemUtils.getVersionCode()) {
            if (updateVersionJson.getUpdateForceUpdate().equals("1")) {
                updatedVersion(updateVersionJson.getUpdateDownLoadUrl());
            } else {
                CommonDialog commonDialog = new CommonDialog(getActivity());
                commonDialog.setMsg(updateVersionJson.getUpdateLogMsg())
                        .setTitle("是否更新版本")
                        .setRightBtnText("更新")
                        .setLeftBtnText("取消")
                        .setRightClick(v -> updatedVersion(updateVersionJson.getUpdateDownLoadUrl()))
                        .show();
            }
        }
    }

    @Override
    public void onUpdatedVersionFail(String msg) {
        showToast(msg);
    }

    private void updatedVersion(String apkUrl) {
        PhoneSystemUtils.downloadFile(getActivity(), apkUrl);
    }

    @OnClick({R.id.btnLogin, R.id.login_save_password_tv})
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
