package com.hc.hmsmoblie.mvp.model;

import com.hc.hmsmoblie.net.ApiServer;
import com.yc.yclibrary.mvp.IModel;
import com.yc.yclibrary.net.NetTransformer;
import com.yc.yclibrary.net.RetrofitUtils;

import io.reactivex.Observable;

/**
 *
 */

public class DipRealTimeDataM implements IModel {
    public Observable getGetTiltSensorPara(String cmID) {
        return RetrofitUtils.Instance
                .getApiService(ApiServer.class)
                .getGetTiltSensorPara(cmID)
                .compose(NetTransformer.compose());
    }
}
