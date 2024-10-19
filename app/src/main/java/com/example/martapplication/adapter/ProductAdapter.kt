package com.example.martapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.martapplication.databinding.ProductItemBinding

class ProductAdapter(
    private val images: List<Int>
) : RecyclerView.Adapter<ProductAdapter.PopularViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val image = images[position]
        holder.bind(image)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    class PopularViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Int) {
            binding.imageView7.setImageResource(image) // Assuming imageView7 exists in the XML
            // Optionally, set a default name or description
            binding.imageView9.setImageResource(image)
        }
    }
}
