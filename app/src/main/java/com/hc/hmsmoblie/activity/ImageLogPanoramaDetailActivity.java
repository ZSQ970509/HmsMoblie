package com.hc.hmsmoblie.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.base.BaseActivity;
import com.hc.hmsmoblie.widget.MatrixImageView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 全局图详情页面(影响日志)
 */

public class ImageLogPanoramaDetailActivity extends BaseActivity {
    @BindView(R.id.ivImageLogPanoramaDetails)
    MatrixImageView mIvPanorama;
    private String mImgUrl;
    private String mPuzzleId;//全景图ID
    private String mImageTimes;//轮数
    private static final String IMG_URL = "img_url";
    private static final String PUZZLE_ID = "puzzle_id";
    private static final String IMAGE_TIMES = "image_times";

    public static void newInstance(Activity activity, String imgUrl, String puzzleId, String imageTimes) {
        Intent intent = new Intent(activity, ImageLogPanoramaDetailActivity.class);
        intent.putExtra(IMG_URL, imgUrl);
        intent.putExtra(PUZZLE_ID, puzzleId);
        intent.putExtra(IMAGE_TIMES, imageTimes);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.image_log_panorama_details_activity;
    }

    @Override
    protected void initView(Bundle bundle) {
        setToolBar("");
        mActionBarRl.setBackgroundResource(R.color.colorTrance);
        mImgUrl = getIntent().getStringExtra(IMG_URL);
        mPuzzleId = getIntent().getStringExtra(PUZZLE_ID);
        mImageTimes = getIntent().getStringExtra(IMAGE_TIMES);
//        mImgUrl = "http://hms.jsqqy.com:7878/Handler/PanoramaHandler.ashx?action=GetPicByPuzzleAuto&path=http://ftp.jsqqy.com:8123/upfile/Puzzle/ptimg/thumbnailsAuto/155655669_001/330807_201806241320062845.jpg&recordId=330807";
        mIvPanorama.loadNetImage(mImgUrl);
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
                ImageLogWideAngleActivity.newInstance(getActivity(), mPuzzleId, mImageTimes, x + "", y + "");
            }
        });
    }
}
