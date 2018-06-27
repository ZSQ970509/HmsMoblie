package com.hc.hmsmoblie.mvp.presenter;

import com.hc.hmsmoblie.bean.json.ImageLogPanoramaListJson;
import com.hc.hmsmoblie.bean.type.EnvironmentParaTypeEnum;
import com.hc.hmsmoblie.mvp.contact.ImageLogPanoramaListC;
import com.hc.hmsmoblie.mvp.model.ImageLogPanoramaListM;
import com.hc.hmsmoblie.net.HttpResponse;
import com.hc.hmsmoblie.net.NetObserver;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.BasePresenter;

/**
 *
 */

public class ImageLogPanoramaListP extends BasePresenter<ImageLogPanoramaListC.V> implements ImageLogPanoramaListC.P {

    @Override
    public void getPanoramaList(String camId, String starTime, String endTime, int pageIndex, int pageSize) {
        new ImageLogPanoramaListM()
                .getPanoramaList(camId, starTime, endTime, pageIndex, pageSize)
                .compose(getIView().bindLifecycle())
                .subscribe(new NetObserver<HttpResponse<ImageLogPanoramaListJson>>() {
                    @Override
                    public void onSuccess(HttpResponse<ImageLogPanoramaListJson> loginBean) {
                        getIView().onPanoramaListSuccess(loginBean.getData());
                    }

                    @Override
                    public void onFail(ApiException msg) {
                        getIView().onPanoramaListFail(msg);
                    }
                });
    }
}
