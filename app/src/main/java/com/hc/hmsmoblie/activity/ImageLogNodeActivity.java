package com.hc.hmsmoblie.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.bean.json.ImageLogNodeJson;
import com.hc.hmsmoblie.mvp.contact.ImageLogNodeC;
import com.hc.hmsmoblie.mvp.presenter.ImageLogNodeP;
import com.hc.hmsmoblie.utils.LoadImgUtils;
import com.yc.yclibrary.exception.ApiException;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
    private String mCamSn;
    private static final String CAM_SN = "cam_sn";//设备
    private static final String PANORAMA_ID = "panorama_id";//全景图ID
    private static final String IMAGE_TIMES = "image_times";//轮数
    private static final String POINT_X = "point_x";//X轴
    private static final String POINT_Y = "point_y";//Y轴
    private static final String AHA = "aha";//水平角度
    private static final String AVA = "ava";//垂直角度

    public static void newInstance(Activity activity, String panoramaId, String imageTimes, String pointX, String pointY, String aha, String ava, String camSn) {
        Intent intent = new Intent(activity, ImageLogNodeActivity.class);
        intent.putExtra(PANORAMA_ID, panoramaId);
        intent.putExtra(IMAGE_TIMES, imageTimes);
        intent.putExtra(POINT_X, pointX);
        intent.putExtra(POINT_Y, pointY);
        intent.putExtra(AHA, aha);
        intent.putExtra(AVA, ava);
        intent.putExtra(CAM_SN, camSn);
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
        mPanoramaId = getIntent().getStringExtra(PANORAMA_ID);
        mImageTimes = getIntent().getStringExtra(IMAGE_TIMES);
        mPointX = getIntent().getStringExtra(POINT_X);
        mPointY = getIntent().getStringExtra(POINT_Y);
        mAha = getIntent().getStringExtra(AHA);
        mAva = getIntent().getStringExtra(AVA);
        mCamSn = getIntent().getStringExtra(CAM_SN);
//        mCamSn = "795652107";
//        mPanoramaId = "402685";
//        mImageTimes = "181";
//        mPointX = "814.12645358809";
//        mPointY = "557.9368911223929";
//        mAha = "0.0";
//        mAva = "0.0";
        mShowImageView[0][0] = mIvNode11;
        mShowImageView[0][1] = mIvNode12;
        mShowImageView[0][2] = mIvNode13;
        mShowImageView[1][0] = mIvNode21;
        mShowImageView[1][1] = mIvNode22;
        mShowImageView[1][2] = mIvNode23;
        mShowImageView[2][0] = mIvNode31;
        mShowImageView[2][1] = mIvNode32;
        mShowImageView[2][2] = mIvNode33;
        searchPro();
    }

    private void searchPro() {
        mPresenter.getNode(mCamSn, mPanoramaId, mImageTimes, mPointX, mPointY, mAha, mAva);
    }

    private ImageLogNodeJson.Data30Bean[][] mShowData;
    private ImageView[][] mShowImageView = new ImageView[3][3];

    @Override
    public void onNodeSuccess(ImageLogNodeJson json) {
        ImageLogNodeJson.DataCenterBean centerData = json.getDataCenter().get(0);//中心图片的数据
        int centerRowIndex = centerData.getRowNum();
        int centerColIndex = centerData.getColNum();
        int minRow = json.getData30().get(0).getRownum();
        int minCol = json.getData30().get(0).getColnum();
        List<ImageLogNodeJson.Data30Bean> data30 = json.getData30();
        for (int i = 0; i < data30.size(); i++) {
            if (minRow > data30.get(i).getRownum()) {
                minRow = data30.get(i).getRownum();
            }
            if (minCol > data30.get(i).getColnum()) {
                minCol = data30.get(i).getColnum();
            }
        }

        int maxRow = minRow + json.getRowSum30();
        int maxCol = minCol + json.getColSum30();
        mShowData = new ImageLogNodeJson.Data30Bean[json.getRowSum30()][json.getColSum30()];

        for (int i = 0; i < data30.size(); i++) {
            ImageLogNodeJson.Data30Bean temp = data30.get(i);
            mShowData[temp.getRownum() - minRow ][temp.getColnum() - minCol ] = temp;
        }
        //找中心点位置
        if (centerRowIndex >= maxRow) {
            centerRowIndex--;
        } else if (centerRowIndex <= minRow) {
            centerRowIndex++;
        }
        if (centerColIndex >= maxCol) {
            centerColIndex--;
        } else if (centerColIndex <= minCol) {
            centerColIndex++;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int row = centerRowIndex - minRow - 1 + i;
                int col = centerColIndex - minCol - 1 + j;
                if (row < 0 || row >= json.getRowSum30() || col < 0 || col >= json.getColSum30()) {
                    mShowImageView[i][j].setImageResource(R.drawable.img_fail);
                    continue;
                }
                ImageLogNodeJson.Data30Bean temp = mShowData[row][col];
                if (temp == null)
                    mShowImageView[i][j].setImageResource(R.drawable.img_fail);
                else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    LoadImgUtils.loadImg(getActivity(), temp.getImgpath(), mShowImageView[i][j]);
                    Log.e("asd","row"+row+" col"+col+" path"+temp.getImgpath());
                }
            }
        }
//        for (int i = 0; i < json.getData30().size(); i++) {
//            ImageLogNodeJson.Data30Bean data = json.getData30().get(i);
//            if (midRowIndex - 1 == data.getRownum() && midColIndex - 1 == data.getColnum()) {
//                LoadImgUtils.loadImg(getActivity(), data.getImgpath(), mIvNode11);
//            } else if (midRowIndex - 1 == data.getRownum() && midColIndex == data.getColnum()) {
//                LoadImgUtils.loadImg(getActivity(), data.getImgpath(), mIvNode12);
//            } else if (midRowIndex - 1 == data.getRownum() && midColIndex + 1 == data.getColnum()) {
//                LoadImgUtils.loadImg(getActivity(), data.getImgpath(), mIvNode13);
//            } else if (midRowIndex == data.getRownum() && midColIndex - 1 == data.getColnum()) {
//                LoadImgUtils.loadImg(getActivity(), data.getImgpath(), mIvNode21);
//            } else if (midRowIndex == data.getRownum() && midColIndex == data.getColnum()) {
//                LoadImgUtils.loadImg(getActivity(), data.getImgpath(), mIvNode22);
//            } else if (midRowIndex == data.getRownum() && midColIndex + 1 == data.getColnum()) {
//                LoadImgUtils.loadImg(getActivity(), data.getImgpath(), mIvNode23);
//            } else if (midRowIndex + 1 == data.getRownum() && midColIndex - 1 == data.getColnum()) {
//                LoadImgUtils.loadImg(getActivity(), data.getImgpath(), mIvNode31);
//            } else if (midRowIndex + 1 == data.getRownum() && midColIndex == data.getColnum()) {
//                LoadImgUtils.loadImg(getActivity(), data.getImgpath(), mIvNode32);
//            } else if (midRowIndex + 1 == data.getRownum() && midColIndex + 1 == data.getColnum()) {
//                LoadImgUtils.loadImg(getActivity(), data.getImgpath(), mIvNode33);
//            }
//        }
    }

    @Override
    public void onNodeFail(ApiException apiException) {
        showToast(apiException.getMessage());
    }

    @OnClick({R.id.ivImageLogNodeBack})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivImageLogNodeBack:
                finish();
                break;
        }
    }
}
