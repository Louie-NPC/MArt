package com.example.martapplication.fragment

import com.example.martapplication.adapter.ActivePostProductsAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.martapplication.R
import com.example.martapplication.databinding.FragmentProfileBinding
import android.content.Intent
import com.example.martapplication.SettingsActivity

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val postImages = listOf(
            R.drawable.rectangle18855,
            R.drawable.rectangle18856,
            R.drawable.rectangle8850,
            R.drawable.rectangle_18851,
            R.drawable.rectangle8850
        )

        val adapter = ActivePostProductsAdapter(postImages)
        binding.recyclerFinalProfile.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerFinalProfile.adapter = adapter

        // Set onClickListener for the logout row using findViewById
        val settingsButton = view.findViewById<View>(R.id.settings)
        settingsButton?.setOnClickListener {
            val intent = Intent(requireContext(), SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
