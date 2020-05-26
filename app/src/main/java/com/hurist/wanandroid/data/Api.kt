package com.hurist.wanandroid.data

import com.hurist.wanandroid.data.responseData.ArticleList
import com.hurist.wanandroid.data.responseData.UserInfo
import retrofit2.http.*

interface Api {

    @GET("article/list/{page}/json")
    suspend fun getIndex(@Path("page") page: Int): Response<ArticleList>

    @FormUrlEncoded
    @POST("user/logi")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("test") test: String?
    ): Response<UserInfo>

    @FormUrlEncoded
    @POST("user-info/login")
    suspend fun bitBaseLogin(
        @Field("account") account: String,
        @Field("loginPwd") loginPwd: String,
        @Field("areaNo") password: String? = null
    )

}