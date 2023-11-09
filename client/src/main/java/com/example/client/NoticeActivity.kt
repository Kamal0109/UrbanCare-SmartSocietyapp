package com.example.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class NoticeActivity : AppCompatActivity() {

    private lateinit var noticeAdapter: NoticeAdapter
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Retrieve notices from Firebase Firestore

        val notices = listOf(
        Notice("Diwali Celebration", "Happy Diwali", "https://firebasestorage.googleapis.com/v0/b/adminprofile-e7362.appspot.com/o/images%2Fimage1.jpg?alt=media&token=179c3dc6-bb9f-4ed5-9d20-058ca0025b57"),
        Notice("Birthday Celebration", "Birthday celebration of mayank sharma", "https://firebasestorage.googleapis.com/v0/b/adminprofile-e7362.appspot.com/o/images%2Fyour_image.jpg?alt=media&token=97545298-72bf-4c34-b41d-3c556631cde3"),
        // Add more notices as needed
        )

        // Initialize the NoticeAdapter with the list of notices
        noticeAdapter = NoticeAdapter(notices)
        recyclerView.adapter = noticeAdapter
    }
}