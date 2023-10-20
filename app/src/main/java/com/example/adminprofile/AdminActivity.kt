package com.example.adminprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.adminprofile.databinding.ActivityAdminBinding
import com.example.adminprofile.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AdminActivity : AppCompatActivity() {

    lateinit var adminBinding: ActivityAdminBinding

    val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    val myReference : DatabaseReference = database.reference.child("Admins")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adminBinding = ActivityAdminBinding.inflate(layoutInflater)
        val view = adminBinding.root
        setContentView(view)

        adminBinding.button.setOnClickListener {

            addUserToDatabase()
            val intent = Intent(this@AdminActivity,HomeActivity::class.java)
            startActivity(intent)

        }

    }


    fun addUserToDatabase(){

        val name : String = adminBinding.editTextName.text.toString()
        val email : String = adminBinding.editTextEmail.text.toString()
        val phoneNo : String = adminBinding.editTextPhoneNo.text.toString()

        val id : String = myReference.push().key.toString()

        val admin = Admin(id,email,phoneNo)

        myReference.child(id).setValue(admin).addOnCompleteListener {task->

            if(task.isSuccessful){
                Toast.makeText(applicationContext,"The new admin has been added to database", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext,task.exception.toString(), Toast.LENGTH_SHORT).show()
            }

        }

    }
}