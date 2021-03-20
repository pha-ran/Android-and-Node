package com.example.client_retrofit

import retrofit2.Call
import retrofit2.http.GET

interface retrofitService {

    @GET("data")
    fun getDatas(): Call<resultData>
}