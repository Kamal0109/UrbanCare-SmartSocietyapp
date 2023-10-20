package com.example.adminprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.adminprofile.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding : ActivityMainBinding

    val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    val myReference : DatabaseReference = database.reference.child("Users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        mainBinding.button.setOnClickListener {

            addUserToDatabase()
            val intent = Intent(this@MainActivity,HomeActivity::class.java)
            startActivity(intent)

        }

        mainBinding.buttonAdmin.setOnClickListener {

            val intent = Intent(this@MainActivity,AdminActivity::class.java)
            startActivity(intent)
            finish()

        }

    }

    fun addUserToDatabase(){

        val name : String = mainBinding.editTextName.text.toString()
        val email : String = mainBinding.editTextEmail.text.toString()
        val phoneNo : String = mainBinding.editTextPhoneNo.text.toString()

        val id : String = myReference.push().key.toString()

        val user = User(name,email,phoneNo)

        myReference.child(id).setValue(user).addOnCompleteListener {task->

            if(task.isSuccessful){
                Toast.makeText(applicationContext,"The new User has been added to database",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext,task.exception.toString(),Toast.LENGTH_SHORT).show()
            }

        }

    }

}