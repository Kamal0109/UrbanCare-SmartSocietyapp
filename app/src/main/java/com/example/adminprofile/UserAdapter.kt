package com.example.adminprofile

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.adminprofile.databinding.UserItemBinding

class UserAdapter(var context : Context,
                  var userList: ArrayList<User>) {

    inner class UserViewHolder(val adapter: UserItemBinding) : RecyclerView.ViewHolder(adapter.root){}


}