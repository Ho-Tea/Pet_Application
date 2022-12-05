package com.example.happydog

import retrofit2.Call
import com.example.happydog.model.User
import retrofit2.http.Body
import retrofit2.http.POST


public interface API {
    @POST("search")
    fun postLocationResponse(@Body user: User) : Call<String>

//    @GET("search")
//    fun getLocationResponse() : Call<String>
//
//    @POST("register")
//    fun getUserResponse(@Body userLocation: User) : Call<String>
}