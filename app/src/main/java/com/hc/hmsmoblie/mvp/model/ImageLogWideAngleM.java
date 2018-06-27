package com.hc.hmsmoblie.mvp.model;

import com.hc.hmsmoblie.net.ApiServer;
import com.yc.yclibrary.mvp.IModel;
import com.yc.yclibrary.net.NetTransformer;
import com.yc.yclibrary.net.RetrofitUtils;

import io.reactivex.Observable;

/**
 *
 */

public class ImageLogWideAngleM implements IModel {
    public Observable getPanoramaList(String panoramaId, String imageTimes, String pointX, String pointY) {
        return RetrofitUtils.Instance
                .getApiService(ApiServer.class)
                .getWideAngle(panoramaId, imageTimes, pointX, pointY)
                .compose(NetTransformer.compose());
    }
}
