package com.hc.hmsmoblie.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ffcs.surfingscene.function.GeyeUserLogin;
import com.ffcs.surfingscene.function.SurfingScenePlayer;
import com.ffcs.surfingscene.function.onPlayListener;
import com.ffcs.surfingscene.http.HttpCallBack;
import com.ffcs.surfingscene.response.BaseResponse;
import com.ffcs.surfingscene.util.PublicUtils;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.bean.domain.VideoBean;
import com.hc.hmsmoblie.bean.json.LoginJson;
import com.hc.hmsmoblie.bean.json.UpdateVersionJson;
import com.hc.hmsmoblie.db.UserInfoPref;
import com.hc.hmsmoblie.mvp.contact.LoginC;
import com.hc.hmsmoblie.mvp.presenter.LoginP;
import com.hc.hmsmoblie.utils.AnimationUtil;
import com.hc.hmsmoblie.utils.HuXinUtil;
import com.hc.hmsmoblie.widget.CommonDialog;
import com.yc.yclibrary.utils.ActivityUtils;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 */

public class HuXinVideoActivity extends BaseMvpActivity<LoginP> implements LoginC.V {
    @BindView(R.id.ivHuXinBack)
    ImageView ivHuXinBack;
    @BindView(R.id.control_Main)
    ImageView imageView_main;
    @BindView(R.id.control_Speed)
    ImageView controlSpeed;
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
    @BindView(R.id.glv_HuXin_Video)
    GLSurfaceView glvHuXinVideo;
    @BindView(R.id.layout_pross)
    public View layoutPross;
    @BindView(R.id.txt_pross)
    public TextView prossTV;
    private static final String Video_Bean = "video_bean";
    VideoBean videoBean;
    public SurfingScenePlayer splay;
    int type = 0;
    int controlType = 0;
    String speedNum = "1";
    public static void newInstance(Activity activity, VideoBean videoBean) {
        Intent intent = new Intent(activity, HuXinVideoActivity.class);
        intent.putExtra(Video_Bean, videoBean);
        activity.startActivity(intent);
    }
    @Override
    protected LoginP loadPresenter() {
        return new LoginP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.video_hu_xin_activity;
    }

