package com.example.martapplication.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.martapplication.ProductOverviewActivity
import com.example.martapplication.databinding.ProductItemBinding

class ProductAdapter(
    private val images: MutableList<Any>
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val image = images[position]
        holder.bind(image)
    }

    override fun getItemCount(): Int = images.size

    class ProductViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(image: Any) {
            // Handle both Uri and String types
            when (image) {
                is Uri -> {
                    Glide.with(binding.imageViewProduct.context)
                        .load(image)
                        .into(binding.imageViewProduct)
                }
                is String -> {
                    Glide.with(binding.imageViewProduct.context)
                        .load(image)
                        .into(binding.imageViewProduct)
                }
            }

            // Set click listener to open ProductOverviewActivity
            binding.root.setOnClickListener {
                val context = binding.root.context
                val intent = Intent(context, ProductOverviewActivity::class.java)
                intent.putExtra("imageUri", image.toString())  // Pass URL or Uri as string
                context.startActivity(intent)
            }
        }
    }
}
