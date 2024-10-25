package com.example.martapplication.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.martapplication.R
import com.example.martapplication.SettingsActivity
import com.example.martapplication.adapter.ActivePostProductsAdapter
import com.example.martapplication.data.imageforadapter.HelperClass
import com.example.martapplication.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var adapter: ActivePostProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        database = FirebaseDatabase.getInstance().getReference("Users")

        if (currentUser != null) {
            val userId = currentUser.uid
            database.child(userId).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val user = snapshot.getValue(HelperClass::class.java)
                        user?.let { userData ->
                            binding.userName.text = "${userData.firstName} ${userData.lastName}"
                            // Access other user data from userData as needed
                        }
                    } else {
                        // Handle case where user data doesn't exist
                        Toast.makeText(requireContext(), "User data not found", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(requireContext(), "Failed to load user data", Toast.LENGTH_SHORT).show()
                }
            })
        }


        // Set up RecyclerView with images
        val postImages = listOf(
            R.drawable.rectangle18855,
            R.drawable.rectangle18856,
            R.drawable.rectangle8850,
            R.drawable.rectangle_18851,
            R.drawable.rectangle8850
        )

        adapter = ActivePostProductsAdapter(postImages)
        binding.recyclerFinalProfile.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerFinalProfile.adapter = adapter

        // Set click listener for settings button
        binding.settings.setOnClickListener {
            val intent = Intent(requireContext(), SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}