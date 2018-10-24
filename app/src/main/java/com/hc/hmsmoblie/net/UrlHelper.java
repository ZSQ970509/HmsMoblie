package com.hc.hmsmoblie.net;

/**
 *  接口地址和api
 */

public class UrlHelper {
    //地址
//    public static String BASE_URL = "http://10.1.3.68:42173/";//朱强电脑
//    public static String BASE_URL = "http://api.jsqqy.com/";//外网
    public static String BASE_URL = "http://192.168.1.85:26969/";//测试地址
//    public static String BASE_URL = "http://10.1.3.86:8023/";//小温

    public final static String BASE_URL_UPDATE = "http://api.jsqqy.com/";//
    //Api
    public static final String BASE_API = "ApiCamera.ashx?action=";
    public static final String API_UPDATE = "api.ashx?action=";//版本更新的api

    public static final String API_TILT_SENSOR = "OpenInterface/TiltSensorHandler.ashx?action=";//倾角的api
}
