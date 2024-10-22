package com.example.martapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.martapplication.databinding.ActivityProductOverviewBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import androidx.appcompat.widget.AppCompatImageButton
import com.bumptech.glide.Glide

class ProductOverviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductOverviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductOverviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle back arrow click to navigate back to MainActivity
        binding.backArrow.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish() // Optionally close the current activity
        }

        // Show overview bottom sheet dialog when clicking the OverviewButton
        binding.buttonOverVIew.setOnClickListener {
            showOverviewBottomSheet()
        }

        // Load product image from intent
        loadProductImage()
    }

    private fun showOverviewBottomSheet() {
        // First BottomSheetDialog (Overview)
        val bottomSheetDialog = BottomSheetDialog(this)
        val bottomSheetView = layoutInflater.inflate(R.layout.overview_template, null)
        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.show()

        // Find the share and comment buttons inside the dialog
        val shareButton = bottomSheetView.findViewById<AppCompatImageButton>(R.id.share_button)
        val commentButton = bottomSheetView.findViewById<AppCompatImageButton>(R.id.comment_button)

        // Handle share button click to open a second BottomSheetDialog (Share Page)
        shareButton.setOnClickListener {
            val shareDialog = BottomSheetDialog(this)
            val shareDialogView = layoutInflater.inflate(R.layout.share_page_template, null)
            shareDialog.setContentView(shareDialogView)
            shareDialog.show()
        }

        // Handle comment button click to open CommentPageActivity
        commentButton.setOnClickListener {
            val intent = Intent(this, CommentPageActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadProductImage() {
        // Retrieve the image from intent
        val imageRes = intent.getIntExtra("image_res", -1)
        val imageUri = intent.getStringExtra("imageUri")

        // Load the image into ImageView
        if (imageRes != -1) {
            binding.imageView7.setImageResource(imageRes)
        } else if (imageUri != null) {
            Glide.with(this).load(imageUri).into(binding.imageView7)
        }
    }
}
