package com.hc.hmsmoblie.mvp.model;

import com.hc.hmsmoblie.net.ApiServer;
import com.yc.yclibrary.mvp.IModel;
import com.yc.yclibrary.net.NetTransformer;
import com.yc.yclibrary.net.RetrofitUtils;

import io.reactivex.Observable;

/**
 *
 */

public class WeighingMachineM implements IModel{
    public Observable getWeighbridgeList(String  projId,int pageIndex, int pageSize, String openingTimeBegin, String openingTimeEnd) {
        return RetrofitUtils.Instance
                .getApiService(ApiServer.class)
                .getWeighbridgeList(projId, pageIndex,pageSize,openingTimeBegin,openingTimeEnd)
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
