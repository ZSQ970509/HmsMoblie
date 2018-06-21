package com.hc.hmsmoblie.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.base.BaseActivity;
import com.hc.hmsmoblie.utils.LoadImgUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 查看人脸对比图
 */

public class LadderControlPictureActivity extends BaseActivity {
    @BindView(R.id.tvLadderControlPictureName)
    TextView mTvName;
    @BindView(R.id.ivLadderControlPictureImg1)
    ImageView mIv1;
    @BindView(R.id.ivLadderControlPictureImg2)
    ImageView mIv2;

    private static String NAME = "name";
    private static String IMG_URL1 = "img_url1";
    private static String IMG_URL2 = "img_url2";

    public static void newInstance(Activity activity, String name, String imgUrl1, String imgUrl2, ActivityOptionsCompat optionsCompat) {
        Intent intent = new Intent(activity, LadderControlPictureActivity.class);
        intent.putExtra(NAME, name);
        intent.putExtra(IMG_URL1, imgUrl1);
        intent.putExtra(IMG_URL2, imgUrl2);
        activity.startActivity(intent, optionsCompat.toBundle());
    }

    @Override
    protected int getLayoutId() {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS); // 必须
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setAllowReturnTransitionOverlap(false);
            getWindow().setAllowEnterTransitionOverlap(false);
        }
        return R.layout.ladder_control_picture_activity;
    }

    @Override
    protected void initView(Bundle bundle) {
        Intent intent = getIntent();
        mTvName.setText("人脸查看(" + intent.getStringExtra(NAME)+")");
        LoadImgUtils.loadImg(getActivity(), intent.getStringExtra(IMG_URL1), mIv1);
        LoadImgUtils.loadImg(getActivity(), intent.getStringExtra(IMG_URL2), mIv2);
    }

    @OnClick({R.id.btnLadderControlPictureClose})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLadderControlPictureClose:
                finish();
                break;
        }
    }
}
