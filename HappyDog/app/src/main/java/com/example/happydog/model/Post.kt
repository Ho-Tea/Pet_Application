package com.example.happydog.model

import com.google.gson.annotations.SerializedName

class Post {
    @SerializedName("contents")
    lateinit var contents: List<UserDto>
    class UserDto(
        @SerializedName("uid")
        var uid : String,
        @SerializedName("username")
        var username : String,
        @SerializedName("email")
        var email : String)
}