package com.hc.hmsmoblie.utils;

import android.app.Activity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hc.hmsmoblie.R;

/**
 *
 */

public class LoadImgUtils {
    public static void loadImg( Activity activity, String imgUrl, ImageView imageView) {
        Glide.with(activity)
                .load(imgUrl)//拿到头像本地存放路径
//                .asBitmap()//避免图片变形
                .error(R.drawable.img_fail)//失败默认
                .placeholder(R.drawable.img_loading)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.NONE)//缓存
//                .skipMemoryCache(true)
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
