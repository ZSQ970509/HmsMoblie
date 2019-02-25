package com.hc.hmsmoblie.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.text.TextUtils;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.utils.LoadImgUtils;
import com.yc.yclibrary.base.YcAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */

public class ShowImgActivity extends YcAppCompatActivity {
    @BindView(R.id.showImgIv)
    ImageView showImgIv;
    @BindView(R.id.showImgRl)
    RelativeLayout showImgRl;
    private static final String IMG_URL_KEY = "img_url_key";

    public static void newInstance(Activity activity, String imgUrl) {
        Intent intent = new Intent(activity, ShowImgActivity.class);
        intent.putExtra(IMG_URL_KEY, imgUrl);
        activity.startActivity(intent);
    }

    public static void newInstance(Activity activity, String imgUrl, ActivityOptionsCompat optionsCompat) {
        Intent intent = new Intent(activity, ShowImgActivity.class);
        intent.putExtra(IMG_URL_KEY, imgUrl);
        activity.startActivity(intent, optionsCompat.toBundle());
    }

    @Override
    protected int getLayoutId() {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS); // 必须
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setAllowReturnTransitionOverlap(false);
            getWindow().setAllowEnterTransitionOverlap(false);
        }
        return R.layout.activity_show_img;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCompat.finishAfterTransition(this);
    }
    @Override
    protected void initView(Bundle bundle) {
        String imgUrl = getIntent().getStringExtra(IMG_URL_KEY);
        if (TextUtils.isEmpty(imgUrl)) {
            showToast("图片地址为空");
        } else
            LoadImgUtils.loadImg(getActivity(), imgUrl, showImgIv);
        showImgRl.setOnClickListener(v -> onBackPressed());
        showImgIv.setOnClickListener(v -> onBackPressed());
    }

}
