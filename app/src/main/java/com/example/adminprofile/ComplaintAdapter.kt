package com.example.adminprofile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ComplaintAdapter(private val complaints: List<UserComplaint>) : RecyclerView.Adapter<ComplaintAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.complaint_item, parent, false)
        return ViewHolder(itemView)

    }

    override fun getItemCount(): Int {

        return complaints.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val complaint = complaints[position]

        holder.titleTextView.text = complaint.title
        holder.descriptionTextView.text = complaint.description

    }
}