package com.hc.hmsmoblie.mvp.contact;

import com.hc.hmsmoblie.bean.json.ImageLogPanoramaListJson;
import com.yc.yclibrary.exception.ApiException;
import com.yc.yclibrary.mvp.IView;

import java.util.List;

/**
 *
 */

public class ImageLogPanoramaListC {
    public interface V extends IView {
        void onPanoramaListSuccess(ImageLogPanoramaListJson json);

        void onPanoramaListFail(ApiException apiException);
    }

    public interface P {
        void getPanoramaList(String camId, String starTime, String endTime, int pageIndex, int pageSize);
    }
}
