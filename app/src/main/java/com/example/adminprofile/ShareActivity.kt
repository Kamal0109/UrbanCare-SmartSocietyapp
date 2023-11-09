package com.example.adminprofile

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class ShareActivity: AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance()
    private val storageRef = storage.reference

    private lateinit var titleEditText: EditText
    private lateinit var contentEditText: EditText
    private lateinit var selectImageButton: Button
    private lateinit var uploadButton: Button
    private lateinit var imageView: ImageView

    private var imageUri: Uri? = null

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            imageUri = it
            imageView.setImageURI(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)

        titleEditText = findViewById(R.id.titleEditText)
        contentEditText = findViewById(R.id.contentEditText)
        selectImageButton = findViewById(R.id.selectImageButton)
        uploadButton = findViewById(R.id.uploadButton)
        imageView = findViewById(R.id.imageView)

        selectImageButton.setOnClickListener {
            getContent.launch("image/*")
        }

        uploadButton.setOnClickListener {
            // Get the notice details from the EditText fields
            val title = titleEditText.text.toString()
            val content = contentEditText.text.toString()

            // Upload the image to Firebase Storage
            val imagesRef: StorageReference = storageRef.child("images/${imageUri?.lastPathSegment}")
            val uploadTask = imagesRef.putFile(imageUri!!)

            // Listen for the completion of the upload task
            uploadTask.addOnSuccessListener {
                // Get the download URL from the StorageReference
                imagesRef.downloadUrl.addOnSuccessListener { uri ->
                    // Upload the notice with the image URL to Firestore
                    val noticesCollection = db.collection("notices")
                    val newNotice = hashMapOf(
                        "title" to title,
                        "content" to content,
                        "imageUrl" to uri.toString()
                    )

                    noticesCollection.add(newNotice)
                        .addOnSuccessListener { documentReference ->
                            // Notice uploaded successfully
                            titleEditText.text.clear()
                            contentEditText.text.clear()
                            imageView.setImageURI(null)
                            imageUri = null
                        }
                        .addOnFailureListener { e ->
                            // Handle the error
                        }
                }
            }
        }

    }
}