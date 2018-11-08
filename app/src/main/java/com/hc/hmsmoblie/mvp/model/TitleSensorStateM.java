package com.hc.hmsmoblie.mvp.model;

import com.hc.hmsmoblie.net.ApiServer;
import com.yc.yclibrary.mvp.IModel;
import com.yc.yclibrary.net.NetTransformer;
import com.yc.yclibrary.net.RetrofitUtils;

import io.reactivex.Observable;

/**
 *
 */

public class TitleSensorStateM implements IModel{
    public Observable getTiltSensorState(String deviceId) {
        return RetrofitUtils.Instance
                .getApiService(ApiServer.class)
                .getTitleSensorState(deviceId)
                .compose(NetTransformer.compose());
    }

}
