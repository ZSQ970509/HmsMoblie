package com.hc.hmsmoblie.mvp.contact;

import com.hc.hmsmoblie.bean.json.ProjectJson;
import com.yc.yclibrary.mvp.IView;

/**
 *
 */

public class VideoSelectProjectC {
    public interface V extends IView{
        void onGetVideoProjectSuccess(ProjectJson dataBean);

        void onGetVideoProjectFail(String msg);
    }
    public interface P {
        void getVideoProject(String keyword, int pageindex, int pagesize, String sysId, String userid);
    }
}
