package com.hc.hmsmoblie.net;

import com.hc.hmsmoblie.bean.json.DevicePtzJson;
import com.hc.hmsmoblie.bean.json.EnvironmentDetailsJson;
import com.hc.hmsmoblie.bean.json.EnvironmentDeviceListJson;
import com.hc.hmsmoblie.bean.json.GetDevUrlJson;
import com.hc.hmsmoblie.bean.json.LadderControlDetailsErrorJson;
import com.hc.hmsmoblie.bean.json.LadderControlDetailsOperationJson;
import com.hc.hmsmoblie.bean.json.LadderControlDeviceListJson;
import com.hc.hmsmoblie.bean.json.MainJson;
import com.hc.hmsmoblie.bean.json.OnlineTimeJson;
import com.hc.hmsmoblie.bean.json.ProjectDetailsJson;
import com.hc.hmsmoblie.bean.json.ProjectJson;
import com.hc.hmsmoblie.bean.json.LoginJson;
import com.hc.hmsmoblie.bean.json.SensorLogJson;
import com.hc.hmsmoblie.bean.json.TiltSensorAllJson;
import com.hc.hmsmoblie.bean.json.TiltSensorJson;
import com.hc.hmsmoblie.bean.json.TiltSensorSettingJson;
import com.hc.hmsmoblie.bean.json.TiltSensorStateJson;
import com.hc.hmsmoblie.bean.json.TiltSensorChartJsonNew;
import com.hc.hmsmoblie.bean.json.TiltSensorParaJson;
import com.hc.hmsmoblie.bean.json.UpdateVersionJson;
import com.hc.hmsmoblie.bean.json.VideoDriverJson;
import com.hc.hmsmoblie.bean.json.ImageLogNodeJson;
import com.hc.hmsmoblie.bean.json.ImageLogPanoramaListJson;
import com.hc.hmsmoblie.bean.json.ImageLogWideAngleJson;
import com.hc.hmsmoblie.bean.json.WeighingMachineJson;
import com.hc.hmsmoblie.bean.json.WeighingMachineMsg;
import com.hc.hmsmoblie.widget.TitleSenorSettingDialog;
import com.yc.yclibrary.YcInit;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * 实际接口
 */

public interface ApiServer {
    @FormUrlEncoded
    @POST(UrlHelper.BASE_API + "hmsLogin")
    Observable<HttpResponse<LoginJson>> login(@Field("userName") String userName, @Field("userPassWord") String passWord, @Field("typeid") String userType);

    @Headers(YcInit.OTHER_BASE_URL + ":" + UrlHelper.BASE_URL_UPDATE)
    @GET(UrlHelper.API_UPDATE + "update")
    Observable<ArrayList<UpdateVersionJson>> updatedVersion(@Query("type") String packageName);

    @FormUrlEncoded
    @POST(UrlHelper.BASE_API + "GetModulesList")
    Observable<HttpResponse<ArrayList<MainJson>>> GetModulesList(@Field("userAccount") String userAccount, @Field("userID") String userID);

    @FormUrlEncoded
    @POST(UrlHelper.BASE_API + "changeHmsPassWord")
    Observable<HttpResponse> changeHmsPassWord(@Field("userAccount") String userAccount, @Field("UserPwd") String UserPwd, @Field("newPwd") String newPwd);

    @FormUrlEncoded
    @POST(UrlHelper.BASE_API + "GetAppMonitorList")
    Observable<HttpResponse<ProjectJson>> getCameraList(@Field("keyword") String keyword, @Field("pageindex") int pageindex
            , @Field("pagesize") int pagesize, @Field("sysId") String sysId, @Field("userid") String userid, @Field("account") String account, @Field("TokenId") String tokenId);

    @FormUrlEncoded
    @POST(UrlHelper.BASE_API + "getCameradetails")
    Observable<HttpResponse<ArrayList<ProjectDetailsJson>>> getCameradetails(@Field("projId") String projId, @Field("systemid") int systemid);

