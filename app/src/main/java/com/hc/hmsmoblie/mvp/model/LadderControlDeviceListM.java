package com.hc.hmsmoblie.mvp.model;

import com.hc.hmsmoblie.net.ApiServer;
import com.yc.yclibrary.mvp.IModel;
import com.yc.yclibrary.net.NetTransformer;
import com.yc.yclibrary.net.RetrofitUtils;

import io.reactivex.Observable;

/**
 *
 */

public class LadderControlDeviceListM implements IModel{
    public Observable getTowerCraneDevList(String keyword, int pageIndex, int pageSize, String proId) {
        return RetrofitUtils.Instance
                .getApiService(ApiServer.class)
                .getTowerCraneDevList(keyword, pageIndex,pageSize,proId)
                .compose(NetTransformer.compose());
    }
}
