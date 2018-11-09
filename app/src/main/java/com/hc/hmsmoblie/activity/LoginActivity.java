package com.hc.hmsmoblie.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.CheckBox;

import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.bean.json.LoginJson;
import com.hc.hmsmoblie.bean.json.UpdateVersionJson;
import com.hc.hmsmoblie.bean.type.UserTypeId;
import com.hc.hmsmoblie.db.UserInfoPref;
import com.hc.hmsmoblie.mvp.contact.LoginC;
import com.hc.hmsmoblie.mvp.presenter.LoginP;
import com.hc.hmsmoblie.utils.PhoneSystemUtils;
import com.hc.hmsmoblie.widget.CommonDialog;
import com.hc.hmsmoblie.widget.LogonServerDialog;
import com.yc.yclibrary.utils.ActivityUtils;

import java.math.BigDecimal;

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
        //MainActivity.newInstance(getActivity());
        VideoSelectProjectActivity.newInstance(getActivity(), "11");
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
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                // 设置提示框的标题

                BigDecimal bg = new BigDecimal(Double.parseDouble(updateVersionJson.getUpdateFileSize()) / 1024 / 1024);
                double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                String appSize = f1 + "";
                builder.setTitle("发现新版本").
                        // 设置提示框的图标
                                setIcon(R.drawable.logo2).
                        // 设置要显示的信息
                                setMessage("最新版本：V" + updateVersionJson.getUpdateVersionName() + "\n" + "新版本大小：" + appSize + "M\n\n" + "更新内容：\n" + updateVersionJson.getUpdateLogMsg()).
                        // 设置确定按钮
                                setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                updatedVersion(updateVersionJson.getUpdateDownLoadUrl());
                            }
                        }).
                        // 设置取消按钮,null是什么都不做，并关闭对话框
                                setNegativeButton("取消", null);
                // 生产对话框
                AlertDialog alertDialog = builder.create();
                alertDialog.setCanceledOnTouchOutside(false);
                // 显示对话框
                alertDialog.show();
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.rgb(88, 190, 252));
            /*    // 生产对话框
                AlertDialog alertDialog = builder.create();
                alertDialog.setCanceledOnTouchOutside(false);
                // 显示对话框
                alertDialog.show();
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.rgb(88,190,252));
                CommonDialog commonDialog = new CommonDialog(getActivity());
                commonDialog.setMsg(updateVersionJson.getUpdateLogMsg())
                        .setTitle("是否更新版本")
                        .setRightBtnText("更新")
                        .setLeftBtnText("取消")
                        .setRightClick(v -> getTiltSensorChart(updateVersionJson.getUpdateDownLoadUrl()))
                        .show();*/
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

    @OnClick({R.id.btnLogin, R.id.login_save_password_tv, R.id.ivLoginSetting})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                mPresenter.login(mUserAccountTv.getText().toString(), mUserPasswordTv.getText().toString(), UserTypeId.getUserTypeId(UserInfoPref.getUserTypeId()).getTypeId());
                break;
            case R.id.login_save_password_tv:
                mIsSavePasswordCb.setChecked(!mIsSavePasswordCb.isChecked());
                break;
            case R.id.ivLoginSetting:
                LogonServerDialog.newInstance(getActivity())
                        .setLeftOnClick((url, port, userTypeId) -> {
                            UserInfoPref.setUrl(url);
                            UserInfoPref.setPort(port);
                            UserInfoPref.setUserTypeId(userTypeId.getIndex());
                        })
                        .setUrl(UserInfoPref.getUrl())
                        .setPort(UserInfoPref.getPort())
                        .setUserType(UserTypeId.getUserTypeId(UserInfoPref.getUserTypeId()))
                        .show();
                break;
        }
    }
}
