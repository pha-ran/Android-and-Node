package com.example.client_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val text = findViewById<TextView>(R.id.text)

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
                    text.text = "onResponse : ${response.body()}"
                } else {
                    // 통신 실패 (응답 코드 3xx, 4xx 등)
                    text.text = "fail"
                }
            }

            override fun onFailure(call: Call<resultData>, t: Throwable) {
                //통신 실패 (인터넷 끊김, 예외 발생 등)
                text.text = "onFailure : ${t.message}"
            }
        })

        val edittext = findViewById<EditText>(R.id.editTextTextPersonName)
        val edittext2 = findViewById<EditText>(R.id.editTextTextPersonName2)
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {

            service.postDatas(edittext.text.toString(), edittext2.text.toString()).enqueue(object : Callback<postData> {
                override fun onResponse(call: Call<postData>, response: Response<postData>) {
                    if (response.isSuccessful) {
                        // 통신 성공
                        text.text = "onResponse : ${response.body()}"
                    } else {
                        // 통신 실패 (응답 코드 3xx, 4xx 등)
                        text.text = "fail"
                    }
                }

                override fun onFailure(call: Call<postData>, t: Throwable) {
                    //통신 실패 (인터넷 끊김, 예외 발생 등)
                    text.text = "onFailure : ${t.message}"
                }
            })


        }
    }
}