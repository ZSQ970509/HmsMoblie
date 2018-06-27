package com.hc.hmsmoblie.mvp.model;

import com.hc.hmsmoblie.net.ApiServer;
import com.yc.yclibrary.mvp.IModel;
import com.yc.yclibrary.net.NetTransformer;
import com.yc.yclibrary.net.RetrofitUtils;

import io.reactivex.Observable;

/**
 *
 */

public class MainM implements IModel{
    public Observable GetModulesList(String userAccount, String userID) {
        return RetrofitUtils.Instance
                .getApiService(ApiServer.class)
                .GetModulesList(userAccount, userID)
                .compose(NetTransformer.compose());
    }
}
