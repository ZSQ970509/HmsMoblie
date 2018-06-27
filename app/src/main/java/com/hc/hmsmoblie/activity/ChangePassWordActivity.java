package com.hc.hmsmoblie.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.bean.json.LoginJson;
import com.hc.hmsmoblie.db.UserInfoPref;
import com.hc.hmsmoblie.mvp.contact.ChangePassWordC;
import com.hc.hmsmoblie.mvp.contact.LoginC;
import com.hc.hmsmoblie.mvp.presenter.ChangePassWordP;
import com.hc.hmsmoblie.mvp.presenter.LoginP;
import com.hc.hmsmoblie.net.HttpResponse;
import com.yc.yclibrary.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 */

public class ChangePassWordActivity extends BaseMvpActivity<ChangePassWordP> implements ChangePassWordC.V {
    @BindView(R.id.editText_Change_OldPassWord)
    EditText editTextChangeOldPassWord;
    @BindView(R.id.editText_Change_NewPassWord)
    EditText editTextChangeNewPassWord;
    @BindView(R.id.editText_Change_NewPassWord_Again)
    EditText editTextChangeNewPassWordAgain;
    public static void newInstance(Activity activity) {
        activity.startActivity(new Intent(activity, ChangePassWordActivity.class));
    }
    @Override
    protected ChangePassWordP loadPresenter() {
        return new ChangePassWordP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.password_activity;
    }

    @Override
    protected void initView(Bundle bundle) {
        setToolBar("修改密码");
    }
    @OnClick({R.id.imageView_Change_Submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView_Change_Submit:
                if (TextUtils.isEmpty(editTextChangeOldPassWord.getText().toString())) {
                    showToast("未填写旧密码，请先填写旧密码！");
                    return;
                }
                if (TextUtils.isEmpty(editTextChangeNewPassWord.getText().toString())) {
                    showToast("未填写新密码，请先填写新密码！");
                    return;
                }
                if (TextUtils.isEmpty(editTextChangeNewPassWordAgain.getText().toString())) {
                    showToast("未填写确认密码，请先填写确认密码！");
                    return;
                }
                if (!editTextChangeNewPassWord.getText().toString().equals(editTextChangeNewPassWordAgain.getText().toString())) {
                    showToast("新密码两次输入的不同，请检查重试！");
                    return;
                }
               if (editTextChangeOldPassWord.getText().toString().equals(editTextChangeNewPassWord.getText().toString())) {
                    showToast("新密码与旧密码相同，请检查重试！");
                    return;
                }
                //showProgressDialogWithText("正在修改中，请稍候...");
                mPresenter.changeHmsPassWord(UserInfoPref.getUserAccount(),editTextChangeOldPassWord.getText().toString(), editTextChangeNewPassWord.getText().toString());
                break;
            default:
                break;
        }
    }

    @Override
    public void onChangeHmsPassWordSuccess(HttpResponse loginJs) {

        LoginActivity.newInstance(getActivity());
    }

    @Override
    public void onChangeHmsPassWordFail(String msg) {
        showToast(msg);
    }
}
