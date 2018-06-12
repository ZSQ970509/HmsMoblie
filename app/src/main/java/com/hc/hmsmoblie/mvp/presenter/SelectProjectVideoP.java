package com.hc.hmsmoblie.mvp.presenter;

import com.hc.hmsmoblie.bean.domain.ProjectVideoBean;
import com.hc.hmsmoblie.mvp.contact.LoginC;
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
    public void getVideoProject(String keyword, String pageindex, String pagesize, String sysId, String userid) {
        getIView().showLoading("正在登陆中...");
        new SelectProjectVideoM()
                .getCameraList(keyword,pageindex,pagesize,sysId,userid)
                .compose(getIView().bindLifecycle())
                .subscribe(new NetObserver<HttpResponse<ProjectVideoBean>>() {
                    @Override
                    public void onSuccess(HttpResponse<ProjectVideoBean> loginBean) {
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
