package com.example.client_retrofit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface retrofitService {

    @GET("data")
    fun getDatas(): Call<resultData>

    @POST("data")
    fun postDatas(@Body param : HashMap<String, String>): Call<postData>
                    //값이 여러개일 경우
}