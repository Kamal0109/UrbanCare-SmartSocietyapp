package com.example.client

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.client.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.saveButton.setOnClickListener {

            val name = binding.uploadName.text.toString()
            val phoneNo = binding.uploadPhoneNo.text.toString()
            val societyName = binding.uploadSocietyName.text.toString()
            val flatNo = binding.uploadFlatNo.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Users")
            val users = User(name, phoneNo, societyName, flatNo)
            database.child(phoneNo).setValue(users).addOnSuccessListener {

                binding.uploadName.text.clear()
                binding.uploadPhoneNo.text.clear()
                binding.uploadSocietyName.text.clear()
                binding.uploadFlatNo.text.clear()

                Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show()



            }.addOnFailureListener {
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }

        }


    }
}