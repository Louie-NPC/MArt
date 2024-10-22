package com.example.martapplication

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.commit
import com.example.martapplication.fragment.HomeFragment
import com.google.android.material.textfield.TextInputEditText
import java.io.File
import java.io.IOException

class ProductPosting : AppCompatActivity() {

    private lateinit var productImageView: ImageView
    private lateinit var txtPost: TextView
    private lateinit var bioEditText: TextInputEditText
    private lateinit var txtAbstract: TextInputEditText
    private lateinit var txtForsale: TextInputEditText
    private lateinit var txtPortrait: TextInputEditText

    private var imageUri: Uri? = null
    private lateinit var photoFile: File

    private val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_posting)

        // Initialize views
        productImageView = findViewById(R.id.productFromGallery)
        txtPost = findViewById(R.id.txtPost)
        bioEditText = findViewById(R.id.bioEditTextInput)
        txtAbstract = findViewById(R.id.txtAbstractInput)
        txtForsale = findViewById(R.id.txtForsaleInput)
        txtPortrait = findViewById(R.id.txtPortraitInput)

        // Launch camera when clicking on ImageView
        productImageView.setOnClickListener {
            takePicture()
        }

        // Set click listener for txtPost to navigate to HomeFragment
        txtPost.setOnClickListener {
            postDataToHomeFragment(imageUri)
        }
    }

    private fun takePicture() {
        // Create an Intent to capture a photo
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            // Create the File where the photo should go
            try {
                photoFile = createImageFile()
                // Continue only if the File was successfully created
                photoFile.also {
                    imageUri = FileProvider.getUriForFile(
                        this,
                        "com.example.martapplication.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            } catch (ex: IOException) {
                // Error occurred while creating the File
                ex.printStackTrace()
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val imageFileName = "JPEG_${System.currentTimeMillis()}"
        val storageDir = getExternalFilesDir(null)
        return File.createTempFile(
            imageFileName, /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        )
    }

    // Handle the result from the camera activity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // Show the captured image in ImageView
            imageUri?.let {
                productImageView.setImageURI(it)
            }
        }
    }

    private fun postDataToHomeFragment(imageUri: Uri?) {
        val bioText = bioEditText.text.toString()
        val abstractText = txtAbstract.text.toString()
        val forsaleText = txtForsale.text.toString()
        val portraitText = txtPortrait.text.toString()

        val homeFragment = HomeFragment().apply {
            arguments = Bundle().apply {
                putString("imageUri", imageUri?.toString()) // Convert URI to String
                putString("bioText", bioText)
                putString("abstractText", abstractText)
                putString("forsaleText", forsaleText)
                putString("portraitText", portraitText)
            }
        }

        supportFragmentManager.commit {
            replace(R.id.nav_fragment, homeFragment)
            addToBackStack(null)
        }
    }
}