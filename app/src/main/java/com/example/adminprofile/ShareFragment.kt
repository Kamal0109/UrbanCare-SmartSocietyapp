package com.example.adminprofile

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [ShareFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShareFragment : Fragment() {
    // TODO: Rename and change types of parameters


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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_share, container, false)

        titleEditText = rootView.findViewById(R.id.titleEditText)
        contentEditText = rootView.findViewById(R.id.contentEditText)
        selectImageButton = rootView.findViewById(R.id.selectImageButton)
        uploadButton = rootView.findViewById(R.id.uploadButton)
        imageView = rootView.findViewById(R.id.imageView)

        selectImageButton.setOnClickListener {
            getContent.launch("image/*")
        }

        uploadButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val content = contentEditText.text.toString()

            if (title.isNotBlank() && content.isNotBlank() && imageUri != null) {
                // Upload the image to Firebase Storage
                val imagesRef: StorageReference = storageRef.child("images/${imageUri!!.lastPathSegment}")
                val uploadTask = imagesRef.putFile(imageUri!!)

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

                                val toast = Toast.makeText(requireContext(), "Uploaded", Toast.LENGTH_SHORT)
                                toast.show()
                            }
                            .addOnFailureListener { e ->
                                // Handle the error
                            }
                    }
                }
            } else {
                // Handle the case where fields are not filled or image is not selected
                val toast = Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT)
                toast.show()
            }
        }

        return rootView
    }


}