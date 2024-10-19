package com.example.martapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.martapplication.adapter.RecyclerViewUsersCommentAdapter
import com.example.martapplication.adapter.UserCommentOnProductAdapter
import com.example.martapplication.databinding.FragmentNotificationBinding

class NotificationFragment : Fragment() {

    private var _binding: FragmentNotificationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotificationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Sample data for the first RecyclerView (user comments without time)
        val userComments = listOf(
            UserCommentOnProductAdapter.UserComment(
                "Gabriel",
                "This is great art!"
            ),
            UserCommentOnProductAdapter.UserComment(
                "John",
                "Looks perfect, send it for review tomorrow."
            )
        )

        // Setup the first RecyclerView
        binding.recyclerView1.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView1.adapter = UserCommentOnProductAdapter(userComments)

        // Sample data for the second RecyclerView (userName, comment, and time)
        val userName = "Steve"
        val comment = "Steve added a comment"
        val time = "10:15 AM"

        // Setup the second RecyclerView
        binding.recyclerViewUsersComments.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewUsersComments.adapter = RecyclerViewUsersCommentAdapter(userName, comment, time)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
