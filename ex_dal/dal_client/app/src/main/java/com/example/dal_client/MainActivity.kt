package com.example.dal_client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Dal_client)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}