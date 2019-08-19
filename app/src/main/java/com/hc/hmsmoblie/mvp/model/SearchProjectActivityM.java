package com.hc.hmsmoblie.mvp.model;

import com.hc.hmsmoblie.db.UserInfoPref;
import com.hc.hmsmoblie.net.ApiServer;
import com.yc.yclibrary.mvp.IModel;
import com.yc.yclibrary.net.NetTransformer;
import com.yc.yclibrary.net.RetrofitUtils;

import io.reactivex.Observable;

/**
 *
 */

public class SearchProjectActivityM implements IModel {
    public Observable getCameraList(String keyword, int pageindex, int pagesize, String sysId) {
        return RetrofitUtils.Instance
                .getApiService(ApiServer.class)
                .getCameraList(keyword, pageindex, pagesize, sysId, UserInfoPref.getUserId(), UserInfoPref.getUserAccount(), UserInfoPref.getUserHeadSculpture())
                .compose(NetTransformer.compose());
    }
}
