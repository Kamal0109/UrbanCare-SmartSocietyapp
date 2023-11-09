package com.example.client

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class NoticeAdapter(private val notices: List<Notice>) : RecyclerView.Adapter<NoticeAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.notice_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notice = notices[position]

        holder.titleTextView.text = notice.title
        holder.contentTextView.text = notice.content

        // Load the image using Picasso or Glide
        if (notice.imageUrl.isNotEmpty()) {
            Picasso.get().load(notice.imageUrl).into(holder.imageView)
        }
    }



    override fun getItemCount(): Int {
        return notices.size
    }
}