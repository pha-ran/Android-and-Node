package com.example.client_retrofit

import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface retrofitService {

    @GET("data")
    fun getDatas(): Call<ArrayList<resultData>>

    @FormUrlEncoded
    @POST("data")
    fun postDatas(@Field("title") title:String,
                  @Field("body") body:String
    ): Call<postData>
}