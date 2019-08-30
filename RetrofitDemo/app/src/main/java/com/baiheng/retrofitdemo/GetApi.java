package com.baiheng.retrofitdemo;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetApi {
    /**
     * 获取用户信息
     * @return
     * @Query 注解
     */
    @GET("getUserInfo")
    Call<UserInfo> getUserInfo(@Query("id") String userId);
}
