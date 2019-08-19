package com.hc.hmsmoblie.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.base.BaseActivity;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.bean.json.ImageLogPanoramaListJson;
import com.hc.hmsmoblie.mvp.contact.ImageLogPanoramaListC;
import com.hc.hmsmoblie.mvp.presenter.ImageLogPanoramaListP;
import com.hc.hmsmoblie.widget.MatrixImageView;
import com.yc.yclibrary.base.YcMvpAppCompatActivity;
import com.yc.yclibrary.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 全局图详情页面(影响日志)
 */

public class ImageLogPanoramaDetailActivity extends BaseMvpActivity<ImageLogPanoramaListP> implements ImageLogPanoramaListC.V {
    @BindView(R.id.ivImageLogPanoramaDetails)
    MatrixImageView mIvPanorama;
    //    private String mImgUrl;
//    private String mPuzzleId;//全景图ID
//    private String mImageTimes;//轮数
    private static final String DATA = "data";
    private static final String PAGE_INDEX = "page_index";
    private static final String PAGE_TOTAL = "page_total";
    private static final String CAM_ID = "cam_id";
    private static final String START_TIME = "start_time";
    private static final String END_TIME = "end_time";
    private static final String SELECT_POSITION = "select_position";
    private int mPageIndex = 0;
    private final int mPageSize = 10;
    private int mPageTotal = 0;
    private int mSelectPosition = 0;
    private String mCamId;
    private String mStartTime;
    private String mEndTime;
    private ArrayList<ImageLogPanoramaListJson.ListBean> mData;

    public static void newInstance(Activity activity, ArrayList<ImageLogPanoramaListJson.ListBean> data, int pageIndex, int pageTotal, String camId, String startTime, String endTime, int position) {
        Intent intent = new Intent(activity, ImageLogPanoramaDetailActivity.class);
        intent.putExtra(DATA, data);
        intent.putExtra(PAGE_INDEX, pageIndex);
        intent.putExtra(PAGE_TOTAL, pageTotal);
        intent.putExtra(CAM_ID, camId);
        intent.putExtra(START_TIME, startTime);
        intent.putExtra(END_TIME, endTime);
        intent.putExtra(SELECT_POSITION, position);
        activity.startActivity(intent);
    }

    @Override
    protected ImageLogPanoramaListP loadPresenter() {
        return new ImageLogPanoramaListP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.image_log_panorama_details_activity;
    }

    @Override
    protected void initView(Bundle bundle) {
//        setToolBar("");
//        mActionBarRl.setBackgroundResource(R.color.colorTrance);
        mData = (ArrayList<ImageLogPanoramaListJson.ListBean>) getIntent().getSerializableExtra(DATA);
        mPageIndex = getIntent().getIntExtra(PAGE_INDEX, 0);
        mPageTotal = getIntent().getIntExtra(PAGE_TOTAL, 0);
        mCamId = getIntent().getStringExtra(CAM_ID);
        mStartTime = getIntent().getStringExtra(START_TIME);
        mEndTime = getIntent().getStringExtra(END_TIME);
        mSelectPosition = getIntent().getIntExtra(SELECT_POSITION, 0);
//        mImgUrl = "http://hms.jsqqy.com:7878/Handler/PanoramaHandler.ashx?action=GetPicByPuzzleAuto&path=http://ftp.jsqqy.com:8123/upfile/Puzzle/ptimg/thumbnailsAuto/155655669_001/330807_201806241320062845.jpg&recordId=330807";
        mIvPanorama.setOnDoubleClick(new MatrixImageView.OnDoubleClick() {
            @Override
            public void onClick(float pointInViewX, float pointInViewY, double scale, float originalImageHeight, float originalImageWidth) {
                //点在原始原图的位置
                double x = pointInViewX / scale;
                double y = pointInViewY / scale;
                //当双击的位置为空白部分时，显示提示
                if (x < 0 || x > originalImageWidth || y < 0 || y > originalImageHeight) {
                    showToast("请点击图片的位置（非空白处）");
                    return;
                }
                ImageLogWideAngleActivity.newInstance(getActivity(), mData.get(mSelectPosition).getRecordId() + "", mData.get(mSelectPosition).getImageTimes() + "", x + "", y + "");
            }
        });
    }

    boolean isLoaded = false;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //防止加载图片时，mIvPanorama的高宽还未获取到
        if (!isLoaded) {
            mIvPanorama.loadNetImage(mData.get(mSelectPosition).getThumbnailsAuto());
            isLoaded = true;
        }
    }

    //上一张
    private void before() {
        if (mSelectPosition > 0) {
            mSelectPosition--;
            mIvPanorama.loadNetImage(mData.get(mSelectPosition).getThumbnailsAuto());
        } else {
            showToast("已经是第一张！");
        }
    }

    //下一张
    private void next() {
        if (mSelectPosition < mData.size() - 1) {
            mSelectPosition++;
            mIvPanorama.loadNetImage(mData.get(mSelectPosition).getThumbnailsAuto());
        } else {
            mPageIndex++;
            if (mPageIndex >= mPageTotal) {
                showToast("已经是最后一张！");
            } else {
                mPresenter.getPanoramaList(mCamId, mStartTime, mEndTime, mPageIndex, mPageSize);
            }
        }
    }


    @Override
    public void onPanoramaListSuccess(ImageLogPanoramaListJson json) {
        mData.addAll(json.getList());
        mPageTotal = json.getTotal();
        next();
    }

    @Override
    public void onPanoramaListFail(ApiException apiException) {
        showToast(apiException.getMessage());
    }

    @OnClick({R.id.ivImageLogPanoramaDetailsNext, R.id.ivImageLogPanoramaDetailsBefore, R.id.ivImageLogPanoramaDetailsBack})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivImageLogPanoramaDetailsBefore:
                before();
                break;
            case R.id.ivImageLogPanoramaDetailsNext:
                next();
                break;
            case R.id.ivImageLogPanoramaDetailsBack:
                finish();
                break;
        }
    }
}
