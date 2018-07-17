package com.hc.hmsmoblie.mvp.model;

import com.hc.hmsmoblie.net.ApiServer;
import com.yc.yclibrary.mvp.IModel;
import com.yc.yclibrary.net.NetTransformer;
import com.yc.yclibrary.net.RetrofitUtils;

import io.reactivex.Observable;

/**
 *
 */

public class LoginM implements IModel{
    public Observable login(String userAccount, String userPassword) {
        return RetrofitUtils.Instance
                .getApiService(ApiServer.class)
                .login(userAccount, userPassword,"20")
                .compose(NetTransformer.compose());
    }
    public Observable updatedVersion(String packageName) {
        return RetrofitUtils.Instance
                .getApiService(ApiServer.class)
                .updatedVersion(packageName)
                .compose(NetTransformer.compose());
    }
}
