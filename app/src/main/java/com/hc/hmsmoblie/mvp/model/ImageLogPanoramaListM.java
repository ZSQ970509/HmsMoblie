package com.hc.hmsmoblie.mvp.model;

import com.hc.hmsmoblie.net.ApiServer;
import com.yc.yclibrary.mvp.IModel;
import com.yc.yclibrary.net.NetTransformer;
import com.yc.yclibrary.net.RetrofitUtils;

import io.reactivex.Observable;
import retrofit2.http.Field;

/**
 *
 */

public class ImageLogPanoramaListM implements IModel {
    public Observable getPanoramaList(String camId, String starTime, String endTime, int pageIndex, int pageSize) {
        return RetrofitUtils.Instance
                .getApiService(ApiServer.class)
                .getPanoramaList(camId, starTime, endTime, pageIndex, pageSize)
                .compose(NetTransformer.compose());
    }
}
