package com.hc.hmsmoblie.mvp.model;

import com.hc.hmsmoblie.net.ApiServer;
import com.yc.yclibrary.mvp.IModel;
import com.yc.yclibrary.net.NetTransformer;
import com.yc.yclibrary.net.RetrofitUtils;

import io.reactivex.Observable;

/**
 *
 */

public class ChangePassWordM implements IModel{
    public Observable changeHmsPassWord(String userAccount, String UserPwd, String newPwd) {
        return RetrofitUtils.Instance
                .getApiService(ApiServer.class)
                .changeHmsPassWord(userAccount, UserPwd,newPwd)
                .compose(NetTransformer.compose());
    }
}
