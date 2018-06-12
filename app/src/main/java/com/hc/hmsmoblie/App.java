package com.hc.hmsmoblie;

import android.app.Application;

import com.hc.hmsmoblie.net.UrlHelper;
import com.yc.yclibrary.EasyCode;

/**
 *
 */

public class App extends Application {
    private static App mApp;

    public static App getInstance() {
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        EasyCode.init(this, UrlHelper.BASE_URL);
        mApp = this;
    }
}
