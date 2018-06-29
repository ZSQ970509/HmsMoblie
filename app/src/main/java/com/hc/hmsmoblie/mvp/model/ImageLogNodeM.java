package com.hc.hmsmoblie.mvp.model;

import com.hc.hmsmoblie.net.ApiServer;
import com.yc.yclibrary.mvp.IModel;
import com.yc.yclibrary.net.NetTransformer;
import com.yc.yclibrary.net.RetrofitUtils;

import io.reactivex.Observable;

/**
 *
 */

public class ImageLogNodeM implements IModel {
    public Observable getNode(String camSn, String panoramaId, String imageTimes, String pointX, String pointY, String aha, String ava) {
        return RetrofitUtils.Instance
                .getApiService(ApiServer.class)
                .getNode(camSn, panoramaId, imageTimes, pointX, pointY, aha, ava)
                .compose(NetTransformer.compose());
    }
}
