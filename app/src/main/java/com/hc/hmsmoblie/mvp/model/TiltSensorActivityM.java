package com.hc.hmsmoblie.mvp.model;

import com.hc.hmsmoblie.net.ApiServer;
import com.yc.yclibrary.mvp.IModel;
import com.yc.yclibrary.net.NetTransformer;
import com.yc.yclibrary.net.RetrofitUtils;

import io.reactivex.Observable;

/**
 *
 */

public class TiltSensorActivityM implements IModel {
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
}
