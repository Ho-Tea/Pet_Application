package com.example.happydog.model

import com.google.gson.annotations.SerializedName

class User {
    @SerializedName("email")
    var email: String? = null
        get() { return field}
        set(value) { field = value}
    @SerializedName("uid")
    var uid : String? = null
        get() { return field}
        set(value) { field = value}
    @SerializedName("username")
    var username : String? = null

    @SerializedName("coordinate")
    lateinit var coordinate: CoordinateDto

    class CoordinateDto(
    @SerializedName("latitude")
    var latitude : String? = null,
//        get() { return field}
//        set(value) { field = value}
    @SerializedName("longtitude")
    var longtitude : String? = null
//        get() { return field}
//        set(value) { field = value}
    )

}