    @Override
    protected void initView(Bundle bundle) {

        videoBean = (VideoBean) getIntent().getSerializableExtra(Video_Bean);
        splay = new SurfingScenePlayer(this);

        glvHuXinVideo = (GLSurfaceView) this.findViewById(R.id.glv_HuXin_Video);
        showLoading("正在加载中...");
       // initVideoSDK();
       HuXinUtil.initVideoSDK(this,videoBean.getmUserName(), videoBean.getmPassword(),new HttpCallBack<BaseResponse>() {
            @Override
            public void callBack(BaseResponse arg0, String arg1) {
                if(getActivity()!= ActivityUtils.INSTANCE.getCurrentActivity())
                    return ;
                if ("1".equals(arg0.getReturnCode())) {
                    hideLoading();
                    HuXinUtil.initVideo(splay,glvHuXinVideo,videoBean.getVideoId(),videoBean.getmUserName(),new  onPlayListener() {

                        @Override
                        public void setOnPlaysuccess() {
                            if(getActivity()!= ActivityUtils.INSTANCE.getCurrentActivity())
                                return ;
                            prossTV.setText("视频缓冲进度：100%");
                            layoutPross.setVisibility(View.GONE);
                            if(splay != null) {
                                splay.changetofullScreen();
                            }
                        }

                        @Override
                        public void onPlayFail(int arg0, final String error) {
                            if(getActivity()!= ActivityUtils.INSTANCE.getCurrentActivity())
                                return ;
                            layoutPross.setVisibility(View.GONE);
                             //DialogUtil.showDialog(PuIdPlayerActivity.this, error);
                            CommonDialog.newInstanceSingle(getActivity())
                                    .setTitle("提示")
                                    .setMsg("播放失败："+error)
                                    .setSingleBtnText("确定")
                                    .setSingleClick(v -> finish())
                                    .show();
                            //showErrorDialog(HuXinVideoActivity.this,"播放失败："+error);
                            //上传错误信息
                            //sendErrorData(error);

                        }
                        @Override
                        public void onBufferProssgress(int bufferValue) {
                            if(getActivity()!= ActivityUtils.INSTANCE.getCurrentActivity())
                                return ;
                            if (bufferValue >= 99) {
                                prossTV.setText("视频缓冲进度：100%");
                                layoutPross.setVisibility(View.GONE);
                            } else {
                                prossTV.setText("视频缓冲进度：" + bufferValue + "%");
                            }
                        }
                    });
                } else {
                    if(getActivity()!= ActivityUtils.INSTANCE.getCurrentActivity())
                        return ;
                    hideLoading();
                    CommonDialog.newInstanceSingle(getActivity())
                            .setTitle("播放提示")
                            .setMsg("播放失败："+arg0.getMsg())
                            .setSingleBtnText("确定")
                            .setSingleClick(v -> finish())
                            .show();

                }
            }
        });

    }
    private void initVideo() {

        splay = new SurfingScenePlayer(this);
        glvHuXinVideo = (GLSurfaceView) this.findViewById(R.id.glv_HuXin_Video);

        setContentView(glvHuXinVideo);
        glvHuXinVideo.setVisibility(View.VISIBLE);
        splay.init(glvHuXinVideo);
        splay.getProgressValue();

        splay.setPlayListener(new onPlayListener() {

            @Override
            public void setOnPlaysuccess() {
                if(getActivity()!= ActivityUtils.INSTANCE.getCurrentActivity())
                    return ;
                prossTV.setText("视频缓冲进度：100%");
                layoutPross.setVisibility(View.GONE);
            }

            @Override
            public void onPlayFail(int arg0, final String error) {
                if(getActivity()!= ActivityUtils.INSTANCE.getCurrentActivity())
                    return ;
                layoutPross.setVisibility(View.GONE);
                // DialogUtil.showDialog(PuIdPlayerActivity.this, error);

               //s showErrorDialog(HuXinVideoActivity.this,"播放失败："+error);
                //上传错误信息
                //sendErrorData(error);

            }


            @Override
            public void onBufferProssgress(int bufferValue) {
                if(getActivity()!= ActivityUtils.INSTANCE.getCurrentActivity())
                    return ;
                if (bufferValue >= 99) {
                    prossTV.setText("视频缓冲进度：100%");
                    layoutPross.setVisibility(View.GONE);
                } else {
                    prossTV.setText("视频缓冲进度：" + bufferValue + "%");
                }
            }
        });

        splay.playerVideoByPuId("086591-1435552375", 2, "350100", 2, 1, videoBean.getmUserName(), 1);
        //splay.playerVideoByPuId(ivms_8700_bean.getCam_Dx_Puid(), 2, "350100", 2, 1, ivms_8700_bean.getmUserName(), 1);
    }
    public void initVideoSDK(){
        PublicUtils.savekey(this, "fzhc", "fzhc1234");//设置appkey和appSecret
        showLoading("正在加载中...");
        GeyeUserLogin.getInstance(HuXinVideoActivity.this).userLogin(videoBean.getmUserName(), Long.valueOf(videoBean.getmPassword()),
                350100, new HttpCallBack<BaseResponse>() {
                    @Override
                    public void callBack(BaseResponse arg0, String arg1) {
                        if(getActivity()!= ActivityUtils.INSTANCE.getCurrentActivity())
                            return ;
                        if ("1".equals(arg0.getReturnCode())) {
                            hideLoading();
                            initVideo();
                        } else {

                            hideLoading();
                            new AlertDialog.Builder(HuXinVideoActivity.this).setTitle("播放提示")
                                    .setMessage("播放失败："+arg0.getMsg()).setPositiveButton("确定",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog,
                                                            int which) {
                                            finish();
                                        }
                                    })
                                    .setCancelable(false).create().show();

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
    public void onUpdatedVersionSuccess(UpdateVersionJson httpResponse) {

    }

    @Override
    public void onUpdatedVersionFail(String msg) {

    }

    @OnClick({R.id.ivHuXinBack,R.id.control_Main,R.id.control_Speed,R.id.control_Setting,R.id.control_Direction,R.id.control_Up,R.id.control_Left,R.id.control_Down,R.id.control_Right})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivHuXinBack:
                finish();
                break;
            case R.id.control_Main:
                switch (type){
                    case 0:
                        AnimationUtil.LeftRotation(imageView_main);
                        AnimationUtil.getShowAlpha(controlSpeed);
                        AnimationUtil.getShowAlpha(controlSetting);
                        AnimationUtil.getShowAlpha(controlDirection);
                        type = 1;
                        break;
                    case 1:
                        AnimationUtil.rightRotation(imageView_main);
                        AnimationUtil.getDismissAlpha(controlSpeed);
                        AnimationUtil.getDismissAlpha(controlSetting);
                        AnimationUtil.getDismissAlpha(controlDirection);
                        type = 2;
                        break;
                    case 2:
                        AnimationUtil.rightRotation(imageView_main);
                        AnimationUtil.getDismissAlpha(controlUp);
                        AnimationUtil.getDismissAlpha(controlLeft);
                        AnimationUtil.getDismissAlpha(controlDown);
                        AnimationUtil.getDismissAlpha(controlRight);
                        type = 0;
                        break;
                }
                break;
            case R.id.control_Speed:
                speedDialogShow();
                break;
            case R.id.control_Setting:
                controlUp.setImageResource(R.drawable.big);
                controlLeft.setImageResource(R.drawable.bright);
                controlDown.setImageResource(R.drawable.small);
                controlRight.setImageResource(R.drawable.dark);
                AnimationUtil.rightAroundRotation(imageView_main);
                AnimationUtil.getDismissAndShowAlpha(controlSpeed,controlUp);
                AnimationUtil.getDismissAndShowAlpha(controlSetting,controlLeft);
                AnimationUtil.getDismissAndShowAlpha(controlDirection,controlDown);
                AnimationUtil.getDismissAndShowAlpha(controlSpeed,controlRight);

                controlType = 0;
                break;
            case R.id.control_Direction:
                controlUp.setImageResource(R.drawable.up);
                controlLeft.setImageResource(R.drawable.left);
                controlDown.setImageResource(R.drawable.down);
                controlRight.setImageResource(R.drawable.right);
                AnimationUtil.rightAroundRotation(imageView_main);
                AnimationUtil.getDismissAndShowAlpha(controlSpeed,controlUp);
                AnimationUtil.getDismissAndShowAlpha(controlSetting,controlLeft);
                AnimationUtil.getDismissAndShowAlpha(controlDirection,controlDown);
                AnimationUtil.getDismissAndShowAlpha(controlSpeed,controlRight);

                controlType = 1;
                break;
            case R.id.control_Up:
                if(controlType == 1){
                    splay.control(videoBean.getmUserName(), "1", speedNum);//1 向上
                }else{
                    splay.control(videoBean.getmUserName(), "7", speedNum);//7 放大
                }
                break;
            case R.id.control_Left:
                if(controlType == 1){
                    splay.control(videoBean.getmUserName(), "3", speedNum);//2 向左
                }else{
                    splay.control(videoBean.getmUserName(), "5", speedNum);//5 变亮
                }
                break;
            case R.id.control_Down:
                if(controlType == 1){
                    splay.control(videoBean.getmUserName(), "2", speedNum);//3 向下
                }else{
                    splay.control(videoBean.getmUserName(), "8", speedNum);//8 缩小
                }
                break;
            case R.id.control_Right:
                if(controlType == 1){
                    splay.control(videoBean.getmUserName(), "4", speedNum);//4 向右
                }else{
                    splay.control(videoBean.getmUserName(), "6", speedNum);//6 变暗
                }
                break;
        }
    }
    private void speedDialogShow() {
        String dqSpeedNum = speedNum;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View v = inflater.inflate(R.layout.dialog_seekbar, null);
        Button btn_sure = (Button) v.findViewById(R.id.dialog_btn_sure);
        Button btn_cancel = (Button) v.findViewById(R.id.dialog_btn_cancel);
        SeekBar sbSpeedDialog = (SeekBar) v.findViewById(R.id.sb_Speed_Dialog);
        sbSpeedDialog.setProgress(Integer.parseInt(speedNum));
        TextView tvSpeedDialog = (TextView) v.findViewById(R.id.tv_Speed_Dialog);
        //builer.setView(v);//这里如果使用builer.setView(v)，自定义布局只会覆盖title和button之间的那部分
        final Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setContentView(v);//自定义布局应该在这里添加，要在dialog.show()的后面
        //dialog.getWindow().setGravity(Gravity.CENTER);//可以设置显示的位置
        btn_sure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                speedNum = dqSpeedNum;
                dialog.dismiss();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                dialog.dismiss();

            }
        });
        sbSpeedDialog.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                tvSpeedDialog.setText("当前转速："+ (progress+1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        splay.playEnd();
        splay.unReceiver();

        System.gc();
        super.onDestroy();
    }
}
