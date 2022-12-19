package com.example.happydog.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
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
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.happydog.MainActivity
import com.example.happydog.R
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
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {
    companion object{
        private var imageUri : Uri? = null
        private val fireStorage = FirebaseStorage.getInstance().reference
        private val fireDatabase = FirebaseDatabase.getInstance().reference
        private val user = Firebase.auth.currentUser
        private val uid = user?.uid.toString()
                fun newInstance() : ProfileFragment {
            return ProfileFragment()
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
    private val getContent =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if(result.resultCode == AppCompatActivity.RESULT_OK) {
                    imageUri = result.data?.data //이미지 경로 원본
                    profile_imageview.setImageURI(imageUri) //이미지 뷰를 바꿈

                    //기존 사진을 삭제 후 새로운 사진을 등록
                    fireStorage.child("userImages/$uid/photo").delete().addOnSuccessListener {
                        fireStorage.child("userImages/$uid/photo").putFile(imageUri!!).addOnSuccessListener {
                            fireStorage.child("userImages/$uid/photo").downloadUrl.addOnSuccessListener {
                                val photoUri : Uri = it
                                println("$photoUri")
                                fireDatabase.child("users/$uid/profileImageUrl").setValue(photoUri.toString())
                                Toast.makeText(requireContext(), "프로필사진이 변경되었습니다.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    Log.d("이미지", "성공")
                }
                else{
                    Log.d("이미지", "실패")
                }
            }
    //뷰가 생성되었을 때
    //프레그먼트와 레이아웃을 연결시켜주는 부분
    override fun onCreateView(

            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        //view 선언을 안하고 return에 바로 적용시키면 glide가 작동을 안함
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val photo = view?.findViewById<ImageView>(R.id.profile_imageview)
        val email = view?.findViewById<TextView>(R.id.profile_textview_email)
        val name = view?.findViewById<TextView>(R.id.profile_textview_name)
        val sex = view?.findViewById<TextView>(R.id.profile_textview_sex)
        val type = view?.findViewById<TextView>(R.id.profile_textview_type)
        val location = view?.findViewById<TextView>(R.id.profile_textview_location)
        val mbti = view?.findViewById<TextView>(R.id.profile_textview_tendency)
        val button = view?.findViewById<Button>(R.id.profile_button)
        val mbti_button = view?.findViewById<ImageView>(R.id.mbti_button)
        val account_button = view?.findViewById<ImageView>(R.id.account)
        val petsitting = view?.findViewById<ImageView>(R.id.petsitting)


        //프로필 구현
        fireDatabase.child("users").child(uid).addListenerForSingleValueEvent(object : ValueEventListener {
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
                sex?.text = userProfile?.sex
                type?.text = userProfile?.type
                location?.text = userProfile?.location
                mbti?.text = userProfile?.tendency
            }
        })
//        //프로필사진 바꾸기
//        photo?.setOnClickListener{
//            val intentImage = Intent(Intent.ACTION_PICK)
//            intentImage.type = MediaStore.Images.Media.CONTENT_TYPE
//            getContent.launch(intentImage)
//        }
        button?.setOnClickListener{
            (activity as MainActivity).fragmentChange(SetProfileFragment.newInstance()) // 버튼 클릭시 setprofile 프래그머트 호출

//            if(name?.text!!.isNotEmpty()) {
//                fireDatabase.child("users/$uid/name").setValue(name.text.toString())
//                name.clearFocus()
//                Toast.makeText(requireContext(), "이름이 변경되었습니다.", Toast.LENGTH_SHORT).show()
//            }

        }
        var friendUid : String? = null
        petsitting?.setOnClickListener{
            fireDatabase.child("petsitting").child(uid).addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(error: DatabaseError) {
                    }
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val petsitt = snapshot.getValue<PetSitting>()
                        println(petsitt)
                        friendUid = petsitt?.destUid
                        (activity as MainActivity).fragmentChange(PetSittingFragment.newInstance(friendUid.toString()))
                    }
                })

        }

        mbti_button?.setOnClickListener{
            val MyIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://doda.app/quiz/wap7xT8Dlu"))
            startActivity(MyIntent)
        }
        account_button?.setOnClickListener{
            (activity as MainActivity).fragmentChange(AccountFragment.newInstance("1","2")) // 버튼 클릭시 setprofile 프래그머트 호출
        }

        return view
    }
}