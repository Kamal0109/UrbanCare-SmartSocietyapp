package com.example.adminprofile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.adminprofile.databinding.FragmentUserRequestBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [UserRequestFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserRequestFragment : Fragment() {
    private lateinit var binding: FragmentUserRequestBinding
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserRequestBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.searchButton.setOnClickListener {
            val searchPhone: String = binding.searchPhone.text.toString()
            if (searchPhone.isNotEmpty()) {
                readData(searchPhone)
            } else {
                // Handle the case when the phone number is empty
                // For example, show a message to the user
            }
        }

        return view
    }

    private fun readData(phone: String) {
        database = FirebaseDatabase.getInstance().reference.child("Users")
        database.child(phone).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val name = dataSnapshot.child("name").value
                    val flatNo = dataSnapshot.child("flatNo").value
                    val societyName = dataSnapshot.child("societyName").value

                    // Handle the found data (e.g., update UI elements)
                    binding.readName.text = name.toString()
                    binding.readFlatNo.text = flatNo.toString()
                    binding.readSocietyName.text = societyName.toString()

                    // Show a message to indicate success
                    // For example:
                    // Toast.makeText(requireContext(), "Results Found", Toast.LENGTH_SHORT).show()
                } else {
                    // Handle the case when the phone number does not exist
                    // For example, show a message to the user
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle the failure scenario
                // For example, show a message to the user
            }
        })
    }

}