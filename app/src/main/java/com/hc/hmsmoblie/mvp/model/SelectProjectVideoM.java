package com.hc.hmsmoblie.mvp.model;

import com.hc.hmsmoblie.net.ApiServer;
import com.yc.yclibrary.mvp.IModel;
import com.yc.yclibrary.net.NetTransformer;
import com.yc.yclibrary.net.RetrofitUtils;

import io.reactivex.Observable;

/**
 *
 */

public class SelectProjectVideoM implements IModel{
    public Observable getCameraList(String keyword, String pageindex, String pagesize, String sysId, String userid) {
        return RetrofitUtils.Instance
                .getApiService(ApiServer.class)
                .getCameraList(keyword, pageindex,pagesize,sysId,userid)
                .compose(NetTransformer.compose());
    }
}
