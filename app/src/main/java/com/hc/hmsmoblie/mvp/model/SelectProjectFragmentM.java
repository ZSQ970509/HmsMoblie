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

public class SelectProjectFragmentM implements IModel {
    public Observable getProListByArea(String folderId, int folderLevel, int pageIndex, int pageSize, String sysId) {
        return RetrofitUtils.Instance
                .getApiService(ApiServer.class)
                .getProListByArea(folderId, folderLevel, pageIndex, pageSize, sysId, UserInfoPref.getUserId(), UserInfoPref.getUserHeadSculpture())
                .compose(NetTransformer.compose());
    }
}
