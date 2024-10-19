package com.example.pm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pm.data.comment_profile
import com.example.pm.R
import android.view.View
import android.widget.TextView
import android.widget.ImageView



class comment_adapter(
    private val comment: List<comment_profile>
) : RecyclerView.Adapter<comment_adapter.commentContentHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): commentContentHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.comment_placeholder, parent, false)
        return commentContentHolder(view)
    }

    override fun onBindViewHolder(holder: commentContentHolder, position: Int) {
        val currentItem = comment[position]
        holder.commenterTextView.text = currentItem.username
        holder.commenterTimeTextView.text = currentItem.time
        holder.commenterContentTextView.text = currentItem.comment_content
        holder.profPicImageView.setImageResource(currentItem.user_prof_pic)
    }


    class commentContentHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profPicImageView: ImageView = itemView.findViewById(R.id.comment_prof)
        val commenterTextView: TextView = itemView.findViewById(R.id.commenter_name)
        val commenterTimeTextView: TextView = itemView.findViewById(R.id.commenter_time)
        val commenterContentTextView: TextView = itemView.findViewById(R.id.comment_content)
    }

    override fun getItemCount(): Int {
        return comment.size
    }
}