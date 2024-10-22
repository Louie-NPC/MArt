package com.example.martapplication.adapter

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.martapplication.ProductOverviewActivity
import com.example.martapplication.databinding.ProductItemBinding

class ProductAdapter(
    private val images: List<Any>
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
            when (image) {
                is Int -> binding.imageViewProduct.setImageResource(image) // Resource ID
                is Uri -> { // Handle URI
                    Glide.with(binding.imageViewProduct.context)
                        .load(image)
                        .into(binding.imageViewProduct)
                    Log.d("ProductAdapter", "Loaded URI: $image")
                }
                else -> Log.e("ProductAdapter", "Unknown image type: $image")
            }

            binding.root.setOnClickListener {
                val context = binding.root.context
                val intent = Intent(context, ProductOverviewActivity::class.java)
                when (image) {
                    is Int -> intent.putExtra("image_res", image)
                    is Uri -> intent.putExtra("imageUri", image.toString())
                }
                context.startActivity(intent)
            }
        }
    }
}
