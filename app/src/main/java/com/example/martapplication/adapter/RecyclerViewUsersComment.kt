package com.example.martapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.martapplication.databinding.RecyclerViewUsersCommentsBinding

class RecyclerViewUsersCommentAdapter(
    private val userName: String,
    private val comment: String,
    private val time: String
) : RecyclerView.Adapter<RecyclerViewUsersCommentAdapter.UserCommentViewHolder>() {

    inner class UserCommentViewHolder(private val binding: RecyclerViewUsersCommentsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(userName: String, comment: String, time: String) {
            binding.textView40.text = comment
            binding.textView41.text = comment
            binding.editTextTime.setText(time) // Assuming editTextTime is used to display time
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserCommentViewHolder {
        val binding = RecyclerViewUsersCommentsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return UserCommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserCommentViewHolder, position: Int) {
        holder.bind(userName, comment, time)
    }

    override fun getItemCount() = 1 // Adjust as needed; currently set to one comment
}
