package com.hc.hmsmoblie.mvp.model;

import com.hc.hmsmoblie.net.ApiServer;
import com.yc.yclibrary.mvp.IModel;
import com.yc.yclibrary.net.NetTransformer;
import com.yc.yclibrary.net.RetrofitUtils;

import io.reactivex.Observable;

/**
 *
 */

public class LadderControlDetailsOperationM implements IModel {
    public Observable GetTowerCraneListByPaging(String devId, int pageIndex, int pageSize) {
        return RetrofitUtils.Instance
                .getApiService(ApiServer.class)
                .GetTowerCraneListByPaging(devId, "", pageIndex, pageSize)
                .compose(NetTransformer.compose());
    }
}
