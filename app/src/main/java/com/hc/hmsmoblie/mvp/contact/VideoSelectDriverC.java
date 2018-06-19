package com.hc.hmsmoblie.mvp.contact;

import com.hc.hmsmoblie.bean.json.ProjectJson;
import com.hc.hmsmoblie.bean.json.VideoDriverJson;
import com.yc.yclibrary.mvp.IView;

import java.util.ArrayList;

/**
 *
 */

public class VideoSelectDriverC {
    public interface V extends IView{
        void onGetCameraListdetailsSuccess(ArrayList<VideoDriverJson> dataBean);

        void onGetCameraListdetailsFail(String msg);
    }
    public interface P {
        void getCameraListdetails(String projId, String systemid);
    }
}
