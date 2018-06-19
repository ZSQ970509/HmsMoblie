package com.hc.hmsmoblie.mvp.presenter;

import com.hc.hmsmoblie.bean.json.ProjectJson;
import com.hc.hmsmoblie.bean.json.VideoDriverJson;
import com.hc.hmsmoblie.mvp.contact.VideoSelectDriverC;
import com.hc.hmsmoblie.mvp.contact.VideoSelectProjectC;
import com.hc.hmsmoblie.mvp.model.VideoSelectDriverM;
import com.hc.hmsmoblie.mvp.model.VideoSelectProjectM;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.BasePresenter;

import java.util.ArrayList;

/**
 *
 */

public class VideoSelectDriverP extends BasePresenter<VideoSelectDriverC.V> implements VideoSelectDriverC.P{
    @Override
    public void getCameraListdetails(String projId, String systemid) {

        new VideoSelectDriverM()
                .getCameraListdetails(projId,systemid)
                .compose(getIView().bindLifecycle())
                .subscribe(new NetObserver<HttpResponse<ArrayList<VideoDriverJson>>>() {
                    @Override
                    public void onSuccess(HttpResponse<ArrayList<VideoDriverJson>> loginBean) {
                        getIView().hideLoading();
                        getIView().onGetCameraListdetailsSuccess(loginBean.getData());
                    }

                    @Override
                    public void onFail(ApiException msg) {
                        getIView().hideLoading();
                        getIView().onGetCameraListdetailsFail(msg.getMessage());
                    }
                });
    }
}
