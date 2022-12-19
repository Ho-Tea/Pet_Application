package com.example.happydog.fragment

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.happydog.MainActivity
import com.example.happydog.MessageActivity
import com.example.happydog.R
import com.example.happydog.model.Profile
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_home.*


class FriendFragment : Fragment() {
    companion object {
        fun newInstance(): FriendFragment {
            return FriendFragment()
        }
    }

    private lateinit var database: DatabaseReference
    private var friend: ArrayList<Profile> = arrayListOf()
    private var filterFriends: ArrayList<Profile> = arrayListOf()
    var textlength = 0

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
    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        database = Firebase.database.reference
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.home_recycler)
        //this는 액티비티에서 사용가능, 프래그먼트는 requireContext()로 context 가져오기
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = RecyclerViewAdapter()
        val cancelButton = view.findViewById<ImageView>(R.id.cancelItem)
        val addfriend = view.findViewById<ImageView>(R.id.addpet_image)
        val search_edt=view.findViewById<EditText>(R.id.search_view)
        search_edt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(edit: Editable?) {
            }
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                textlength=search_edt.text.length
                filterFriends.clear()
                var str_sequence=charSequence.toString()
                for(i in friend.indices){
                    if(friend[i].name.toString().contains(str_sequence) && friend[i].uid.toString() != Firebase.auth.currentUser?.uid.toString())
                        filterFriends.add(friend[i])

                }
                recyclerView.adapter = SecondRecyclerViewAdapter()
            }
        })
        cancelButton?.setOnClickListener {
            (activity as MainActivity).fragmentChange(newInstance())
        }
//        addfriend?.setOnClickListener {
//            ()
//        }



        return view
    }
    inner class SecondRecyclerViewAdapter : RecyclerView.Adapter<SecondRecyclerViewAdapter.SecondCustomViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecondCustomViewHolder {

            return SecondCustomViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_home, parent, false)
            )
        }

        inner class SecondCustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView: ImageView = itemView.findViewById(R.id.home_item_iv)
            val textView: TextView = itemView.findViewById(R.id.home_item_tv)
            val textViewEmail: TextView = itemView.findViewById(R.id.home_item_email)
            val locationView : TextView = itemView.findViewById(R.id.location)

//
        }

        override fun onBindViewHolder(holder: SecondCustomViewHolder, position: Int) {
                Glide.with(holder.itemView.context).load(filterFriends[position].profileImageUrl)
                    .apply(RequestOptions().circleCrop())
                    .into(holder.imageView)
                holder.textView.text = filterFriends[position].name
                holder.textViewEmail.text = filterFriends[position].email
                holder.locationView.text = filterFriends[position].location
                holder.imageView.setOnClickListener {
                    (activity as MainActivity).fragmentChange(
                        FriendProfileFragment.newInstance(
                            filterFriends[position].uid.toString()
                        )
                    )
                }
                holder.itemView.setOnClickListener {
                    val intent = Intent(context, MessageActivity::class.java)
                    intent.putExtra("destinationUid", filterFriends[position].uid)
                    context?.startActivity(intent)
                }

        }


        override fun getItemCount(): Int {
            return filterFriends.size
        }
    }

    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder>() {
        init {
            val myUid = Firebase.auth.currentUser?.uid.toString()
            FirebaseDatabase.getInstance().reference.child("users")
                .addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(error: DatabaseError) {
                    }

                    override fun onDataChange(snapshot: DataSnapshot) {
                        friend.clear()
                        for (data in snapshot.children) {
                            val item = data.getValue<Profile>()
                            if (item?.uid.equals(myUid)) {
                                friend.add(0, item!!)
                                continue
                            } // 본인은 친구창에서 제외
                            friend.add(item!!)
                        }
                        notifyDataSetChanged()
                    }
                })

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

            return CustomViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_home, parent, false)
            )
        }

        inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView: ImageView = itemView.findViewById(R.id.home_item_iv)
            val textView: TextView = itemView.findViewById(R.id.home_item_tv)
            val textViewEmail: TextView = itemView.findViewById(R.id.home_item_email)
            val locationView : TextView = itemView.findViewById(R.id.location)
//            val selfImageView: ImageView = itemView.findViewById(R.id.home_item_self_iv)
//            val selfTextView : TextView = itemView.findViewById(R.id.home_item_self_tv)
//            val heartImage : ImageView = itemView.findViewById(R.id.home_item_self_vv)
        }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            if (position == 0) {
                Glide.with(holder.itemView.context).load(friend[position].profileImageUrl)
                    .apply(RequestOptions().circleCrop())
                    .into(holder.imageView)
                holder.textView.text = "내 프로필"
                holder.textViewEmail.text = ""
                holder.locationView.text = friend[position].location
                holder.itemView.setOnClickListener {
                    (activity as MainActivity).fragmentChange(ProfileFragment.newInstance())
                }
            } else {
                Glide.with(holder.itemView.context).load(friend[position].profileImageUrl)
                    .apply(RequestOptions().circleCrop())
                    .into(holder.imageView)
                holder.textView.text = friend[position].name
                holder.textViewEmail.text = friend[position].email
                holder.locationView.text = friend[position].location
                holder.itemView.setOnClickListener {
                    (activity as MainActivity).fragmentChange(FriendProfileFragment.newInstance(friend[position].uid.toString()))
                }
//                holder.itemView.setOnClickListener {
//                    val intent = Intent(context, MessageActivity::class.java)
//                    intent.putExtra("destinationUid", friend[position].uid)
//                    context?.startActivity(intent)
//                }
            }


        }


        override fun getItemCount(): Int {
            return friend.size
        }

    }
}
