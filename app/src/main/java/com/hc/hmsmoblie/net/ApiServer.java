package com.hc.hmsmoblie.net;

import com.hc.hmsmoblie.bean.domain.ProjectVideoBean;
import com.hc.hmsmoblie.bean.json.LoginJs;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 *  实际接口
 */

public interface ApiServer {
    @GET(UrlHelper.BASE_API + "hmsLogin")
    Observable<HttpResponse<LoginJs>> login(@Query("userName") String userName, @Query("userPassWord") String passWord);
    @POST(UrlHelper.BASE_API + "getCameraList")
    Observable<HttpResponse<ProjectVideoBean>> getCameraList(@Field("keyword") String keyword, @Field("pageindex") String pageindex
            ,@Field("pagesize") String pagesize, @Field("sysId") String sysId, @Field("userid") String userid);
}
