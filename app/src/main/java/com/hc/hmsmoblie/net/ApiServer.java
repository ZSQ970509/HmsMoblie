package com.hc.hmsmoblie.net;

import com.hc.hmsmoblie.bean.json.LadderControlDeviceListJson;
import com.hc.hmsmoblie.bean.json.ProjectDetailsJson;
import com.hc.hmsmoblie.bean.json.ProjectJson;
import com.hc.hmsmoblie.bean.json.LoginJson;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 *  实际接口
 */

public interface ApiServer {
    @GET(UrlHelper.BASE_API + "hmsLogin")
    Observable<HttpResponse<LoginJson>> login(@Query("userName") String userName, @Query("userPassWord") String passWord);
    @FormUrlEncoded
    @POST(UrlHelper.BASE_API + "getCameraList")
    Observable<HttpResponse<ProjectJson>> getCameraList(@Field ("keyword") String keyword, @Field("pageindex") int pageindex
            , @Field("pagesize") int pagesize, @Field("sysId") String sysId, @Field("userid") String userid);
    @FormUrlEncoded
    @POST(UrlHelper.BASE_API + "getCameradetails")
    Observable<HttpResponse<ArrayList<ProjectDetailsJson>>> getCameradetails(@Field ("projId") String projId, @Field("systemid") int systemid);

    @FormUrlEncoded
    @POST(UrlHelper.BASE_API + "GetTowerCraneDevList")
    Observable<HttpResponse<LadderControlDeviceListJson>> getTowerCraneDevList(@Field ("keyword") String keyword, @Field("pageindex") int pageindex
            , @Field("pagesize") int pagesize, @Field("projId") String proId);
}
