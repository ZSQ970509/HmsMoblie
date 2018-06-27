package com.hc.hmsmoblie.mvp.contact;

import com.hc.hmsmoblie.bean.json.LoginJson;
import com.hc.hmsmoblie.bean.json.MainJson;
import com.yc.yclibrary.mvp.IView;

import java.util.ArrayList;

/**
 *
 */

public class MainC {
    public interface V extends IView{
        void onGetModulesListSuccess(ArrayList<MainJson> mainJson);

        void onGetModulesListFail(String msg);
    }
    public interface P {
        void GetModulesList(String userAccount, String userID);
    }
}
