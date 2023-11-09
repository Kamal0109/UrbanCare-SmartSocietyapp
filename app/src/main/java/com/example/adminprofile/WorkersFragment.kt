package com.example.adminprofile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [WorkersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WorkersFragment : Fragment() {
    // TODO: Rename and change types of parameters


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_workers, container, false)

        // Sample list of workers
        val workersList = listOf(
            Worker(1, "Mayank", "Plumber", 5000.0),
            Worker(2, "Sarthak", "Electrician", 6000.0),
            Worker(3, "Hina", "HouseHelper", 3000.0),
            Worker(3, "Mukesh", "Driver", 2000.0),

            // Add more workers as needed
        )

        val recyclerView: RecyclerView = rootView.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = WorkerAdapter(workersList)

        return rootView
    }


}