    @FormUrlEncoded
    @POST(UrlHelper.BASE_API + "GetOnlineRate")
    Observable<HttpResponse<ArrayList<OnlineTimeJson>>> GetOnlineRate(@Field("projId") String projId, @Field("startDate") String startDate, @Field("endDate") String endDate);

    @FormUrlEncoded
    @POST(UrlHelper.BASE_API + "getCameraListdetails")
    Observable<HttpResponse<ArrayList<VideoDriverJson>>> getCameraListdetails(@Field("projId") String projId, @Field("systemid") String systemid);

    @FormUrlEncoded
    @POST(UrlHelper.BASE_API + "GetTowerCraneDevList")
    Observable<HttpResponse<LadderControlDeviceListJson>> getTowerCraneDevList(@Field("keyword") String keyword, @Field("pageindex") int pageindex
            , @Field("pagesize") int pagesize, @Field("projId") String proId, @Field("camId") String camId);

    @FormUrlEncoded
    @POST(UrlHelper.BASE_API + "GetErrlog")
    Observable<HttpResponse<LadderControlDetailsErrorJson>> GetErrlog(@Field("projId") String projId, @Field("keyword") String keyword, @Field("pageindex") int pageindex
            , @Field("pagesize") int pagesize);

    @FormUrlEncoded
    @POST(UrlHelper.BASE_API + "GetTowerCraneListByPaging")
    Observable<HttpResponse<LadderControlDetailsOperationJson>> GetTowerCraneListByPaging(@Field("devId") String projId, @Field("keyword") String keyword, @Field("pageindex") int pageindex
            , @Field("pagesize") int pagesize);

    @FormUrlEncoded
    @POST(UrlHelper.BASE_API + "GetPara")
    Observable<HttpResponse<EnvironmentDetailsJson>> getPara(@Field("CamId") String camId, @Field("seqId") String seqId, @Field("date") String date
            , @Field("projId") String projId, @Field("para") String para, @Field("timeType") String timeType);

    @FormUrlEncoded
    @POST(UrlHelper.BASE_API + "getEnvironmentalListdetails")
    Observable<HttpResponse<List<EnvironmentDeviceListJson>>> getEnvironmentDeviceList(@Field("projId") String projId, @Field("systemid") String systemid);

    @FormUrlEncoded
    @POST(UrlHelper.BASE_API + "GetPanoramaImg")
    Observable<HttpResponse<ImageLogPanoramaListJson>> getPanoramaList(@Field("camId") String camId, @Field("startime") String starTime, @Field("endTime") String endTime, @Field("pageindex") int pageIndex, @Field("pagesize") int pageSize);

    @FormUrlEncoded
    @POST(UrlHelper.BASE_API + "GetRangeNodeImgNew")
    Observable<HttpResponse<ImageLogWideAngleJson>> getWideAngle(@Field("puzzleId") String panoramaId, @Field("ImageTimes") String imageTimes, @Field("pointx") String pointX, @Field("pointy") String pointY);

    @FormUrlEncoded
    @POST(UrlHelper.BASE_API + "GetRangeNodeImgDetail")
    Observable<HttpResponse<ImageLogNodeJson>> getNode(@Field("CamSn") String camSn, @Field("puzzleId") String panoramaId, @Field("ImageTimes") String imageTimes, @Field("pointx") String pointX, @Field("pointy") String pointY, @Field("aha") String aha, @Field("ava") String ava);

    @FormUrlEncoded
    @POST(UrlHelper.API_TILT_SENSOR + "GetNewTiltSensorLog")
    Observable<HttpResponse<List<TiltSensorChartJsonNew>>> getTiltSensorChart(@Field("camID") String camID, @Field("paraID") String paraID, @Field("timeType") String timeType, @Field("selDate") String selDate, @Field("type") int type);

