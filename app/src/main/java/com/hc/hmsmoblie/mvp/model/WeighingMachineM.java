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

public class WeighingMachineM implements IModel {
    public Observable getWeighbridgeList(String projId, int pageIndex, int pageSize, String openingTimeBegin, String openingTimeEnd,
                                         String supplier, String merchandise, String weighing, double weigh) {
        //type	int	1.	大于  2.等于  3.小于(默认大于)
        return RetrofitUtils.Instance
                .getApiService(ApiServer.class)
                .getWeighbridgeList(projId, pageIndex, pageSize, openingTimeBegin, openingTimeEnd, supplier, merchandise, weighing, 1, weigh)
                .compose(NetTransformer.compose());
    }

    public Observable getWeighbridge(String recordId) {
        return RetrofitUtils.Instance
                .getApiService(ApiServer.class)
                .getWeighbridge(recordId)
                .compose(NetTransformer.compose());
    }

    public Observable getWeighGroupList(String proId) {
        return RetrofitUtils.Instance
                .getApiService(ApiServer.class)
                .getWeighGroupList(proId)
                .compose(NetTransformer.compose());
    }
}
