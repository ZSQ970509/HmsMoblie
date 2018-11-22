package com.hc.hmsmoblie.mvp.model;

import com.hc.hmsmoblie.net.ApiServer;
import com.yc.yclibrary.mvp.IModel;
import com.yc.yclibrary.net.NetTransformer;
import com.yc.yclibrary.net.RetrofitUtils;

import io.reactivex.Observable;

/**
 *
 */

public class TiltSensorActivityMOld implements IModel {
    public Observable getGetTiltSensorPara(String cmID) {
        return RetrofitUtils.Instance
                .getApiService(ApiServer.class)
                .getGetTiltSensorPara(cmID)
                .compose(NetTransformer.compose());
    }

    public Observable getTiltSensorLog(String cmID, String paraID, int pageindex, int pagesize, String startTime, String endTime) {
        return RetrofitUtils.Instance
                .getApiService(ApiServer.class)
                .getTiltSensorLog(cmID, paraID, pageindex, pagesize, startTime, endTime)
                .compose(NetTransformer.compose());
    }

    public Observable setAllMessage(String paraID, String seq, String type) {
        return RetrofitUtils.Instance
                .getApiService(ApiServer.class)
                .setAllMessage(paraID, seq, type)
                .compose(NetTransformer.compose());
    }

    public Observable getTiltSensorState(String deviceId) {
        return RetrofitUtils.Instance
                .getApiService(ApiServer.class)
                .getTitleSensorState(deviceId)
                .compose(NetTransformer.compose());
    }

    public Observable setIotDeviceInfo(String deviceId, String serviceId, String method
            , String jsonCommand, int expireTime) {
        return RetrofitUtils.Instance
                .getApiService(ApiServer.class)
                .setIotDeviceInfo(deviceId, serviceId, method, jsonCommand, expireTime)
                .compose(NetTransformer.compose());
    }
}
