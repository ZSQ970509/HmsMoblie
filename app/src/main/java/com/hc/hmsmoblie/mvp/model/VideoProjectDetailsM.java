package com.hc.hmsmoblie.mvp.model;

import com.hc.hmsmoblie.net.ApiServer;
import com.yc.yclibrary.mvp.IModel;
import com.yc.yclibrary.net.NetTransformer;
import com.yc.yclibrary.net.RetrofitUtils;

import io.reactivex.Observable;

/**
 *
 */

public class VideoProjectDetailsM implements IModel{
    public Observable getCameradetails(String projId, int systemid) {
        return RetrofitUtils.Instance
                .getApiService(ApiServer.class)
                .getCameradetails(projId,systemid)
                .compose(NetTransformer.compose());
    }
}
