package com.hc.hmsmoblie.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.bean.json.LoginJson;
import com.hc.hmsmoblie.bean.json.UpdateVersionJson;
import com.hc.hmsmoblie.db.UserInfoPref;
import com.hc.hmsmoblie.mvp.contact.AboutC;
import com.hc.hmsmoblie.mvp.contact.LoginC;
import com.hc.hmsmoblie.mvp.presenter.AboutP;
import com.hc.hmsmoblie.mvp.presenter.LoginP;
import com.hc.hmsmoblie.utils.PhoneSystemUtils;
import com.hc.hmsmoblie.widget.CommonDialog;
import com.yc.yclibrary.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 */

public class AboutActivity extends BaseMvpActivity<AboutP> implements AboutC.V {
    @BindView(R.id.textview_NowVersion)
    TextView textviewNowVersion;
    @BindView(R.id.imageView_Update_Btn)
    ImageView imageViewUpdateBtn;
    public static void newInstance(Activity activity) {
        activity.startActivity(new Intent(activity, AboutActivity.class));
    }
    @Override
    protected AboutP loadPresenter() {
        return new AboutP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle bundle) {
        setToolBar("关于");
        textviewNowVersion.setText("当前版本："+PhoneSystemUtils.getPackageName());
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
                        .setRightClick(v -> updatedVersion(updateVersionJson.getUpdateDownLoadUrl()));
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

    @OnClick({R.id.imageView_Update_Btn})
    void onClick(View v) {
        switch (v.getId()){
            case R.id.imageView_Update_Btn:
                mPresenter.updatedVersion();
                break;
            default:
                break;
        }
    }
}
