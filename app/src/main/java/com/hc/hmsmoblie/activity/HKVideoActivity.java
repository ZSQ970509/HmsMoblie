package com.hc.hmsmoblie.activity;

import android.app.Activity;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;

import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.bean.domain.VideoBean;
import com.hc.hmsmoblie.bean.json.LoginJson;
import com.hc.hmsmoblie.db.UserInfoPref;
import com.hc.hmsmoblie.mvp.contact.LoginC;
import com.hc.hmsmoblie.mvp.presenter.LoginP;
import com.hc.hmsmoblie.utils.HKUtil;
import com.hc.hmsmoblie.utils.HuXinUtil;
import com.hc.hmsmoblie.widget.CommonDialog;
import com.hc.hmsmoblie.widget.CustomSurfaceView;
import com.hikvision.sdk.VMSNetSDK;
import com.hikvision.sdk.consts.SDKConstant;
import com.hikvision.sdk.net.bean.LoginData;
import com.hikvision.sdk.net.business.OnVMSNetSDKBusiness;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 */

public class HKVideoActivity extends BaseMvpActivity<LoginP> implements LoginC.V ,SurfaceHolder.Callback{
    @BindView(R.id.csv_Hk_Video)
    CustomSurfaceView csvHkVideo;
    private static final String Video_Bean = "video_bean";
    VideoBean videoBean;
    public static void newInstance(Activity activity, VideoBean videoBean) {
        Intent intent = new Intent(activity, HKVideoActivity.class);
        intent.putExtra(Video_Bean, videoBean);
        activity.startActivity(intent);
    }
    @Override
    protected LoginP loadPresenter() {
        return new LoginP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.video_hk_activity;
    }

    @Override
    protected void initView(Bundle bundle) {
        csvHkVideo.getHolder().addCallback(HKVideoActivity.this);
        videoBean = (VideoBean) getIntent().getSerializableExtra(Video_Bean);
        HKUtil.login(videoBean.getmIp(),videoBean.getmPort(),videoBean.getmUserName(),videoBean.getmPassword(),new OnVMSNetSDKBusiness(){
            @Override
            public void onFailure() {
//                    mHandler.sendEmptyMessage(LOGIN_FAILED);
                showToast("登录失败");
                Log.e("8700", "登录失败");
            }

            @Override
            public void onSuccess(Object obj) {
                //Log.e("8700", "登录成功");
                if (obj instanceof LoginData) {
                    int mStreamType = SDKConstant.LiveSDKConstant.MAIN_HIGH_STREAM; // 码流
                    HKUtil.start(videoBean.getmSysCode(), csvHkVideo, mStreamType);
                }
            }
        });

    }

    @Override
    public void onLoginSuccess(LoginJson loginJs) {
    }

    @Override
    public void onLoginFail(String msg) {
        showToast(msg);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean stopLiveResult = VMSNetSDK.getInstance().stopLiveOpt();
        if (stopLiveResult) {
            showToast("停止成功");
        }
    }
}
