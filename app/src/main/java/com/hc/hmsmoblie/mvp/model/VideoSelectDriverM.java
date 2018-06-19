package com.hc.hmsmoblie.mvp.model;

import com.hc.hmsmoblie.net.ApiServer;
import com.yc.yclibrary.mvp.IModel;
import com.yc.yclibrary.net.NetTransformer;
import com.yc.yclibrary.net.RetrofitUtils;

import io.reactivex.Observable;

/**
 *
 */

public class VideoSelectDriverM implements IModel{
    public Observable getCameraListdetails(String projId, String systemid) {
        return RetrofitUtils.Instance
                .getApiService(ApiServer.class)
                .getCameraListdetails(projId,systemid)
                .compose(NetTransformer.compose());
    }
}
