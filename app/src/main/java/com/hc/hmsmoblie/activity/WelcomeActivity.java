package com.hc.hmsmoblie.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.base.BaseActivity;
import com.hc.hmsmoblie.utils.PermissionsUtils;
import com.yc.yclibrary.base.YcAppCompatActivity;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 *  欢迎页面
 */

public class WelcomeActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView(Bundle bundle) {
        PermissionsUtils.requestPermissions(this, PermissionsUtils.PERMISSION_BLUETOOTH, new PermissionsUtils.PermissionCall() {
            @Override
            public void onSuccess() {
                //延迟两秒后跳转
                Observable.timer(3, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(l -> toLoginActivity());
            }

            @Override
            public void onFail() {
                showToast("权限被拒绝，无法启动！");
            }
        });

    }
    public void toLoginActivity(){
        startActivity(new Intent(getActivity(),LoginActivity.class));
        finish();
    }

}
