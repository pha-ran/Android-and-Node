package com.example.client_retrofit

import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*

interface retrofitService {

    @GET("data")
    fun getDatas(): Call<ArrayList<resultData>>

    @FormUrlEncoded
    @POST("data")
    fun postDatas(@Field("title") title:String,
                  @Field("body") body:String
    ): Call<postData>

    @FormUrlEncoded
    @PUT("data/{title}")
    fun putData(@Path("title") title : String,
                @Field("body") body:String
    ) : Call<postData>

    @DELETE("data/{title}")
    fun deleteData(@Path("title") title : String) : Call<Void>
}
