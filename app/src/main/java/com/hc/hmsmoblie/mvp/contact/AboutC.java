package com.hc.hmsmoblie.mvp.contact;

import com.hc.hmsmoblie.bean.json.LoginJson;
import com.hc.hmsmoblie.bean.json.UpdateVersionJson;
import com.yc.yclibrary.mvp.IView;

/**
 *
 */

public class AboutC {
    public interface V extends IView{

        void onUpdatedVersionSuccess(UpdateVersionJson httpResponse);

        void onUpdatedVersionFail(String msg);
    }
    public interface P {

        void updatedVersion();
    }
}
