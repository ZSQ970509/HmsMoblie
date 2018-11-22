package com.hc.hmsmoblie.mvp.model;

import com.hc.hmsmoblie.bean.json.TiltSensorAllJson;
import com.hc.hmsmoblie.bean.json.TiltSensorJson;
import com.hc.hmsmoblie.net.ApiServer;
import com.hc.hmsmoblie.net.HttpResponse;
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
    public Observable<HttpResponse<TiltSensorAllJson>> getTitAll(String paraID) {
        return RetrofitUtils.Instance
                .getApiService(ApiServer.class)
                .getTitAll(paraID)
                .compose(NetTransformer.compose());
    }
}
