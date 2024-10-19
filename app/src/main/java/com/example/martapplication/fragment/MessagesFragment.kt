package com.example.martapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.martapplication.R
import com.example.martapplication.adapter.UserMessage
import com.example.martapplication.adapter.UserMessageAdapter
import com.example.martapplication.databinding.FragmentMessagesBinding

class MessagesFragment : Fragment() {

    private var _binding: FragmentMessagesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMessagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Sample data for RecyclerView
        val userMessages = listOf(
            UserMessage("John Doe", "Yes, you can.", "12:45", R.drawable.img_ellipse_2),
            UserMessage("Jane Smith", "Let's meet at 5.", "11:20", R.drawable.img_ellipse_2),
            UserMessage("Mike Ross", "Got it!", "09:55", R.drawable.img_ellipse_2),
            UserMessage("Rachel Zane", "See you soon!", "14:35", R.drawable.img_ellipse_2)
        )

        // Setting up the RecyclerView with adapter
        val adapter = UserMessageAdapter(userMessages)
        binding.RecylerViewUserMessage.layoutManager = LinearLayoutManager(requireContext())
        binding.RecylerViewUserMessage.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
