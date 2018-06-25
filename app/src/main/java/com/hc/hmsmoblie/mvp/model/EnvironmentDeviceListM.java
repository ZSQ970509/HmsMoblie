package com.hc.hmsmoblie.mvp.model;

import com.hc.hmsmoblie.net.ApiServer;
import com.yc.yclibrary.mvp.IModel;
import com.yc.yclibrary.net.NetTransformer;
import com.yc.yclibrary.net.RetrofitUtils;

import io.reactivex.Observable;

/**
 *
 */

public class EnvironmentDeviceListM implements IModel {
    public Observable getEnvironmentDeviceList(String projId) {
        return RetrofitUtils.Instance
                .getApiService(ApiServer.class)
                .getEnvironmentDeviceList(projId,"21")
                .compose(NetTransformer.compose());
    }
}
