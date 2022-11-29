package com.example.happydog.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.happydog.MainActivity
import com.example.happydog.MessageActivity
import com.example.happydog.R
import com.example.happydog.model.Profile
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_profile.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [FriendProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FriendProfileFragment : Fragment() {
    companion object{
        private var imageUri : Uri? = null
        private val fireStorage = FirebaseStorage.getInstance().reference
        private val fireDatabase = FirebaseDatabase.getInstance().reference
        private lateinit var uid : String
        private var friendUid : String? = "0"
        fun newInstance(uid : String) : FriendProfileFragment {
            this.uid = uid
            return FriendProfileFragment()
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
        val view = inflater.inflate(R.layout.fragment_friend_profile, container, false)
        val photo = view?.findViewById<ImageView>(R.id.profile_imageview)
        val name = view?.findViewById<TextView>(R.id.profile_textview_name)
        val email = view?.findViewById<TextView>(R.id.profile_textview_email)
        val cancelButton = view?.findViewById<ImageView>(R.id.cancel)
        val msgButton = view?.findViewById<ImageView>(R.id.sendMessage)
        val requestPet = view?.findViewById<ImageView>(R.id.requestContract)

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
                friendUid = userProfile?.uid

            }
        })
        cancelButton?.setOnClickListener {
            (activity as MainActivity).fragmentChange(FriendFragment.newInstance())
        }
        msgButton?.setOnClickListener {
            val intent = Intent(context, MessageActivity::class.java)
            intent.putExtra("destinationUid", friendUid)
            context?.startActivity(intent)
        }

        return view
    }
}