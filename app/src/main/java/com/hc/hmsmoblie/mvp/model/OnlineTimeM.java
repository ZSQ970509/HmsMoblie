package com.hc.hmsmoblie.mvp.model;

import com.hc.hmsmoblie.net.ApiServer;
import com.yc.yclibrary.mvp.IModel;
import com.yc.yclibrary.net.NetTransformer;
import com.yc.yclibrary.net.RetrofitUtils;

import io.reactivex.Observable;

/**
 *
 */

public class OnlineTimeM implements IModel{
    public Observable GetOnlineRate(String projId, String startDate, String endDate) {
        return RetrofitUtils.Instance
                .getApiService(ApiServer.class)
                .GetOnlineRate(projId,startDate,endDate)
                .compose(NetTransformer.compose());
    }
}
