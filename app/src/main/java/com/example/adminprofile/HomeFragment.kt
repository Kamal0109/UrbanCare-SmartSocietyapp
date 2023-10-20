package com.example.adminprofile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import java.net.URI

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private val PICK_IMAGE_REQUEST = 1
    private lateinit var uploadPhotoImageView: ImageView
    private lateinit var selectedImageImageView: ImageView
    lateinit var imageUri : URI
    private val storage = FirebaseStorage.getInstance()
    private val storageReference = storage.reference



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home,container,false)

        uploadPhotoImageView = view.findViewById(R.id.uploadPhoto)
        selectedImageImageView = view.findViewById(R.id.selectedImage)

        uploadPhotoImageView.setOnClickListener {

            Toast.makeText(context,"Photo is clicked",Toast.LENGTH_SHORT).show()
            openImagePicker()


        }

        return view
    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            val selectedImage = data?.data
            if (selectedImage != null) {
                selectedImageImageView.setImageURI(selectedImage)
                selectedImageImageView.visibility = View.VISIBLE
                uploadImage(selectedImage)
                retrieveImages()
            }
        }
    }

    private fun uploadImage(imageUri: Uri) {
        val imagesRef = storageReference.child("images/image1.jpg")

        val uploadTask = imagesRef.putFile(imageUri)

        uploadTask.addOnSuccessListener { taskSnapshot ->
            // Image upload successful
            // You can get the image URL from taskSnapshot and save it to Firebase Realtime Database if needed.
            val downloadUrl = taskSnapshot.storage.downloadUrl.toString()
            // Handle success, e.g., display a success message or process the downloadUrl.
            Toast.makeText(context, "Image uploaded successfully", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener { exception ->
            // Handle failed upload
            Log.e("Upload", "Image upload failed: ${exception.message}")
            Toast.makeText(context, "Image upload failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun retrieveImages() {
        // Reference the folder where your images are stored
        val folderReference = storageReference.child("images")

        // List all items (images) in the folder
        folderReference.listAll()
            .addOnSuccessListener { result ->
                for (item in result.items) {
                    // Get the download URL for each image
                    item.downloadUrl.addOnSuccessListener { uri ->
                        // Here, you can load and display the image using a library like Glide or Picasso
                        // For this example, I'll set the image URI to an ImageView
                        val imageUri = uri
                        selectedImageImageView.setImageURI(imageUri)
                    }
                }
            }
            .addOnFailureListener { exception ->
                // Handle any errors
            }

        Glide.with(this)
            .load(imageUri) // Replace 'imageUri' with the actual URI of your image
            .into(selectedImageImageView)
    }

}