package com.example.martapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.martapplication.databinding.RowUserprofileBinding

data class UserMessage(
    val userName: String,
    val message: String,
    val time: String,
    val avatarResId: Int // Resource ID for user's avatar
)

class UserMessageAdapter(
    private val messages: List<UserMessage>
) : RecyclerView.Adapter<UserMessageAdapter.MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val binding = RowUserprofileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]
        holder.bind(message)
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    class MessageViewHolder(private val binding: RowUserprofileBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(userMessage: UserMessage) {
            binding.textViewUsername.text = userMessage.userName
            binding.textViewMessage.text = userMessage.message
            binding.textViewTime.text = userMessage.time
            binding.imageViewUserProfile.setImageResource(userMessage.avatarResId) // Assuming the avatars are drawable resources
        }
    }
}
