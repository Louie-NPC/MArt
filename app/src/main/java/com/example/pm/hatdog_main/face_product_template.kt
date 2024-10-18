package com.example.pm.hatdog_main

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pm.R
import com.google.android.material.bottomsheet.BottomSheetDialog


class face_product_template : AppCompatActivity() {

    private lateinit var OverviewButton: AppCompatImageButton
    private lateinit var BackArrowProduct: AppCompatImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.face_product_template)

        OverviewButton = findViewById<AppCompatImageButton>(R.id.overview_button)
        BackArrowProduct = findViewById<AppCompatImageButton>(R.id.back_arrow_product)

        OverviewButton.setOnClickListener {
            // First BottomSheetDialog (Overview)
            val bottomSheetDialog = BottomSheetDialog(this)
            val bottomSheetView = layoutInflater.inflate(R.layout.overview_template, null)

            bottomSheetDialog.setContentView(bottomSheetView)
            bottomSheetDialog.show() // Display the BottomSheetDialog

            // Find the share and comment buttons inside the dialog
            val shareButtonDialogButton = bottomSheetView.findViewById<AppCompatImageButton>(R.id.share_button)
            val commentButtonDialogButton = bottomSheetView.findViewById<AppCompatImageButton>(R.id.comment_button)

            // When the share button is clicked, open a second BottomSheetDialog (Share Page)
            shareButtonDialogButton.setOnClickListener {
                val shareButtonDialog = BottomSheetDialog(this)
                val shareDialogView = layoutInflater.inflate(R.layout.share_page_template, null)

                // Set the new layout for the second dialog
                shareButtonDialog.setContentView(shareDialogView)
                shareButtonDialog.show() // Display the second BottomSheetDialog
            }

                commentButtonDialogButton?.setOnClickListener {
                    val intent = Intent(this, CommentPageActivity::class.java)
                    startActivity(intent)
                }


        BackArrowProduct.setOnClickListener{ //back to homepage
            val intent = Intent(this, hatdog_home_page::class.java)
            startActivity(intent)

        }





    }
}}