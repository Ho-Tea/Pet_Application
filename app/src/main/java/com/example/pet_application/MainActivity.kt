package com.example.pet_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //navController = nav_host_fragment.findNavController()
        var navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController


    }
}

//
//
//interface LoginService {
//    @FormUrlEncoded
//    @POST("/app_login/")    //서버측의 호출 메서드
//    fun requestLogin(
//        @Field("userid") userid:String,
//        @Field("userpw") userpw:String
//    ) : Call<Login>
//}