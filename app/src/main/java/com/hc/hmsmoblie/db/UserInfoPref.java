package com.hc.hmsmoblie.db;


import io.reactivex.annotations.NonNull;

/**
 * 用户信息
 */

public class UserInfoPref {
    private static final String mUserName = "mUserName";//用户名
    private static final String mUId = "mUId";//用户名id
    private static final String mUserAccount = "mUserAccount";//用户账号
    private static final String mUserPassword = "mUserPassword";//用户密码
    private static final String mUserToken = "mUserToken";//用户登录后的Token标示
    private static final String mUserWeighingMachineFrist = "mUserWeighingMachineFrist";//用户是否第一次使用地磅系统

    private static final String mSavePassWord = "mSavePassWord";//是否记住密码 true  false
    private static final String mUserTypeId = "mUserTypeId";//用户类型id
    private static final String mUrl = "mUrl";//地址
    private static final String mPort = "mPort";//端口

    private static PrefHelper mPrefHelper = PrefHelper.Instance;

    public static int getUserTypeId() {
        return mPrefHelper.getPref(mUserTypeId, 0);
    }

    public static void setUserTypeId(@NonNull int userTypeId) {
        mPrefHelper.setPref(mUserTypeId, userTypeId);
    }

    public static String getUserToken() {
        return mPrefHelper.getPref(mUserToken, "");
    }

    public static void setUserToken(@NonNull String userToken) {
        mPrefHelper.setPref(mUserToken, userToken);
    }

    public static String getUrl() {
        return mPrefHelper.getPref(mUrl, "");
    }

    public static void setUrl(@NonNull String url) {
        mPrefHelper.setPref(mUrl, url);
    }

    public static String getPort() {
        return mPrefHelper.getPref(mPort, "");
    }

    public static void setPort(@NonNull String port) {
        mPrefHelper.setPref(mPort, port);
    }

    public static String getUserName() {
        return mPrefHelper.getPref(mUserName, "");
    }

    public static void setUserName(@NonNull String userName) {
        mPrefHelper.setPref(mUserName, userName);
    }


    public static String getUserId() {
        return mPrefHelper.getPref(mUId, mUId);
    }

    public static void setUserId(@NonNull String userId) {
        mPrefHelper.setPref(mUId, userId);
    }


    public static String getUserAccount() {
        return mPrefHelper.getPref(mUserAccount, mUserAccount);
    }

    public static void setUserAccount(@NonNull String userAccount) {
        mPrefHelper.setPref(mUserAccount, userAccount);
    }


    public static String getUserPassword() {
        return mPrefHelper.getPref(mUserPassword, mUserPassword);
    }

    public static void setUserPassword(@NonNull String userPassword) {
        mPrefHelper.setPref(mUserPassword, userPassword);
    }


    public static boolean getSavePassWord() {
        return mPrefHelper.getPref(mSavePassWord, false);
    }

    public static void setSavePassWord(@NonNull boolean loginState) {
        mPrefHelper.setPref(mSavePassWord, loginState);
    }

    public static boolean getmUserWeighingMachineFrist() {
        return mPrefHelper.getPref(mUserWeighingMachineFrist, true);
    }

    public static void setmUserWeighingMachineFrist(@NonNull boolean userToken) {
        mPrefHelper.setPref(mUserWeighingMachineFrist, userToken);
    }
    /**
     * 保存用户登录信息
     */
    public static void saveLoginInfo(String userAccount, String userName, String userPassword, String userId) {
        setUserAccount(userAccount);
        setUserName(userName);
        setUserPassword(userPassword);
        setUserId(userId);
    }

    /**
     * 清除用户登录信息
     */
    public static void clearloginInfo() {
        setUserAccount("");
        setUserName("");
        setUserPassword("");
        setUserId("");
    }
}
