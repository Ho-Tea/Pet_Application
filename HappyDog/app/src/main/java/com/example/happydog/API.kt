package com.example.happydog

import android.util.Size
import com.example.happydog.model.Post
import retrofit2.Call
import com.example.happydog.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


public interface API {
    @POST("/auth/register")
    fun postLocationResponse(@Body user: User) : Call<String>

    @GET("/search")
    fun getLocationResponse(@Query("uid") uid : String, @Query("page") page : Int, @Query("size") size: Int) : Call<Post>

}