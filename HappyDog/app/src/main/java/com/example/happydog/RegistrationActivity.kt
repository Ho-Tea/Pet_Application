package com.example.happydog

import android.Manifest
import android.content.Intent
import android.location.Geocoder
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.example.happydog.model.Profile
import com.example.happydog.model.User
import com.google.android.gms.common.api.Response
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_registration.*
import java.util.*
import retrofit2.*


private lateinit var auth: FirebaseAuth
lateinit var database: DatabaseReference

@Suppress("DEPRECATION")
class RegistrationActivity : AppCompatActivity() {
    private var imageUri: Uri? = null
    private var lati : String? = null
    private var longti : String? = null
    fun Regsiter(user: User) {
        val call = RetrofitBuilder.api.postLocationResponse(user)
        call.enqueue(object :  retrofit2.Callback<String> {
            override fun onResponse(call: Call<String>, response: retrofit2.Response<String>) {
                Log.d("히히히히히히히히히ㅣㅎ",response.body().toString())
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("Connection Failure", t.localizedMessage)
            }
        })
    }

    //이미지 등록
    private val getContent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                imageUri = result.data?.data //이미지 경로 원본
                registration_iv.setImageURI(imageUri) //이미지 뷰를 바꿈
                Log.d("이미지", "성공")
            } else {
                Log.d("이미지", "실패")
            }
        }

    private fun getAddress(position: LatLng): String {
        val geoCoder = Geocoder(this, Locale.KOREA)
        var addr = "주소 오류"
        longti = position.longitude.toString()
        lati = position.latitude.toString()
        //GRPC 오류? try catch 문으로 오류 대처
        try {
            addr = geoCoder.getFromLocation(position.latitude, position.longitude, 1).first()
                .getAddressLine(0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        Toast.makeText(this, addr, Toast.LENGTH_SHORT).show()
        return addr
    }

    private fun getLatLng(address: String): LatLng {
        val geoCoder = Geocoder(this, Locale.KOREA)   // Geocoder 로 자기 나라에 맞게 설정
        val list = geoCoder.getFromLocationName(address, 3)

        var location = LatLng(37.554891, 126.970814)     //임시 서울역

        if (list != null) {
            if (list.size == 0) {
                Log.d("GeoCoding", "해당 주소로 찾는 위도 경도가 없습니다. 올바른 주소를 입력해주세요.")
                Toast.makeText(this, "해당 주소로 찾는 위도 경도가 없습니다. 올바른 주소를 입력해주세요.", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val addressLatLng = list[0]
                location = LatLng(addressLatLng.latitude, addressLatLng.longitude)
                return location
            }
        }

        return location
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        auth = Firebase.auth
        database = Firebase.database.reference

        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            1
        )

        val email = et_registration_id.text
        val password = et_registration_password.text
        val name = findViewById<EditText>(R.id.et_registration_name).text
        val location = findViewById<EditText>(R.id.et_registration_location).text
        val button = findViewById<Button>(R.id.btn_registration)
        val profile = findViewById<ImageView>(R.id.registration_iv)
        val location_button = findViewById<ImageView>(R.id.location)

        var profileCheck = false

        profile.setOnClickListener {
            val intentImage = Intent(Intent.ACTION_PICK)
            intentImage.type = MediaStore.Images.Media.CONTENT_TYPE
            getContent.launch(intentImage)
            profileCheck = true
        }

        val intent = Intent(this, LoginActivity::class.java)
        location_button.setOnClickListener {
            getAddress(getLatLng(location.toString()))

        }

        button.setOnClickListener {
            if (email.isEmpty() && password.isEmpty() && name.isEmpty() && profileCheck && location.isEmpty()) {
                Toast.makeText(this, "아이디와 비밀번호, 위치, 프로필 사진을 제대로 입력해주세요.", Toast.LENGTH_SHORT)
                    .show()
                Log.d("Email", "$email, $password")
            } else {
                if (!profileCheck) {
                    Toast.makeText(this, "프로필사진을 등록해주세요.", Toast.LENGTH_SHORT).show()
                } else {
                    auth.createUserWithEmailAndPassword(email.toString(), password.toString())
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                val user = Firebase.auth.currentUser
                                val userId = user?.uid
                                val userIdSt = userId.toString()

                                FirebaseStorage.getInstance()
                                    .reference.child("userImages").child("$userIdSt/photo")
                                    .putFile(imageUri!!).addOnSuccessListener {
                                        var userProfile: Uri? = null
                                        FirebaseStorage.getInstance().reference.child("userImages")
                                            .child("$userIdSt/photo").downloadUrl
                                            .addOnSuccessListener {
                                                userProfile = it
                                                Log.d("이미지 URL", "$userProfile")
                                                val friend = Profile(
                                                    email.toString(),
                                                    name.toString(),
                                                    userProfile.toString(),
                                                    userIdSt,
                                                    location.toString()
                                                )
                                                val user = User()
                                                user.email = email.toString()
                                                user.username = name.toString()
                                                user.uid = userIdSt
                                                user.coordinate = User.CoordinateDto(longti, lati)
                                                Regsiter(user)


                                                database.child("users").child(userId.toString())
                                                    .setValue(friend)
                                            }
                                    }
                                Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                                Log.e(TAG, "$userId")
                                startActivity(intent)
                            } else {
                                Toast.makeText(this, "등록에 실패했습니다.", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload();
        }
    }

    private fun reload() {
    }

    companion object {
        private const val TAG = "EmailPassword"
    }

}

