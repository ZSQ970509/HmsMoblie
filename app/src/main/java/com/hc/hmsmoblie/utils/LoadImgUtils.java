package com.hc.hmsmoblie.utils;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.hc.hmsmoblie.R;
import com.yc.yclibrary.utils.ActivityUtils;

import java.util.List;
import java.util.Stack;

import io.reactivex.Observable;

/**
 *
 */

public class LoadImgUtils {
    public static void loadImg(Activity activity, String imgUrl, ImageView imageView) {
        loadImg(activity, imgUrl, imageView, 0);
    }

    public static void loadImg(final Activity activity, final String imgUrl, final ImageView imageView, final int loadNum) {
        Activity curr = ActivityUtils.INSTANCE.getCurrentActivity();
        if (activity == null|| TextUtils.isEmpty(imgUrl) || imageView == null) {
            return;
        }
        Glide.with(activity)
                .load(imgUrl)//拿到头像本地存放路径
//                .asBitmap()//避免图片变形
                .error(R.drawable.img_fail)//失败默认
                .placeholder(R.drawable.img_loading)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.NONE)//缓存
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        if (loadNum < 3)
                            loadImg(activity, imgUrl, imageView, loadNum + 1);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(imageView);
    }
//    public static void loadImgNew(Activity activity, String imgUrl, ImageView imageView) {
//        Glide.with(activity)
//                .load(imgUrl)//拿到头像本地存放路径
//                .asBitmap()//避免图片变形
//                .error(R.drawable.image)//失败默认
//                .placeholder(R.drawable.ic_loading)
//                .fitCenter()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存
//                .skipMemoryCache(true)
//                .into(imageView);
//    }
}
