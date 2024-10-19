package com.example.martapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.martapplication.R
import com.example.martapplication.adapter.ProductAdapter
import com.example.martapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout using View Binding
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Sample data for RecyclerView
        val productImages = listOf(
            R.drawable.rectangle_18851,
            R.drawable.rectangle8850,
            R.drawable.rectangle18855,
            R.drawable.rectangle18856
        ) // Ensure equal size

        // Setting up the RecyclerView with adapter
        val adapter = ProductAdapter(productImages)
        binding.recyclerViewProduct.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewProduct.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
