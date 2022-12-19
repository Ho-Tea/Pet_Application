package com.example.happydog.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
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
import com.example.happydog.database
import com.example.happydog.model.ChatModel
import com.example.happydog.model.PetSitting
import com.example.happydog.model.Profile
import com.example.happydog.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_message.*
import kotlinx.android.synthetic.main.fragment_profile.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [FriendProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FriendProfileFragment : Fragment() {
    companion object{
        private var imageUri : String? = "https://www.eformsign.com/eform/document/external_user_view_service.html?company_id=78e37bac301b49b78c16642375b2c2cc&form_id=740c39a42add4b7d8af82fb8ad024d02&recipient="
        private val fireStorage = FirebaseStorage.getInstance().reference
        private val fireDatabase = FirebaseDatabase.getInstance().reference
        private val user = Firebase.auth.currentUser
        private val countList = HashMap<String,Int>()
        private val count = 0
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
        val counted = view?.findViewById<TextView>(R.id.count)

        //프로필 구현
        fireDatabase.child("users").child(uid.toString()).addListenerForSingleValueEvent(object :
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
                if(countList.containsKey(friendUid)) {
                    counted?.text = countList.getValue(friendUid.toString()).toString()
                }
            }
        })


        cancelButton?.setOnClickListener {
            (activity as MainActivity).fragmentChange(FriendFragment.newInstance())
        }
        msgButton?.setOnClickListener {
            val intent = Intent(context, MessageActivity::class.java)
            intent.putExtra("destinationUid", friendUid)
            intent.putExtra("request", "0")
            context?.startActivity(intent)
        }

        requestPet?.setOnClickListener {
            val intent = Intent(context, MessageActivity::class.java)
            intent.putExtra("destinationUid", friendUid)
            intent.putExtra("request", "1")
            if (countList.containsKey(friendUid.toString())) {
                countList.set(friendUid.toString(), countList.getValue(friendUid.toString()) + 1)
            } else {
                countList.put(friendUid.toString(), 1)
            }


//            FirebaseStorage.getInstance().reference.child("petsitting")
//                .child("default/delete.png").downloadUrl
//                .addOnSuccessListener {
                            val petsitting = PetSitting(
                                user?.uid.toString(),
                                friendUid.toString(),
                                imageUri.toString(),
                                countList.getValue(friendUid.toString()).toString()
                            )

                            fireDatabase.child("petsitting").child(user?.uid.toString())
                                .setValue(petsitting)
//                        }
            context?.startActivity(intent)

        }

        return view
    }

}