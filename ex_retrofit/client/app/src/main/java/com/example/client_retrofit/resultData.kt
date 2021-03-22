package com.example.client_retrofit

import com.google.gson.annotations.SerializedName

data class resultData (

    @SerializedName("id")
    private var id : Int,
    // @SerializedName으로 일치시켜 주지않을 경우엔 클래스 변수명이 일치해야함

    private var name : String,
    // @SerializedName()로 변수명을 입치시켜주면 클래스 변수명이 달라도 알아서 매핑시켜줌

    )