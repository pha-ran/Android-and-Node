package com.example.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.client.Socket.EVENT_CONNECT_ERROR
import java.net.URISyntaxException

class MainActivity : AppCompatActivity() {

    lateinit var mSocket : Socket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Thread{
            try {
                mSocket = IO.socket("http://10.0.2.2:2021")
                mSocket.connect()

                mSocket.on(io.socket.client.Socket.EVENT_CONNECT) {
                    // 소켓 서버 연결이 성공할 경우 호출
                    println("Socket Connect")
                }
                mSocket.on(io.socket.client.Socket.EVENT_DISCONNECT) { args ->
                    // 소켓 서버 연결이 끊어질 경우 호출
                    println("Socket Disconnet")
                }
                mSocket.on(EVENT_CONNECT_ERROR) { args ->
                    // 소켓 서버 연결 시 오류가 발생할 경우 호출
                    println("Socket Connect Error")
                }
            } catch (e: URISyntaxException) {
                println("URISyntaxException ${e.reason}")
            }
        }
    }
}