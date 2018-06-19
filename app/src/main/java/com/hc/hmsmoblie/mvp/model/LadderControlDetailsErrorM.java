package com.hc.hmsmoblie.mvp.model;

import com.hc.hmsmoblie.net.ApiServer;
import com.yc.yclibrary.mvp.IModel;
import com.yc.yclibrary.net.NetTransformer;
import com.yc.yclibrary.net.RetrofitUtils;

import io.reactivex.Observable;

/**
 *
 */

public class LadderControlDetailsErrorM implements IModel {
    public Observable GetErrlog(String proId, int pageIndex, int pageSize) {
        return RetrofitUtils.Instance
                .getApiService(ApiServer.class)
                .GetErrlog(proId, "", pageIndex, pageSize)
                .compose(NetTransformer.compose());
    }
}
