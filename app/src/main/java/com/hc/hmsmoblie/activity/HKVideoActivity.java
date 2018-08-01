package com.hc.hmsmoblie.activity;

import android.app.Activity;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.bean.domain.VideoBean;
import com.hc.hmsmoblie.bean.json.LoginJson;
import com.hc.hmsmoblie.bean.json.UpdateVersionJson;
import com.hc.hmsmoblie.db.UserInfoPref;
import com.hc.hmsmoblie.mvp.contact.LoginC;
import com.hc.hmsmoblie.mvp.presenter.LoginP;
import com.hc.hmsmoblie.utils.AnimationUtil;
import com.hc.hmsmoblie.utils.HKUtil;
import com.hc.hmsmoblie.utils.HuXinUtil;
import com.hc.hmsmoblie.widget.CommonDialog;
import com.hc.hmsmoblie.widget.CustomSurfaceView;
import com.hikvision.sdk.VMSNetSDK;
import com.hikvision.sdk.consts.SDKConstant;
import com.hikvision.sdk.net.bean.LoginData;
import com.hikvision.sdk.net.business.OnVMSNetSDKBusiness;
import com.yc.yclibrary.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 */

public class HKVideoActivity extends BaseMvpActivity<LoginP> implements LoginC.V, SurfaceHolder.Callback {
    @BindView(R.id.ivHuXinBack)
    ImageView ivHuXinBack;
    @BindView(R.id.control_Main)
    ImageView imageView_main;
    @BindView(R.id.control_Setting)
    ImageView controlSetting;
    @BindView(R.id.control_Direction)
    ImageView controlDirection;
    @BindView(R.id.control_Up)
    ImageView controlUp;
    @BindView(R.id.control_Left)
    ImageView controlLeft;
    @BindView(R.id.control_Down)
    ImageView controlDown;
    @BindView(R.id.control_Right)
    ImageView controlRight;
    @BindView(R.id.csv_Hk_Video)
    CustomSurfaceView csvHkVideo;
    @BindView(R.id.layout_pross)
    public View layoutPross;
    /**
     * 是否正在云台控制
     */
    private boolean mIsPtzStart;
    /**
     * 云台控制命令
     */
    private int mPtzCommand;
    int type = 0;
    int controlType = 0;
    String speedNum = "1";
    private static final String Video_Bean = "video_bean";
    private CommonDialog mCommonDialog;
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
        mCommonDialog = CommonDialog.newInstanceSingle(getActivity());
        csvHkVideo.getHolder().addCallback(HKVideoActivity.this);
        videoBean = (VideoBean) getIntent().getSerializableExtra(Video_Bean);
        HKUtil.login(videoBean.getmIp(), videoBean.getmPort(), videoBean.getmUserName(), videoBean.getmPassword(), new OnVMSNetSDKBusiness() {
            @Override
            public void onFailure() {
//                    mHandler.sendEmptyMessage(LOGIN_FAILED);
                //showToast("登录失败");
                layoutPross.setVisibility(View.GONE);
                Log.e("8700", "登录失败");
                if (mCommonDialog == null)
                    return;
                mCommonDialog
                        .setTitle("播放提示")
                        .setMsg("登录失败")
                        .setSingleBtnText("确定")
                        .setSingleClick(v -> finish())
                        .show();

            }

            @Override
            public void onSuccess(Object obj) {
                //Log.e("8700", "登录成功");
                if (getActivity() != ActivityUtils.INSTANCE.getCurrentActivity())
                    return;
                layoutPross.setVisibility(View.GONE);
                if (obj instanceof LoginData) {
                    int mStreamType = SDKConstant.LiveSDKConstant.MAIN_HIGH_STREAM; // 码流
                    HKUtil.start(videoBean.getmSysCode(), csvHkVideo, mStreamType, (BaseMvpActivity) getActivity());
                }
            }
        });
        mPtzCommand = SDKConstant.PTZCommandConstant.CUSTOM_CMD_UP;
    }

    @Override
    public void onLoginSuccess(LoginJson loginJs) {
    }

    @Override
    public void onLoginFail(String msg) {
        showToast(msg);
    }

    @Override
    public void onUpdatedVersionSuccess(UpdateVersionJson httpResponse) {

    }

    @Override
    public void onUpdatedVersionFail(String msg) {

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

    @Override
    protected void onDestroy() {
        if (mCommonDialog != null) {
            mCommonDialog.dismiss();
            mCommonDialog = null;
        }
        super.onDestroy();
    }

    @OnClick({R.id.ivHuXinBack, R.id.control_Main, R.id.control_Setting, R.id.control_Direction, R.id.control_Up, R.id.control_Left, R.id.control_Down, R.id.control_Right})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivHuXinBack:
                finish();
                break;
            case R.id.control_Main:
                switch (type) {
                    case 0:
                        AnimationUtil.LeftRotation(imageView_main);
                        AnimationUtil.getShowAlpha(controlSetting);
                        AnimationUtil.getShowAlpha(controlDirection);
                        type = 1;
                        break;
                    case 1:
                        AnimationUtil.rightRotation(imageView_main);
                        AnimationUtil.getDismissAlpha(controlSetting);
                        AnimationUtil.getDismissAlpha(controlDirection);

                        AnimationUtil.getDismissAlpha(controlUp);
                        AnimationUtil.getDismissAlpha(controlLeft);
                        AnimationUtil.getDismissAlpha(controlDown);
                        AnimationUtil.getDismissAlpha(controlRight);
                        type = 0;
                        break;
                    case 2:
                        AnimationUtil.rightRotation(imageView_main);

                        type = 0;
                        break;
                }
                break;
            case R.id.control_Setting:
                controlUp.setImageResource(R.drawable.big);
                controlLeft.setImageResource(R.drawable.bright);
                controlDown.setImageResource(R.drawable.small);
                controlRight.setImageResource(R.drawable.dark);
                AnimationUtil.rightAroundRotation(imageView_main);
                AnimationUtil.getDismissAndShowAlpha(controlSetting, controlUp);
                AnimationUtil.getDismissAndShowAlpha(controlSetting, controlLeft);
                AnimationUtil.getDismissAndShowAlpha(controlDirection, controlDown);
                AnimationUtil.getDismissAndShowAlpha(controlDirection, controlRight);

                controlType = 0;
                break;
            case R.id.control_Direction:
                controlUp.setImageResource(R.drawable.up);
                controlLeft.setImageResource(R.drawable.left);
                controlDown.setImageResource(R.drawable.down);
                controlRight.setImageResource(R.drawable.right);
                AnimationUtil.rightAroundRotation(imageView_main);
                AnimationUtil.getDismissAndShowAlpha(controlSetting, controlUp);
                AnimationUtil.getDismissAndShowAlpha(controlSetting, controlLeft);
                AnimationUtil.getDismissAndShowAlpha(controlDirection, controlDown);
                AnimationUtil.getDismissAndShowAlpha(controlDirection, controlRight);

                controlType = 1;
                break;
            case R.id.control_Up:
                if (controlType == 1) {
                    mPtzCommand = SDKConstant.PTZCommandConstant.CUSTOM_CMD_UP;
                    ptzBtnOnClick();
                    //splay.control(videoBean.getmUserName(), "1", speedNum);//1 向上
                } else {
                    mPtzCommand = SDKConstant.PTZCommandConstant.CUSTOM_CMD_ZOOM_IN;
                    ptzBtnOnClick();
                    // splay.control(videoBean.getmUserName(), "7", speedNum);//7 放大
                }
                break;
            case R.id.control_Left:
                if (controlType == 1) {
                    mPtzCommand = SDKConstant.PTZCommandConstant.CUSTOM_CMD_LEFT;
                    ptzBtnOnClick();
                    // splay.control(videoBean.getmUserName(), "3", speedNum);//2 向左
                } else {
                    mPtzCommand = SDKConstant.PTZCommandConstant.CUSTOM_CMD_IRIS_UP;
                    ptzBtnOnClick();
                    //splay.control(videoBean.getmUserName(), "5", speedNum);//5 变亮
                }
                break;
            case R.id.control_Down:
                if (controlType == 1) {
                    mPtzCommand = SDKConstant.PTZCommandConstant.CUSTOM_CMD_DOWN;
                    ptzBtnOnClick();
                    // splay.control(videoBean.getmUserName(), "2", speedNum);//3 向下
                } else {
                    mPtzCommand = SDKConstant.PTZCommandConstant.CUSTOM_CMD_ZOOM_OUT;
                    ptzBtnOnClick();
                    //splay.control(videoBean.getmUserName(), "8", speedNum);//8 缩小
                }
                break;
            case R.id.control_Right:
                if (controlType == 1) {
                    mPtzCommand = SDKConstant.PTZCommandConstant.CUSTOM_CMD_RIGHT;
                    ptzBtnOnClick();
                    // splay.control(videoBean.getmUserName(), "4", speedNum);//4 向右
                } else {
                    mPtzCommand = SDKConstant.PTZCommandConstant.CUSTOM_CMD_IRIS_DOWN;
                    ptzBtnOnClick();
                    // splay.control(videoBean.getmUserName(), "6", speedNum);//6 变暗
                }
                break;
        }
    }

    /***
     * 云台控制按钮点击
     */
    private void ptzBtnOnClick() {
        if (mPtzCommand != SDKConstant.PTZCommandConstant.CUSTOM_CMD_GOTO_PRESET
                && mPtzCommand != SDKConstant.PTZCommandConstant.CUSTOM_CMD_CLE_PRESET
                && mPtzCommand != SDKConstant.PTZCommandConstant.CUSTOM_CMD_SET_PRESET) {
            //开始云台操作
            VMSNetSDK.getInstance().sendPTZCtrlCommand(true, SDKConstant.PTZCommandConstant.ACTION_START, mPtzCommand, 256, new OnVMSNetSDKBusiness() {
                @Override
                public void onFailure() {
                    Toast.makeText(getActivity(), "控制失败_1，请重试！", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess(Object obj) {
                    //停止云台操作
                    VMSNetSDK.getInstance().sendPTZCtrlCommand(true, SDKConstant.PTZCommandConstant.ACTION_STOP, mPtzCommand, 256, new OnVMSNetSDKBusiness() {
                        @Override
                        public void onFailure() {
                            Toast.makeText(getActivity(), "控制失败_2，请重试！", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onSuccess(Object obj) {
                            if (mPtzCommand != SDKConstant.PTZCommandConstant.CUSTOM_CMD_GOTO_PRESET
                                    && mPtzCommand != SDKConstant.PTZCommandConstant.CUSTOM_CMD_CLE_PRESET
                                    && mPtzCommand != SDKConstant.PTZCommandConstant.CUSTOM_CMD_SET_PRESET) {
                                Toast.makeText(getActivity(), "控制成功，请重试！", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            });


        }
    }

}
