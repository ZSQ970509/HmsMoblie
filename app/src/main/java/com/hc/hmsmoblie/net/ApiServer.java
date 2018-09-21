package com.hc.hmsmoblie.net;

        import com.hc.hmsmoblie.bean.json.EnvironmentDetailsJson;
        import com.hc.hmsmoblie.bean.json.EnvironmentDeviceListJson;
        import com.hc.hmsmoblie.bean.json.LadderControlDetailsErrorJson;
        import com.hc.hmsmoblie.bean.json.LadderControlDetailsOperationJson;
        import com.hc.hmsmoblie.bean.json.LadderControlDeviceListJson;
        import com.hc.hmsmoblie.bean.json.MainJson;
        import com.hc.hmsmoblie.bean.json.OnlineTimeJson;
        import com.hc.hmsmoblie.bean.json.ProjectDetailsJson;
        import com.hc.hmsmoblie.bean.json.ProjectJson;
        import com.hc.hmsmoblie.bean.json.LoginJson;
        import com.hc.hmsmoblie.bean.json.TiltSensorChartJson;
        import com.hc.hmsmoblie.bean.json.UpdateVersionJson;
        import com.hc.hmsmoblie.bean.json.VideoDriverJson;
import com.hc.hmsmoblie.bean.json.EnvironmentDetailsJson;
import com.hc.hmsmoblie.bean.json.EnvironmentDeviceListJson;
import com.hc.hmsmoblie.bean.json.ImageLogNodeJson;
import com.hc.hmsmoblie.bean.json.ImageLogPanoramaListJson;
import com.hc.hmsmoblie.bean.json.ImageLogWideAngleJson;
import com.hc.hmsmoblie.bean.json.LadderControlDetailsErrorJson;
import com.hc.hmsmoblie.bean.json.LadderControlDetailsOperationJson;
import com.hc.hmsmoblie.bean.json.LadderControlDeviceListJson;
import com.hc.hmsmoblie.bean.json.OnlineTimeJson;
import com.hc.hmsmoblie.bean.json.ProjectDetailsJson;
import com.hc.hmsmoblie.bean.json.ProjectJson;
import com.hc.hmsmoblie.bean.json.LoginJson;
import com.hc.hmsmoblie.bean.json.VideoDriverJson;
        import com.yc.yclibrary.YcInit;

        import java.lang.reflect.Array;
        import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
        import retrofit2.http.Headers;
        import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 实际接口
 */

public interface ApiServer {
    @FormUrlEncoded
    @POST(UrlHelper.BASE_API + "hmsLogin")
    Observable<HttpResponse<LoginJson>> login(@Field("userName") String userName, @Field("userPassWord") String passWord, @Field("typeid") String userType);

    @Headers(YcInit.OTHER_BASE_URL+":"+UrlHelper.BASE_URL_UPDATE)
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
    @Headers(YcInit.OTHER_BASE_URL+":"+"http://api.jsqqy.com/")
    @POST(UrlHelper.API_TILT_SENSOR + "GetNewTiltSensorLog")
    Observable<HttpResponse<List<TiltSensorChartJson>>> getTiltSensorChart(@Field("camID") String camID, @Field("paraID") String paraID, @Field("timeType") String timeType, @Field("selDate") String selDate);

}
