package com.example.happydog.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.happydog.MainActivity
import com.example.happydog.R
import com.example.happydog.model.Friend
import com.example.happydog.model.Profile
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.google.firebase.storage.FirebaseStorage


/**
 * A simple [Fragment] subclass.
 * Use the [PetSittingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PetSittingFragment : Fragment() {
    companion object{
        private var imageUri : Uri? = null
        private val fireStorage = FirebaseStorage.getInstance().reference
        private val fireDatabase = FirebaseDatabase.getInstance().reference
        private val countList = HashMap<String,Int>()
        private val count = 0
        private lateinit var uid : String
        private var friendUid : String = "0"
        fun newInstance(uid : String) : PetSittingFragment {
            this.uid = uid
            return PetSittingFragment()
        }

    }

    //메모리에 올라갔을 때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    //프레그먼트를 포함하고 있는 액티비티에 붙었을 때
    override fun onAttach(context: Context) {
        super.onAttach(context)

    }
    //뷰가 생성되었을 때
    //프레그먼트와 레이아웃을 연결시켜주는 부분
    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //view 선언을 안하고 return에 바로 적용시키면 glide가 작동을 안함
        val view = inflater.inflate(R.layout.fragment_pet_sitting, container, false)
        val photo = view?.findViewById<ImageView>(R.id.home_item_iv)
        val name = view?.findViewById<TextView>(R.id.home_item_tv)
        val email = view?.findViewById<TextView>(R.id.home_item_email)
        val cancelButton = view?.findViewById<ImageView>(R.id.cancel)
        val url = view?.findViewById<ImageView>(R.id.urls)


        //프로필 구현
        fireDatabase.child("users").child(uid).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                val userProfile = snapshot.getValue<Profile>()
                println(userProfile)
                Glide.with(requireContext()).load(userProfile?.profileImageUrl)
                    .apply(RequestOptions().circleCrop())
                    .into(photo!!)
                email?.text = userProfile?.email
                name?.text = userProfile?.name

            }
        })
        cancelButton?.setOnClickListener {
            (activity as MainActivity).fragmentChange(ProfileFragment.newInstance())
        }
        url?.setOnClickListener() {
            val MyIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.eformsign.com/eform/document/external_user_view_service.html?company_id=78e37bac301b49b78c16642375b2c2cc&form_id=740c39a42add4b7d8af82fb8ad024d02&recipient="))
            startActivity(MyIntent)
        }
        return view
    }
}