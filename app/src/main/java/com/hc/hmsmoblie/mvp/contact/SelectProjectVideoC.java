package com.hc.hmsmoblie.mvp.contact;

import com.hc.hmsmoblie.bean.domain.ProjectVideoBean;
import com.hc.hmsmoblie.bean.json.LoginJs;
import com.yc.yclibrary.mvp.IView;

/**
 *
 */

public class SelectProjectVideoC {
    public interface V extends IView{
        void onGetVideoProjectSuccess(ProjectVideoBean projectVideoBean);

        void onGetVideoProjectFail(String msg);
    }
    public interface P {
        void getVideoProject(String keyword, String pageindex, String pagesize, String sysId, String userid);
    }
}
