package com.example.adminprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminprofile.databinding.ActivityShowBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ShowActivity : AppCompatActivity() {

    lateinit var showBinding : ActivityShowBinding

    val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    val myreference : DatabaseReference = database.reference.child("Users")
    private val usersList: ArrayList<User> = ArrayList()
    private lateinit var usersAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showBinding = ActivityShowBinding.inflate(layoutInflater)
        val view = showBinding.root
        setContentView(view)

        usersAdapter = UserAdapter(this,usersList)
        showBinding.recyclerView.layoutManager = LinearLayoutManager(this)



        retrieveDataFromDatabase()

    }

    fun retrieveDataFromDatabase(){

        myreference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                for(eachUser in snapshot.children){

                    val user = eachUser.getValue(User::class.java)

                    if(user != null){
                        println("userId : ${user.userId}")
                        println("userId : ${user.userName}")
                        println("userEmail : ${user.email}")
                        println("userPhoneNo : ${user.phoneNo}")
                        println("*************************")

                    }

                }

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }


}