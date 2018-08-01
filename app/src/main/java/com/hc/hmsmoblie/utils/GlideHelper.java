package com.hc.hmsmoblie.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.hc.hmsmoblie.R;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 */

public class GlideHelper {
    private Queue<String> imgUrls = new LinkedList<String>();
    private Queue<ImageView> imageViews = new LinkedList<ImageView>();
    private WeakReference<Context> mContext;

    public GlideHelper(Context context) {
        mContext = new WeakReference<Context>(context);
    }

    public GlideHelper add(String imgUrl, ImageView imageView) {
        imgUrls.offer(imgUrl);
        imageViews.offer(imageView);
        return this;
    }

    public void start() {
        loadImg(imgUrls.poll(), imageViews.poll(), 0);
    }

    private void loadImg(String imgUrl, ImageView imageView, final int loadNum) {
        if (TextUtils.isEmpty(imgUrl) || imageView == null)
            return;
        Glide.with(mContext.get())
                .load(imgUrl)//拿到头像本地存放路径
//                .asBitmap()//避免图片变形
                .error(R.drawable.img_fail)//失败默认
                .placeholder(R.drawable.img_loading)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.NONE)//缓存
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        if (loadNum < 1)
                            loadImg(imgUrl, imageView, loadNum + 1);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        loadImg(imgUrls.poll(), imageViews.poll(), 0);
                        return false;
                    }
                })
//                .skipMemoryCache(true)
                .into(imageView);
    }
}
