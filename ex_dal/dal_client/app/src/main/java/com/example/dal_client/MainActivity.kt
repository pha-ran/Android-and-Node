package com.example.dal_client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    lateinit var boardFragment : BoardFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        boardFragment = BoardFragment()
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, boardFragment).commit()
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }
}