package com.example.happydog.fragment

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
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.happydog.MainActivity
import com.example.happydog.MessageActivity
import com.example.happydog.R
import com.example.happydog.model.ChatModel
import com.example.happydog.model.Friend
import com.example.happydog.model.Profile
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import java.util.*
import java.util.Collections.reverseOrder
import kotlin.collections.ArrayList

class ChatFragment : Fragment() {
    
    companion object{
                fun newInstance() : ChatFragment {
            return ChatFragment()
        }
    }
    
    private val fireDatabase = FirebaseDatabase.getInstance().reference
    private val chatModels = ArrayList<ChatModel>()
    private val friends: ArrayList<Profile> = arrayListOf()
    private val chatModel = ArrayList<ChatModel>()
    private val filterFriends : ArrayList<Profile> = arrayListOf()
    private val name_chating = ArrayList<Friend>()
    private var textlength = 0
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
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_chat, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.chatfragment_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = RecyclerViewAdapter()
        val cancelButton = view.findViewById<ImageView>(R.id.cancelItem)
        val search_edt=view.findViewById<EditText>(R.id.search_view)
        search_edt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(edit: Editable?) {
            }
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                textlength=search_edt.text.length
                chatModels.clear()
                filterFriends.clear()
                var str_sequence=charSequence.toString()
                for(i in chatModel.indices){
                    for(key in chatModel[i].users.keys) {
                        for (j in friends.indices) {
                            if (friends[j].uid.toString().equals(key) && friends[j].name.toString()
                                    .contains(str_sequence)
                            ) {
                                chatModels.add(chatModel[i])
                                filterFriends.add(friends[j])
                            }
                        }
                    }
                    }
                recyclerView.adapter = SecondRecyclerViewAdapter()
            }
        })
        cancelButton?.setOnClickListener {
            (activity as MainActivity).fragmentChange(ChatFragment.newInstance())
        }
        return view
    }
    inner class SecondRecyclerViewAdapter : RecyclerView.Adapter<ChatFragment.SecondRecyclerViewAdapter.SecondCustomViewHolder>() {

//        private val chatModel = ArrayList<ChatModel>()
        private var uid : String? = null
        private val destinationUsers : ArrayList<String> = arrayListOf()
//
//        init {
//            uid = Firebase.auth.currentUser?.uid.toString()
//            println(uid)
//
//            fireDatabase.child("chatrooms").orderByChild("users/$uid").equalTo(true).addListenerForSingleValueEvent(object : ValueEventListener{
//                override fun onCancelled(error: DatabaseError) {
//                }
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    filterChat.clear()
//                    for(data in snapshot.children){
//                        filterChat.add(data.getValue<ChatModel>()!!)
//                        println(data)
//                    }
//                    notifyDataSetChanged()
//                }
//            })
//        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecondCustomViewHolder {

            return SecondCustomViewHolder(LayoutInflater.from(context).inflate(R.layout.item_chat, parent, false))
        }

        inner class SecondCustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView: ImageView = itemView.findViewById(R.id.chat_item_imageview)
            val textView_title : TextView = itemView.findViewById(R.id.chat_textview_title)
            val textView_lastMessage : TextView = itemView.findViewById(R.id.chat_item_textview_lastmessage)
        }

        override fun onBindViewHolder(holder: SecondCustomViewHolder, position: Int) {
            var destinationUid: String? = null



            //채팅방에 있는 유저 모두 체크
                for (user in chatModels[position].users.keys) {

                    if (!user.equals(uid)) {
                        destinationUid = user
                        destinationUsers.add(destinationUid)
                }}
//            fireDatabase.child("users").child("$destinationUid").addListenerForSingleValueEvent(object : ValueEventListener {
//                override fun onCancelled(error: DatabaseError) {
//                }
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    val friend = snapshot.getValue<Friend>()

                    Glide.with(holder.itemView.context).load(filterFriends[position]?.profileImageUrl)
                        .apply(RequestOptions().circleCrop())
                        .into(holder.imageView)
                    holder.textView_title.text = filterFriends[position]?.name
                //}
//            })
            //메세지 내림차순 정렬 후 마지막 메세지의 키값을 가져옴
            val commentMap = TreeMap<String, ChatModel.Comment>(reverseOrder())
            commentMap.putAll(chatModels[position].comments)
            val lastMessageKey = commentMap.keys.toTypedArray()[0]
            holder.textView_lastMessage.text = chatModels[position].comments[lastMessageKey]?.message
            if(holder.textView_lastMessage.text.length > 20) {
                holder.textView_lastMessage.text = holder.textView_lastMessage.text.substring(0,18)
            }
            //채팅창 선책 시 이동
            holder.itemView.setOnClickListener {
                val intent = Intent(context, MessageActivity::class.java)
                intent.putExtra("destinationUid", destinationUsers[position])
                context?.startActivity(intent)
            }
        }
        override fun getItemCount(): Int {
            return chatModels.size
        }
    }

    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder>() {

//        private val chatModel = ArrayList<ChatModel>()
        private var uid : String? = null
        private val destinationUsers : ArrayList<String> = arrayListOf()

        init {
            uid = Firebase.auth.currentUser?.uid.toString()
            println(uid)

            fireDatabase.child("chatrooms").orderByChild("users/$uid").equalTo(true).addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onCancelled(error: DatabaseError) {
                }
                override fun onDataChange(snapshot: DataSnapshot) {
                    chatModel.clear()
                    name_chating.clear()
                    for(data in snapshot.children){
                        chatModel.add(data.getValue<ChatModel>()!!)
                        name_chating.add(data.getValue<Friend>()!!)
                        println(data)
                    }
                    notifyDataSetChanged()
                }
            })
                val myUid = Firebase.auth.currentUser?.uid.toString()
                FirebaseDatabase.getInstance().reference.child("users")
                    .addValueEventListener(object : ValueEventListener {
                        override fun onCancelled(error: DatabaseError) {
                        }

                        override fun onDataChange(snapshot: DataSnapshot) {
                            friends.clear()
                            for (data in snapshot.children) {
                                val item = data.getValue<Profile>()
                                if (item?.uid.equals(myUid)) {
                                    //friend.add(0, item!!)
                                    continue
                                } // 본인은 친구창에서 제외
                                friends.add(item!!)
                            }
                            notifyDataSetChanged()
                        }
                    })


        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

            return CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.item_chat, parent, false))
        }

        inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView: ImageView = itemView.findViewById(R.id.chat_item_imageview)
            val textView_title : TextView = itemView.findViewById(R.id.chat_textview_title)
            val textView_lastMessage : TextView = itemView.findViewById(R.id.chat_item_textview_lastmessage)
        }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            var destinationUid: String? = null
            //채팅방에 있는 유저 모두 체크
            for (user in chatModel[position].users.keys) {
                if (!user.equals(uid)) {
                    destinationUid = user
                    destinationUsers.add(destinationUid)
                }
            }
            fireDatabase.child("users").child("$destinationUid").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                }
                override fun onDataChange(snapshot: DataSnapshot) {
                    val friend = snapshot.getValue<Friend>()
                    Glide.with(holder.itemView.context).load(friend?.profileImageUrl)
                            .apply(RequestOptions().circleCrop())
                            .into(holder.imageView)
                    holder.textView_title.text = friend?.name
                }
            })
            //메세지 내림차순 정렬 후 마지막 메세지의 키값을 가져옴
            val commentMap = TreeMap<String, ChatModel.Comment>(reverseOrder())
            commentMap.putAll(chatModel[position].comments)
            val lastMessageKey = commentMap.keys.toTypedArray()[0]
            holder.textView_lastMessage.text = chatModel[position].comments[lastMessageKey]?.message
            if(holder.textView_lastMessage.text.length > 20) {
                holder.textView_lastMessage.text = holder.textView_lastMessage.text.substring(0,18)
            }

            //채팅창 선책 시 이동
            holder.itemView.setOnClickListener {
                val intent = Intent(context, MessageActivity::class.java)
                intent.putExtra("destinationUid", destinationUsers[position])
                context?.startActivity(intent)
            }
        }
        override fun getItemCount(): Int {
            return chatModel.size
        }
    }
}