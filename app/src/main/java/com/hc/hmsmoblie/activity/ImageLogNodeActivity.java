package com.hc.hmsmoblie.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.bean.json.ImageLogNodeJson;
import com.hc.hmsmoblie.mvp.contact.ImageLogNodeC;
import com.hc.hmsmoblie.mvp.presenter.ImageLogNodeP;
import com.yc.yclibrary.exception.ApiException;

import butterknife.BindView;

/**
 * 节点图页面(影响日志)
 */

public class ImageLogNodeActivity extends BaseMvpActivity<ImageLogNodeP> implements ImageLogNodeC.V {
    @BindView(R.id.ivImageLogNode11)
    ImageView mIvNode11;
    @BindView(R.id.ivImageLogNode12)
    ImageView mIvNode12;
    @BindView(R.id.ivImageLogNode13)
    ImageView mIvNode13;
    @BindView(R.id.ivImageLogNode21)
    ImageView mIvNode21;
    @BindView(R.id.ivImageLogNode22)
    ImageView mIvNode22;
    @BindView(R.id.ivImageLogNode23)
    ImageView mIvNode23;
    @BindView(R.id.ivImageLogNode31)
    ImageView mIvNode31;
    @BindView(R.id.ivImageLogNode32)
    ImageView mIvNode32;
    @BindView(R.id.ivImageLogNode33)
    ImageView mIvNode33;
    private String mPanoramaId;
    private String mImageTimes;
    private String mPointX;
    private String mPointY;
    private String mAha;
    private String mAva;
    private static final String PANORAMA_ID = "panorama_id";//全景图ID
    private static final String IMAGE_TIMES = "image_times";//轮数
    private static final String POINT_X = "point_x";//X轴
    private static final String POINT_Y = "point_y";//Y轴
    private static final String AHA = "aha";//水平角度
    private static final String AVA = "ava";//垂直角度

    public static void newInstance(Activity activity, String panoramaId, String imageTimes, String pointX, String pointY, String aha, String ava) {
        Intent intent = new Intent(activity, ImageLogNodeActivity.class);
        intent.putExtra(PANORAMA_ID, panoramaId);
        intent.putExtra(IMAGE_TIMES, imageTimes);
        intent.putExtra(POINT_X, pointX);
        intent.putExtra(POINT_Y, pointY);
        intent.putExtra(POINT_Y, aha);
        intent.putExtra(POINT_Y, ava);
        activity.startActivity(intent);
    }

    @Override
    protected ImageLogNodeP loadPresenter() {
        return new ImageLogNodeP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.image_log_node_activity;
    }

    @Override
    protected void initView(Bundle bundle) {
        setToolBar("");
        mActionBarRl.setBackgroundResource(R.color.colorTrance);
        mPanoramaId = getIntent().getStringExtra(PANORAMA_ID);
        mImageTimes = getIntent().getStringExtra(IMAGE_TIMES);
        mPointX = getIntent().getStringExtra(POINT_X);
        mPointY = getIntent().getStringExtra(POINT_Y);
        mAha = getIntent().getStringExtra(AHA);
        mAva = getIntent().getStringExtra(AVA);
        searchPro();
    }

    private void searchPro() {
        mPresenter.getNode(mPanoramaId, mImageTimes, mPointX, mPointY,mAha,mAva);
    }

    @Override
    public void onNodeSuccess(ImageLogNodeJson json) {

    }

    @Override
    public void onNodeFail(ApiException apiException) {
        showToast(apiException.getMessage());
    }
//    @OnClick({R.id.tvImageLogPanoramaSearch})
//    void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.tvImageLogPanoramaSearch:
//                break;
//        }
//    }
}
