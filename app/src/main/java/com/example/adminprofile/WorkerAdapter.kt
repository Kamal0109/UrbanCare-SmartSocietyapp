package com.example.adminprofile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WorkerAdapter(private val workers: List<Worker>) : RecyclerView.Adapter<WorkerAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val serviceTypeTextView: TextView = itemView.findViewById(R.id.serviceTypeTextView)
        val chargesTextView: TextView = itemView.findViewById(R.id.chargesTextView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.worker_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return workers.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val worker = workers[position]

        holder.nameTextView.text = worker.name
        holder.serviceTypeTextView.text = worker.serviceType
        holder.chargesTextView.text = "Charges: $${worker.charges}"

    }
}