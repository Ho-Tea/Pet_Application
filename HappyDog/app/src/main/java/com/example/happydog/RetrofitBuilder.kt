package com.example.happydog

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitBuilder {
    var api : API
    init{
        val retrofit = Retrofit.Builder()
            .baseUrl("http://")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(API::class.java)
    }
}