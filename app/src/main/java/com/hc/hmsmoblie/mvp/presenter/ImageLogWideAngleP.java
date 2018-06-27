package com.hc.hmsmoblie.mvp.presenter;

import com.hc.hmsmoblie.bean.json.ImageLogWideAngleJson;
import com.hc.hmsmoblie.mvp.contact.ImageLogWideAngleC;
import com.hc.hmsmoblie.mvp.model.ImageLogWideAngleM;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.BasePresenter;

/**
 *
 */

public class ImageLogWideAngleP extends BasePresenter<ImageLogWideAngleC.V> implements ImageLogWideAngleC.P {

    @Override
    public void getWideAngle(String panoramaId, String imageTimes, String pointX, String pointY) {
        new ImageLogWideAngleM()
                .getPanoramaList(panoramaId, imageTimes, pointX, pointY)
                .compose(getIView().bindLifecycle())
                .subscribe(new NetObserver<HttpResponse<ImageLogWideAngleJson>>() {
                    @Override
                    public void onSuccess(HttpResponse<ImageLogWideAngleJson> loginBean) {
                        getIView().onWideAngleSuccess(loginBean.getData());
                    }

                    @Override
                    public void onFail(ApiException msg) {
                        getIView().onWideAngleFail(msg);
                    }
                });
    }
}
