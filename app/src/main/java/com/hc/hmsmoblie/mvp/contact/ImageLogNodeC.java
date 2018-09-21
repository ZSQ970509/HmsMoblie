package com.hc.hmsmoblie.mvp.contact;

import com.hc.hmsmoblie.bean.json.ImageLogNodeJson;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.IView;

/**
 *
 */

public class ImageLogNodeC {
    public interface V extends IView {
        void onNodeSuccess(ImageLogNodeJson json);
        void onNodeFail(ApiException apiException);
    }

    public interface P {
        void getNode(String camSn,String panoramaId, String imageTimes, String pointX, String pointY, String aha, String ava);
    }
}
