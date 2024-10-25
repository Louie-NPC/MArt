package com.example.martapplication

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProductPosting : AppCompatActivity() {

    private lateinit var imageUri: Uri
    private lateinit var imgPreview: ImageView
    private lateinit var txtPost: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_posting)

        imgPreview = findViewById(R.id.productFromGallery)
        txtPost = findViewById(R.id.txtPost)

        // Get the image URI passed from MainActivity
        imageUri = Uri.parse(intent.getStringExtra("imageUri"))
        imgPreview.setImageURI(imageUri)

        // Set the click listener for the post button
        txtPost.setOnClickListener {
            // Return the image URI to HomeFragment
            val resultIntent = Intent().apply {
                putExtra("imageUri", imageUri.toString())
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

    }
}
