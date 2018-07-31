package com.hc.hmsmoblie.mvp.presenter;

import com.hc.hmsmoblie.bean.json.ProjectJson;
import com.hc.hmsmoblie.mvp.contact.VideoSelectProjectC;
import com.hc.hmsmoblie.mvp.contact.VideoSelectProjectFragmentC;
import com.hc.hmsmoblie.mvp.model.VideoSelectProjectFragmentM;
import com.hc.hmsmoblie.mvp.model.VideoSelectProjectM;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.BasePresenter;

/**
 *
 */

public class VideoSelectProjectFragmentP extends BasePresenter<VideoSelectProjectFragmentC.V> implements VideoSelectProjectFragmentC.P{
    @Override
    public void getVideoProject(String keyword, int pageindex, int pagesize, String sysId, String userid) {

        new VideoSelectProjectFragmentM()
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
