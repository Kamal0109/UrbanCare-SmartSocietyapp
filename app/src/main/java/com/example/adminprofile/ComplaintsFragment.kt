package com.example.adminprofile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ComplaintsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ComplaintsFragment : Fragment() {

    private lateinit var firebaseStorage: FirebaseStorage


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_complaints, container, false)

        val complaintsList = listOf(
            UserComplaint(1, "Issue 1", "Water Leakage Problem"),
            UserComplaint(2, "Issue 2", "Electricity Cut Problem"),
            UserComplaint(3, "Issue 3", "Disturbance from neighbours"),
            // Add more complaints as needed
        )
        val recyclerView: RecyclerView = rootView.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = ComplaintAdapter(complaintsList)

        return rootView

    }


}