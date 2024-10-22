package com.example.martapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.martapplication.R

class ActivePostProductsAdapter(
    private val images: List<Int>
) : RecyclerView.Adapter<ActivePostProductsAdapter.ActivePostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivePostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.active_post_products, parent, false)
        return ActivePostViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActivePostViewHolder, position: Int) {
        val image = images[position]
        holder.bind(image)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    class ActivePostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageRectangle)

        fun bind(image: Int) {
            // Use Glide to load the image more efficiently
            Glide.with(itemView.context)
                .load(image)
                .placeholder(R.drawable.rectangle8850) // Optional: a placeholder image
                .into(imageView)
        }
    }
}
