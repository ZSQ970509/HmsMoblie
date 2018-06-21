package com.hc.hmsmoblie.activity;

import android.app.Activity;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.CheckBox;

import com.ffcs.surfingscene.function.SurfingScenePlayer;
import com.ffcs.surfingscene.function.onPlayListener;
import com.ffcs.surfingscene.http.HttpCallBack;
import com.ffcs.surfingscene.response.BaseResponse;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.bean.domain.VideoBean;
import com.hc.hmsmoblie.bean.json.LoginJson;
import com.hc.hmsmoblie.db.UserInfoPref;
import com.hc.hmsmoblie.mvp.contact.LoginC;
import com.hc.hmsmoblie.mvp.presenter.LoginP;
import com.hc.hmsmoblie.utils.HuXinUtil;
import com.hc.hmsmoblie.widget.CommonDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 */

public class HuXinVideoActivity extends BaseMvpActivity<LoginP> implements LoginC.V {
    @BindView(R.id.glv_HuXin_Video)
    GLSurfaceView glvHuXinVideo;
    private static final String Video_Bean = "video_bean";
    VideoBean videoBean;
    public SurfingScenePlayer splay;
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
        //glvHuXinVideo = new GLSurfaceView(this);
        HuXinUtil.initVideoSDK(this,videoBean.getmUserName(), videoBean.getmPassword(),new HttpCallBack<BaseResponse>() {
            @Override
            public void callBack(BaseResponse arg0, String arg1) {

                if ("1".equals(arg0.getReturnCode())) {
                    hideLoading();
                    HuXinUtil.initVideo(splay,glvHuXinVideo,"086591-1435552375",videoBean.getmUserName(),new  onPlayListener() {

                        @Override
                        public void setOnPlaysuccess() {
                            //prossTV.setText("视频缓冲进度：100%");
                            //layoutPross.setVisibility(View.GONE);
                        }

                        @Override
                        public void onPlayFail(int arg0, final String error) {
                            //layoutPross.setVisibility(View.GONE);
                            // DialogUtil.showDialog(PuIdPlayerActivity.this, error);
                            CommonDialog.newInstance(getActivity())
                                    .setTitle("提示")
                                    .setMsg("播放失败："+error)
                                    .setSingleBtnText("确定")
                                    .show();
                            //showErrorDialog(HuXinVideoActivity.this,"播放失败："+error);
                            //上传错误信息
                            //sendErrorData(error);

                        }
                        @Override
                        public void onBufferProssgress(int bufferValue) {

                            if (bufferValue >= 99) {
                                //prossTV.setText("视频缓冲进度：100%");
                                //layoutPross.setVisibility(View.GONE);
                            } else {
                                //prossTV.setText("视频缓冲进度：" + bufferValue + "%");
                            }
                        }
                    });
                } else {
                    hideLoading();
                    CommonDialog.newInstance(getActivity())
                            .setTitle("播放提示")
                            .setMsg("播放失败："+arg0.getMsg())
                            .setSingleBtnText("确定")
                            .show();
                  /*  new AlertDialog.Builder(HuXinVideoActivity.this).setTitle("播放提示")
                            .setMessage("播放失败："+arg0.getMsg()).setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                }
                            })
                            .setCancelable(false).create().show();*/

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

    @OnClick()
    void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