    @FormUrlEncoded
    @POST(UrlHelper.API_TILT_SENSOR + "GetTiltSensorLog")
    Observable<HttpResponse<SensorLogJson>> getTiltSensorLog(@Field("CamID") String cmID, @Field("ParaID") String paraID, @Field("pageindex") int pageindex
            , @Field("pagesize") int pagesize, @Field("startTime") String startTime, @Field("endTime") String endTime);

    @FormUrlEncoded
    @POST(UrlHelper.API_TILT_SENSOR + "GetGetTiltSensorPara")
    Observable<HttpResponse<TiltSensorParaJson>> getGetTiltSensorPara(@Field("camId") String cmID);

    @FormUrlEncoded
    @POST(UrlHelper.API_TILT_SENSOR + "SetAllMessage")
    Observable<HttpResponse<String>> setAllMessage(@Field("paraID") String paraID, @Field("Seq") String seq, @Field("Type") String type);

    @FormUrlEncoded
    @Headers(YcInit.OTHER_BASE_URL + ":" + UrlHelper.BASE_TITLE_SENSOR)
    @POST(UrlHelper.API_TITLE_SENSOR + "getIotDeviceInfo?")
    Observable<TiltSensorStateJson> getTitleSensorState(@Field("deviceId") String deviceId);

    @FormUrlEncoded
    @Headers(YcInit.OTHER_BASE_URL + ":" + UrlHelper.BASE_TITLE_SENSOR)
    @POST(UrlHelper.API_TITLE_SENSOR + "setIotDeviceInfo?")
    Observable<TiltSensorSettingJson> setIotDeviceInfo(@Field("deviceId") String deviceId, @Field("serviceId") String serviceId, @Field("method") String method
            , @Field("jsonCommand") String jsonCommand, @Field("expireTime") int expireTime);

    @FormUrlEncoded
    @POST(UrlHelper.API_TILT_SENSOR_2 + "getTitAll")
    Observable<HttpResponse<TiltSensorAllJson>> getTitAll(@Field("ParaID") String paraID);

    @FormUrlEncoded
    @POST(UrlHelper.API_TILT_WEIGHING_MACH + "GetWeighbridgeList")
    Observable<HttpResponse<WeighingMachineJson>> getWeighbridgeList(@Field("projId") String projId
            , @Field("pageindex") int pageindex, @Field("pagesize") int pagesize, @Field("openingTimeBegin") String openingTimeBegin
            , @Field("openingTimeEnd") String openingTimeEnd);

    @FormUrlEncoded
    @POST(UrlHelper.API_TILT_WEIGHING_MACH + "GetWeighbridge")
    Observable<HttpResponse<WeighingMachineMsg>> getWeighbridge(@Field("recordId") String recordId);

//    @Headers(YcInit.OTHER_BASE_URL + ":" + "http://10.1.3.68:42173/")
    @GET("openInterface/SystemDependentService.ashx?action=GetDevUrl")
    Observable<HttpResponse<GetDevUrlJson>> getDevUrl(@Query("ip") String ip, @Query("port") String port, @Query("account") String account,
                                        @Query("password") String password, @Query("deviceNum") String deviceNum, @Query("method") String method);
//    @Multipart
//    @Headers(YcInit.OTHER_BASE_URL+":"+"http://10.1.3.86:803/")
//    @POST(UrlHelper.API_HUIYAN)
//    Observable<GetDevUrlJson> getDevUrl(@PartMap Map<String, RequestBody> requestBodyMap);

//    @Headers(YcInit.OTHER_BASE_URL + ":" + "http://10.1.3.68:42173/")
    @GET("openInterface/SystemDependentService.ashx?action=DevicePtz")
    Observable<DevicePtzJson> devicePtzs(@Query("ip") String ip, @Query("port") String port, @Query("account") String account,
                                         @Query("password") String password, @Query("deviceNum") String deviceNum, @Query("method") String method,
                                         @Query("speed") String speed, @Query("ptz") String ptz, @Query("isSpecial") String isSpecial);


}
