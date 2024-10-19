package com.example.martapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.martapplication.databinding.UserCommentOnProductBinding

class UserCommentOnProductAdapter(private val comments: List<UserComment>) :
    RecyclerView.Adapter<UserCommentOnProductAdapter.UserCommentViewHolder>() {

    data class UserComment(
        val userName: String,
        val comment: String
    )

    inner class UserCommentViewHolder(private val binding: UserCommentOnProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(comment: UserComment) {
            binding.textView38.text = comment.userName
            binding.textView39.text = comment.comment
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserCommentViewHolder {
        val binding = UserCommentOnProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserCommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserCommentViewHolder, position: Int) {
        holder.bind(comments[position])
    }

    override fun getItemCount() = comments.size
}
