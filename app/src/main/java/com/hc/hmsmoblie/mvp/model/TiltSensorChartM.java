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

public class TiltSensorChartM implements IModel{

    public Observable getTiltSensorChart(String camID, String paraID, String timeType, String selDate,int type) {
        return RetrofitUtils.Instance
                .getApiService(ApiServer.class)
                .getTiltSensorChart(camID,paraID,timeType,selDate,type)
                .compose(NetTransformer.compose());
    }
}
