package com.example.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
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

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {

            // 서버의 이벤트를 호출
            val data = "Hello World"
            mSocket.emit("message", data)
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
}