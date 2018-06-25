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

public class EnvironmentDetailsM implements IModel {
    public Observable getPara(String _camId, String seqId, String date, String projId, String para,String timeType) {
        return RetrofitUtils.Instance
                .getApiService(ApiServer.class)
                .getPara(_camId, seqId, date, projId, para,timeType)
                .compose(NetTransformer.compose());
    }
}
