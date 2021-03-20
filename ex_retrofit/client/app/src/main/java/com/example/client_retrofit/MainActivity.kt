package com.example.client_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:2021/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(retrofitService::class.java)

        val call = service.getDatas()

        call.enqueue(object : Callback<resultData> {
            override fun onResponse(call: Call<resultData>, response: Response<resultData>) {
                if (response.isSuccessful) {
                    // 통신 성공
                    val result = response.body()
                    println("onResponse : ${response.body()}")
                } else {
                    // 통신 실패 (응답 코드 3xx, 4xx 등)
                    println("fail")
                }
            }

            override fun onFailure(call: Call<resultData>, t: Throwable) {
                //통신 실패 (인터넷 끊김, 예외 발생 등)
                println("onFailure : ${t.message}")
            }
        })
    }
}