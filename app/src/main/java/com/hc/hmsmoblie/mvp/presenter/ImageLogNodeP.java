package com.hc.hmsmoblie.mvp.presenter;

import com.hc.hmsmoblie.bean.json.ImageLogNodeJson;
import com.hc.hmsmoblie.mvp.contact.ImageLogNodeC;
import com.hc.hmsmoblie.mvp.model.ImageLogNodeM;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.BasePresenter;

/**
 *
 */

public class ImageLogNodeP extends BasePresenter<ImageLogNodeC.V> implements ImageLogNodeC.P {

    @Override
    public void getNode(String panoramaId, String imageTimes, String pointX, String pointY, String aha, String ava) {
        new ImageLogNodeM()
                .getNode(panoramaId, imageTimes, pointX, pointY, aha, ava)
                .compose(getIView().bindLifecycle())
                .subscribe(new NetObserver<HttpResponse<ImageLogNodeJson>>() {
                    @Override
                    public void onSuccess(HttpResponse<ImageLogNodeJson> loginBean) {
                        getIView().onNodeSuccess(loginBean.getData());
                    }
                    @Override
                    public void onFail(ApiException msg) {
                        getIView().onNodeFail(msg);
                    }
                });
    }
}
