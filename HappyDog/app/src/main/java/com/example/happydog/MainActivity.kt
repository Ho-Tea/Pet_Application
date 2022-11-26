package com.example.happydog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.happydog.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

private lateinit var auth: FirebaseAuth

private lateinit var friendFragment: FriendFragment
private lateinit var chatFragment: ChatFragment
private lateinit var profileFragment: ProfileFragment
private lateinit var accoutFragment: AccountFragment

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = Firebase.auth

        bottom_nav.setOnNavigationItemSelectedListener(BottomNavItemSelectedListener)

        friendFragment = FriendFragment.newInstance()
//        supportFragmentManager.beginTransaction().add(R.id.fragments_frame, friendFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.fragments_frame, friendFragment).commit()

    }
    private val BottomNavItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener{
        when(it.itemId){
            R.id.menu_friend -> {
                friendFragment = FriendFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragments_frame, friendFragment).commit()
            }
            R.id.menu_chat -> {
                chatFragment = ChatFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragments_frame, chatFragment).commit()
            }
            R.id.menu_profile -> {
                profileFragment = ProfileFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragments_frame, profileFragment).commit()
            }
            R.id.menu_option -> {
                accoutFragment = AccountFragment.newInstance("1","2")
                supportFragmentManager.beginTransaction().replace(R.id.fragments_frame, accoutFragment).commit()
            }
        }
        true
    }


}