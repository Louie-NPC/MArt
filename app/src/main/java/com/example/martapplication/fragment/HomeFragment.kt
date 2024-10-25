package com.example.martapplication.fragment
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.martapplication.ProductPosting
import com.example.martapplication.R
import com.example.martapplication.adapter.ProductAdapter

class HomeFragment : Fragment() {

    private lateinit var recyclerViewProduct: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private var productImages: MutableList<Any> = mutableListOf()  // Holds image URIs

    private val productPostingLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageUriString = result.data?.getStringExtra("imageUri")
            if (imageUriString != null) {
                val imageUri = Uri.parse(imageUriString)
                addProduct(imageUri)  // Add the new image URI to the list
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerViewProduct = view.findViewById(R.id.recyclerViewProduct)

        // Initialize the adapter with the current list of images
        productAdapter = ProductAdapter(productImages)
        recyclerViewProduct.adapter = productAdapter

        return view
    }

    // Method to add a new product to the list
    fun addProduct(imageUri: Uri) {
        productImages.add(imageUri)  // Add the new image URI to the list
        productAdapter.notifyItemInserted(productImages.size - 1)  // Notify the adapter of the new item
    }

    // Method to launch ProductPosting Activity
    fun launchProductPosting() {
        val intent = Intent(requireContext(), ProductPosting::class.java)
        productPostingLauncher.launch(intent)
    }
}
