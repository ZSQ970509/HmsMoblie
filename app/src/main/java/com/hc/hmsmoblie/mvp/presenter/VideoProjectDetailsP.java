package com.hc.hmsmoblie.mvp.presenter;

import com.hc.hmsmoblie.bean.json.ProjectDetailsJson;
import com.hc.hmsmoblie.bean.json.ProjectJson;
import com.hc.hmsmoblie.mvp.contact.VideoProjectDetailsC;
import com.hc.hmsmoblie.mvp.model.VideoProjectDetailsM;
import com.hc.hmsmoblie.mvp.model.VideoSelectProjectM;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.BasePresenter;

import java.util.ArrayList;

/**
 *
 */

public class VideoProjectDetailsP extends BasePresenter<VideoProjectDetailsC.V> implements VideoProjectDetailsC.P{
    @Override
    public void getCameradetails(String projId, int systemid) {
        getIView().showLoading("正在加载中，请稍后...");
        new VideoProjectDetailsM()
                .getCameradetails(projId,systemid)
                .compose(getIView().bindLifecycle())
                .subscribe(new NetObserver<HttpResponse<ArrayList<ProjectDetailsJson>>>() {
                    @Override
                    public void onSuccess(HttpResponse<ArrayList<ProjectDetailsJson>> loginBean) {
                        getIView().hideLoading();
                        getIView().onGetCameradetailsSuccess(loginBean.getData());
                    }

                    @Override
                    public void onFail(ApiException msg) {
                        getIView().hideLoading();
                        getIView().onGetCameradetailsFail(msg.getMessage());
                    }
                });
    }
}
