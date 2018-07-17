package com.hc.hmsmoblie.mvp.contact;

import com.hc.hmsmoblie.bean.json.LoginJson;
import com.hc.hmsmoblie.bean.json.UpdateVersionJson;
import com.yc.yclibrary.mvp.IView;

/**
 *
 */

public class LoginC {
    public interface V extends IView {
        void onLoginSuccess(LoginJson loginJs);

        void onLoginFail(String msg);

        void onUpdatedVersionSuccess(UpdateVersionJson httpResponse);

        void onUpdatedVersionFail(String msg);
    }

    public interface P {
        void login(String userName, String userPassword, String userType);

        void updatedVersion();
    }
}
