package com.hc.hmsmoblie.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.bean.json.ImageLogWideAngleJson;
import com.hc.hmsmoblie.mvp.contact.ImageLogWideAngleC;
import com.hc.hmsmoblie.mvp.presenter.ImageLogWideAngleP;
import com.hc.hmsmoblie.utils.LoadImgUtils;
import com.hc.hmsmoblie.widget.MatrixImageView;
import com.yc.yclibrary.exception.ApiException;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 广角图页面(影响日志)
 */

public class ImageLogWideAngleActivity extends BaseMvpActivity<ImageLogWideAngleP> implements ImageLogWideAngleC.V {
    @BindView(R.id.ivImageLogWideAngle)
    MatrixImageView mIvWideAngle;

    private String mPanoramaId;
    private String mImageTimes;
    private String mPointX;
    private String mPointY;
    private static final String PANORAMA_ID = "panorama_id";//全景图ID
    private static final String IMAGE_TIMES = "image_times";//轮数
    private static final String POINT_X = "point_x";//X轴
    private static final String POINT_Y = "point_y";//Y轴

    private ImageLogWideAngleJson mData;

    public static void newInstance(Activity activity, String panoramaId, String imageTimes, String pointX, String pointY) {
        Intent intent = new Intent(activity, ImageLogWideAngleActivity.class);
        intent.putExtra(PANORAMA_ID, panoramaId);
        intent.putExtra(IMAGE_TIMES, imageTimes);
        intent.putExtra(POINT_X, pointX);
        intent.putExtra(POINT_Y, pointY);
        activity.startActivity(intent);
    }

    @Override
    protected ImageLogWideAngleP loadPresenter() {
        return new ImageLogWideAngleP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.image_log_wide_angle_activity;
    }

    @Override
    protected void initView(Bundle bundle) {
        setToolBar("");
        mActionBarRl.setBackgroundResource(R.color.colorTrance);
        mPanoramaId = getIntent().getStringExtra(PANORAMA_ID);
        mImageTimes = getIntent().getStringExtra(IMAGE_TIMES);
        mPointX = getIntent().getStringExtra(POINT_X);
        mPointY = getIntent().getStringExtra(POINT_Y);
        searchPro();
    }

    private void searchPro() {
        mPresenter.getWideAngle(mPanoramaId, mImageTimes, mPointX, mPointY);
    }

    @Override
    public void onWideAngleSuccess(ImageLogWideAngleJson json) {
        mData = json;
        mIvWideAngle.loadNetImage(mData.getPath());
        mIvWideAngle.setOnDoubleClick(new MatrixImageView.OnDoubleClick() {
            @Override
            public void onClick(float pointInViewX, float pointInViewY, double scale) {
                //点在原始原图的位置
                double x = pointInViewX / scale;
                double y = pointInViewY / scale;
                ImageLogNodeActivity.newInstance(getActivity(), mPanoramaId, mData.getImageTimes(), x + "", y + "", mData.getAha(), mData.getAva(), mData.getCamSn());
            }
        });
    }

    @Override
    public void onWideAngleFail(ApiException apiException) {
        showToast(apiException.getMessage());
    }

//    @OnClick({R.id.ivImageLogWideAngle})
//    void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.ivImageLogWideAngle:
//                ImageLogNodeActivity.newInstance(getActivity(), "", "", "", "", "", "");
//                break;
//        }
//    }
}
