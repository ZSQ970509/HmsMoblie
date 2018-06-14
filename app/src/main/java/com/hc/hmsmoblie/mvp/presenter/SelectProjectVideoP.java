package com.hc.hmsmoblie.mvp.presenter;

import com.hc.hmsmoblie.bean.json.ProjectJson;
import com.hc.hmsmoblie.mvp.contact.SelectProjectVideoC;
import com.hc.hmsmoblie.mvp.model.SelectProjectVideoM;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.BasePresenter;

/**
 *
 */

public class SelectProjectVideoP extends BasePresenter<SelectProjectVideoC.V> implements SelectProjectVideoC.P{
    @Override
    public void getVideoProject(String keyword, int pageindex, int pagesize, String sysId, String userid) {
        getIView().showLoading("正在搜索中...");
        new SelectProjectVideoM()
                .getCameraList(keyword,pageindex,pagesize,sysId,userid)
                .compose(getIView().bindLifecycle())
                .subscribe(new NetObserver<HttpResponse<ProjectJson>>() {
                    @Override
                    public void onSuccess(HttpResponse<ProjectJson> loginBean) {
                        getIView().hideLoading();
                        getIView().onGetVideoProjectSuccess(loginBean.getData());
                    }

                    @Override
                    public void onFail(ApiException msg) {
                        getIView().hideLoading();
                        getIView().onGetVideoProjectFail(msg.getMessage());
                    }
                });
    }
}
