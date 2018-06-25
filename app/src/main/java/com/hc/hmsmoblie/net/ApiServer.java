package com.hc.hmsmoblie.net;

        import com.hc.hmsmoblie.bean.json.EnvironmentDetailsJson;
        import com.hc.hmsmoblie.bean.json.EnvironmentDeviceListJson;
        import com.hc.hmsmoblie.bean.json.LadderControlDetailsErrorJson;
        import com.hc.hmsmoblie.bean.json.LadderControlDetailsOperationJson;
        import com.hc.hmsmoblie.bean.json.LadderControlDeviceListJson;
        import com.hc.hmsmoblie.bean.json.OnlineTimeJson;
        import com.hc.hmsmoblie.bean.json.ProjectDetailsJson;
        import com.hc.hmsmoblie.bean.json.ProjectJson;
        import com.hc.hmsmoblie.bean.json.LoginJson;
        import com.hc.hmsmoblie.bean.json.VideoDriverJson;

        import java.util.ArrayList;
        import java.util.List;

        import io.reactivex.Observable;
        import retrofit2.http.Field;
        import retrofit2.http.FormUrlEncoded;
        import retrofit2.http.GET;
        import retrofit2.http.POST;
        import retrofit2.http.Query;

/**
 * 实际接口
 */

public interface ApiServer {
    @GET(UrlHelper.BASE_API + "hmsLogin")
    Observable<HttpResponse<LoginJson>> login(@Query("userName") String userName, @Query("userPassWord") String passWord);

    @FormUrlEncoded
    @POST(UrlHelper.BASE_API + "getCameraList")
    Observable<HttpResponse<ProjectJson>> getCameraList(@Field("keyword") String keyword, @Field("pageindex") int pageindex
            , @Field("pagesize") int pagesize, @Field("sysId") String sysId, @Field("userid") String userid);

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
            , @Field("pagesize") int pagesize, @Field("projId") String proId);

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
}
