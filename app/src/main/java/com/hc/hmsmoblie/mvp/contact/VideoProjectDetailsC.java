package com.hc.hmsmoblie.mvp.contact;

import com.hc.hmsmoblie.bean.json.ProjectDetailsJson;
import com.hc.hmsmoblie.bean.json.ProjectJson;
import com.yc.yclibrary.mvp.IView;

import java.util.ArrayList;

/**
 *
 */

public class VideoProjectDetailsC {
    public interface V extends IView{
        void onGetCameradetailsSuccess(ArrayList<ProjectDetailsJson> dataBean);

        void onGetCameradetailsFail(String msg);
    }
    public interface P {
        void getCameradetails(String projId, int systemid);
    }
}
