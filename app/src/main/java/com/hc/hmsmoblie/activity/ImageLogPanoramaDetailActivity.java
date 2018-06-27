package com.hc.hmsmoblie.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.base.BaseActivity;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.bean.json.ImageLogPanoramaListJson;
import com.hc.hmsmoblie.mvp.contact.ImageLogPanoramaListC;
import com.hc.hmsmoblie.mvp.presenter.ImageLogPanoramaListP;
import com.hc.hmsmoblie.utils.LoadImgUtils;
import com.orhanobut.logger.Logger;
import com.yc.yclibrary.exception.ApiException;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 全局图详情页面(影响日志)
 */

public class ImageLogPanoramaDetailActivity extends BaseActivity {
    @BindView(R.id.ivImageLogPanoramaDetails)
    ImageView mIvPanorama;
    private String mImgUrl;
    private static final String IMG_Url = "img_url";

    public static void newInstance(Activity activity, String imgUrl) {
        Intent intent = new Intent(activity, ImageLogPanoramaDetailActivity.class);
        intent.putExtra(IMG_Url, imgUrl);
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
        mImgUrl = getIntent().getStringExtra(IMG_Url);
//        LoadImgUtils.loadImg(getActivity(), mImgUrl, mIvPanorama);
        Glide.with(getActivity())
                .load(mImgUrl)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                        //获取图片原始宽高
                        int originalWidth = resource.getWidth();//原始图的宽度
                        int originalHeight = resource.getHeight();//原始图的高度

                        //获取图片显示（即拉伸后的图）后的宽高
                        double currentWidth;//拉伸后图的宽度
                        double currentHeight;//拉伸后图的高度
                        if (originalWidth / originalHeight - mIvPanorama.getWidth() / mIvPanorama.getHeight() > 0) {//图片的宽大于容器的宽
                            double tmpHeight = mIvPanorama.getWidth() * originalHeight / originalWidth;
                            if (tmpHeight > mIvPanorama.getHeight()) {
                                currentWidth = mIvPanorama.getWidth() * mIvPanorama.getHeight() / tmpHeight;
                                currentHeight = mIvPanorama.getHeight();
                            } else {
                                currentWidth = mIvPanorama.getWidth();
                                currentHeight = tmpHeight;
                            }
                        } else {
                            double tmpWidth = mIvPanorama.getHeight() * originalWidth / originalHeight;
                            if (tmpWidth > mIvPanorama.getWidth()) {
                                currentWidth = mIvPanorama.getWidth();
                                currentHeight = mIvPanorama.getHeight() * mIvPanorama.getWidth() / tmpWidth;
                            } else {
                                currentWidth = tmpWidth;
                                currentHeight = mIvPanorama.getHeight();
                            }
                        }
                        Logger.e("当前：" + currentHeight + " " + currentWidth + " " + " 原始：" + originalHeight + " " + originalWidth + " 控件：" + mIvPanorama.getHeight() + " " + mIvPanorama.getWidth());
                        //压缩图片
                        Matrix matrix = new Matrix();
                        matrix.setScale((float) (currentWidth / resource.getWidth()), (float) (currentHeight / resource.getHeight()));
                        resource = Bitmap.createBitmap(resource, 0, 0, resource.getWidth(), resource.getHeight(), matrix, true);
                        mIvPanorama.setImageBitmap(resource);
                        Matrix ivMatrix = mIvPanorama.getMatrix();
                        ivMatrix.setTranslate((int) ((mIvPanorama.getWidth() - currentWidth) / 2), (int) ((mIvPanorama.getHeight() - currentHeight) / 2));
                        mIvPanorama.setImageMatrix(ivMatrix);
                    }
                });
    }


    @OnClick({R.id.ivImageLogPanoramaDetails})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivImageLogPanoramaDetails:
                ImageLogWideAngleActivity.newInstance(getActivity(), "", "", "", "");
                break;
        }
    }
}
