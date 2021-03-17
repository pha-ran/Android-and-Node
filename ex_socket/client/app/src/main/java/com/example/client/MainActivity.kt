package com.example.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import org.json.JSONObject
import java.net.URISyntaxException

class MainActivity : AppCompatActivity() {

    lateinit var mSocket : Socket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 소켓 연결
        try {
            mSocket = IO.socket("http://10.0.2.2:2021")
            mSocket.connect()
        } catch (e: URISyntaxException) {
            println("URISyntaxException ${e.reason}")
        }

        // 클라이언트에서 이벤트 처리
        mSocket.on(Socket.EVENT_CONNECT, onConnect)
        mSocket.on("message", onMessage)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {

            // 서버의 이벤트를 호출
            var json : JSONObject = JSONObject()
            json.put("name", "seed") //json 오브젝트 전달, mongoDB에 데이터 생성
            mSocket.emit("message", json)
        }

        val button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener {
            // 서버와 연결 끊기
            mSocket.disconnect()
        }
    }

    // EVENT_CONNECT의 리스너 설정
    val onConnect: Emitter.Listener = Emitter.Listener {
        println("Connect")
    }

    val onMessage: Emitter.Listener = Emitter.Listener {
        val obj = it[0].toString()
        println(obj)
    }
}