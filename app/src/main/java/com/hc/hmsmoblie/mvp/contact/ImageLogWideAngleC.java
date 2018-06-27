package com.hc.hmsmoblie.mvp.contact;

import com.hc.hmsmoblie.bean.json.ImageLogPanoramaListJson;
import com.hc.hmsmoblie.bean.json.ImageLogWideAngleJson;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.IView;

/**
 *
 */

public class ImageLogWideAngleC {
    public interface V extends IView {
        void onWideAngleSuccess(ImageLogWideAngleJson json);

        void onWideAngleFail(ApiException apiException);
    }

    public interface P {
        void getWideAngle(String panoramaId, String imageTimes, String pointX, String pointY);
    }
}
