package com.example.happydog.fragment

import android.annotation.SuppressLint
import android.media.Image
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.happydog.MainActivity
import com.example.happydog.R
import com.example.happydog.RetrofitBuilder
import com.example.happydog.model.Friend
import com.example.happydog.model.PetSitting
import com.example.happydog.model.Post
import com.example.happydog.model.Profile
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_message.*
import retrofit2.Call


class PlusFriendFragment : Fragment() {
    companion object {
        private val user = Firebase.auth.currentUser
        private val uid = user?.uid.toString()
        private val fireDatabase = FirebaseDatabase.getInstance().reference
        private var friend: ArrayList<Profile> = arrayListOf()
        private var initFriend: ArrayList<String> = arrayListOf()
//        private var data : Post? = null
        fun newInstance(): PlusFriendFragment {
            return PlusFriendFragment()
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Response(uid)
    }

    fun Response(uid: String) {
        val call = RetrofitBuilder.api.getLocationResponse(uid,0,15)
        call.enqueue(object :  retrofit2.Callback<Post> {
            override fun onResponse(call: Call<Post>, response: retrofit2.Response<Post>) {
                if(response.isSuccessful){
                    initFriend.clear()
                    for(users in response.body()!!.contents) {
                        initFriend.add(users.uid)
                    }
                    Log.d("들어옴", response.body()!!.contents.get(1).uid)
//
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Log.d("Connection Failure", t.localizedMessage)
            }
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_plus_friend, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.plus_recyclerview)
        //this는 액티비티에서 사용가능, 프래그먼트는 requireContext()로 context 가져오기
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = RecyclerViewAdapter()
        val cancelButton = view.findViewById<ImageView>(R.id.cancel)


        cancelButton?.setOnClickListener {
            (activity as MainActivity).fragmentChange(FriendFragment.newInstance())
        }
        return view
    }


    inner class RecyclerViewAdapter : RecyclerView.Adapter<PlusFriendFragment.RecyclerViewAdapter.CustomViewHolder>() {
        init {
//            initFriend.clear()
//            for(user in data.contents) {
//                initFriend.add(user.id)
//            }
            val myUid = Firebase.auth.currentUser?.uid.toString()
            fireDatabase.child("users")
                .addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(error: DatabaseError) {
                    }

                    override fun onDataChange(snapshot: DataSnapshot) {
                        friend.clear()
                        for (data in snapshot.children) {
                                val item = data.getValue<Profile>()
                                if(item?.uid.equals(myUid)){
                                    continue
                                    }
                                if (initFriend.contains(item?.uid)) {
                                    friend.add(item!!)
                            }
                        }
                        notifyDataSetChanged()
                    }
                })
        }


        inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView: ImageView = itemView.findViewById(R.id.home_item_iv)
            val textView: TextView = itemView.findViewById(R.id.home_item_tv)
            val textViewEmail: TextView = itemView.findViewById(R.id.home_item_email)
            val locationView: TextView = itemView.findViewById(R.id.location)
            val addbutton : ImageView = itemView.findViewById(R.id.plusbutton)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
            return CustomViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_self, parent, false)
            )
        }

        override fun getItemCount(): Int {
            return friend.size
        }

        override fun onBindViewHolder(holder: PlusFriendFragment.RecyclerViewAdapter.CustomViewHolder, position: Int) {
                    Glide.with(holder.itemView.context).load(friend[position].profileImageUrl)
                        .apply(RequestOptions().circleCrop())
                        .into(holder.imageView)
                    holder.textView.text = PlusFriendFragment.friend[position].name
                    holder.textViewEmail.text = PlusFriendFragment.friend[position].email
                    holder.locationView.text = PlusFriendFragment.friend[position].location
                    holder.addbutton.setOnClickListener {
                        var friends = Friend(
                            friend[position].email.toString(),
                            friend[position].name.toString(),
                            friend[position].profileImageUrl.toString(),
                            friend[position].uid.toString(),
                            friend[position].location.toString()

                        )
                        fireDatabase.child("friends").child(
                            uid).child(friend[position].uid.toString())
                            .setValue(friends)
                        friend.removeAt(position)
                        Toast.makeText(requireContext(), "친구가 추가되었습니다", Toast.LENGTH_SHORT).show()
                    }
        }
    }
}