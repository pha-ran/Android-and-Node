package com.example.client_retrofit

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    var datalist = ArrayList<resultData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val text = findViewById<TextView>(R.id.text)
        val recyclerview = findViewById<RecyclerView>(R.id.rec)
        val adapter = adapter(datalist, this)
        val layoutmanager = LinearLayoutManager(this)
        recyclerview.layoutManager = layoutmanager
        recyclerview.adapter = adapter

        val retrofit = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:2021/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(retrofitService::class.java)

        val img = findViewById<ImageView>(R.id.imageView2)
        Glide.with(this).load("http://10.0.2.2:2021/images/seed1.jpg").into(img)

        val call = service.getDatas()

        val button4 = findViewById<Button>(R.id.button4)

        button4.setOnClickListener {
            call.clone().enqueue(object : Callback<ArrayList<resultData>> {
                override fun onResponse(call: Call<ArrayList<resultData>>, response: Response<ArrayList<resultData>>) {
                    if (response.isSuccessful) {
                        // ?????? ??????
                        text.text = "onResponse : ${response.body()}"
                        if (response.body() != null) {
                            //datalist = response.body()!!
                            //println(datalist[0].gettitle())
                            for (i in 0 until response.body()!!.size) {
                                datalist.add(resultData(response.body()!![i].getid(), response.body()!![i].gettitle()))
                            }

                            adapter.notifyDataSetChanged()
                        }
                    } else {
                        // ?????? ?????? (?????? ?????? 3xx, 4xx ???)
                        text.text = "fail"
                    }
                }

                override fun onFailure(call: Call<ArrayList<resultData>>, t: Throwable) {
                    //?????? ?????? (????????? ??????, ?????? ?????? ???)
                    text.text = "onFailure : ${t.message}"
                }
            })
        }

        val edittext = findViewById<EditText>(R.id.editTextTextPersonName)
        val edittext2 = findViewById<EditText>(R.id.editTextTextPersonName2)
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {

            service.postDatas(edittext.text.toString(), edittext2.text.toString()).enqueue(object : Callback<postData> {
                override fun onResponse(call: Call<postData>, response: Response<postData>) {
                    if (response.isSuccessful) {
                        // ?????? ??????
                        text.text = "onResponse : ${response.body()}"
                        adapter.notifyDataSetChanged()
                    } else {
                        // ?????? ?????? (?????? ?????? 3xx, 4xx ???)
                        text.text = "fail"
                    }
                }

                override fun onFailure(call: Call<postData>, t: Throwable) {
                    //?????? ?????? (????????? ??????, ?????? ?????? ???)
                    text.text = "onFailure : ${t.message}"
                }
            })
        }

        val button2 = findViewById<Button>(R.id.button2)

        button2.setOnClickListener {
            service.deleteData(edittext.text.toString()).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    adapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    text.text = "onFailure : ${t.message}"
                }

            })
        }

        val button3 = findViewById<Button>(R.id.button3)

        button3.setOnClickListener {
            service.putData(edittext.text.toString(), edittext2.text.toString()).enqueue(object : Callback<postData> {
                override fun onResponse(call: Call<postData>, response: Response<postData>) {
                    if (response.isSuccessful) {
                        text.text = "onResponse : ${response.body()}"
                        adapter.notifyDataSetChanged()
                    } else {
                        text.text = "fail"
                    }
                }

                override fun onFailure(call: Call<postData>, t: Throwable) {
                    text.text = "onFailure : ${t.message}"
                }
            })
        }
    }
}