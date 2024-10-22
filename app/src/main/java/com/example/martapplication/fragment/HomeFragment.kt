package com.example.martapplication.fragment

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.martapplication.R
import com.example.martapplication.adapter.ProductAdapter
import com.example.martapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // Any type to support both Int (resource ID) and String (URI)
    private val productImages = mutableListOf<Any>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize with drawable resources if not restoring state
        if (savedInstanceState == null) {
            productImages.addAll(
                listOf(
                    R.drawable.rectangle_18851,
                    R.drawable.rectangle8850,
                    R.drawable.rectangle18855,
                    R.drawable.rectangle18856
                )
            )
        } else {
            // Restore only URIs, as we are not saving drawable resource IDs
            savedInstanceState.getStringArrayList("productImages")?.let {
                productImages.addAll(it.map { uriString -> Uri.parse(uriString) })
            }
        }

        // Setup RecyclerView with adapter
        val adapter = ProductAdapter(productImages)
        binding.recyclerViewProduct.layoutManager = GridLayoutManager(requireContext(), 2) // Grid Layout
        binding.recyclerViewProduct.adapter = adapter

        // Check if there's a new image URI from ProductPosting and add it to the RecyclerView
        arguments?.getString("imageUri")?.let { uriString ->
            if (!uriString.isNullOrEmpty()) {
                val uri = Uri.parse(uriString) // Convert back to URI
                productImages.add(uri) // Add URI to the list
                adapter.notifyItemInserted(productImages.size - 1)
                Log.d("HomeFragment", "New image URI added: $uri")
            } else {
                Log.e("HomeFragment", "imageUri from arguments is null or empty")
            }
        }
    }

    // Save productImages list on configuration change
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val uriList = ArrayList<String>()
        productImages.forEach {
            if (it is Uri) uriList.add(it.toString()) // Save only URIs
        }
        outState.putStringArrayList("productImages", uriList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
