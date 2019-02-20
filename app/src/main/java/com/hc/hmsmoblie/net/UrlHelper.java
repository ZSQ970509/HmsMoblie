package com.hc.hmsmoblie.net;

/**
 * 接口地址和api
 */

public class UrlHelper {
    //地址
//    public static String BASE_URL = "http://10.1.3.68:42173/";//朱强电脑
    //public static String BASE_URL = "http://api.jsqqy.com/";//外网

//        public static String BASE_URL = "http://192.168.1.85:26969/";//内网测试地址，正式数据
    public final static String BASE_URL_TEST = "http://10.1.3.86:8023/";//小温
    public static String BASE_URL = "http://120.35.11.49:26969/";//外网测试地址，数据正式
    public final static String BASE_URL_UPDATE = "http://api.jsqqy.com/";//

    public final static String BASE_TITLE_SENSOR = "http://27.155.102.250:8869/";
    public final static String API_TITLE_SENSOR = "HMS/";

    //Api
    public static final String BASE_API = "ApiCamera.ashx?action=";
    public static final String API_UPDATE = "api.ashx?action=";//版本更新的api
    public static final String API_TILT_WEIGHING_MACH = "OpenInterface/WeighbridgeService.ashx?action=";//地磅
    public static final String API_TILT_SENSOR = "OpenInterface/TiltSensorHandler.ashx?action=";//倾角的api
    public static final String API_TILT_SENSOR_2 = "OpenInterface/SystemDependentService.ashx?action=";//朱强倾角的api
}
