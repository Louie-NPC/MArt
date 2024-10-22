package com.example.martapplication

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.martapplication.ProductPosting
import com.example.martapplication.R
import com.example.martapplication.fragment.HomeFragment
import com.example.martapplication.fragment.MessagesFragment
import com.example.martapplication.fragment.NotificationFragment
import com.example.martapplication.fragment.ProfileFragment
import com.github.dhaval2404.imagepicker.ImagePicker
import com.github.dhaval2404.imagepicker.constant.ImageProvider

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var categoryImageView: ImageView
    private lateinit var messagesImageView: ImageView
    private lateinit var notificationImageView: ImageView
    private lateinit var profileImageView: ImageView
    private lateinit var newPostImageView: ImageView

    // ActivityResultLauncher for image picker
    private lateinit var imagePickerLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        categoryImageView = findViewById(R.id.category)
        messagesImageView = findViewById(R.id.messages)
        notificationImageView = findViewById(R.id.notification)
        profileImageView = findViewById(R.id.profile)
        newPostImageView = findViewById(R.id.newpost) // Assuming this is the ID for new post image

        // Set click listeners for ImageView
        categoryImageView.setOnClickListener(this)
        messagesImageView.setOnClickListener(this)
        notificationImageView.setOnClickListener(this)
        profileImageView.setOnClickListener(this)
        newPostImageView.setOnClickListener(this)

        // Initialize image picker launcher
        imagePickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // Handle the image and pass it to ProductPosting activity
                val uri: Uri? = result.data?.data
                uri?.let {
                    val intent = Intent(this, ProductPosting::class.java).apply {
                        putExtra("imageUri", it.toString())
                    }
                    startActivityForResult(intent, PRODUCT_POST_REQUEST_CODE) // Use startActivityForResult to capture result from ProductPosting
                }
            }
        }

        // Load HomeFragment by default
        if (savedInstanceState == null) {
            loadFragment(HomeFragment(), "homeFragment")
        }
    }

    override fun onClick(v: View?) {
        val fragment = when (v?.id) {
            R.id.category -> HomeFragment()
            R.id.messages -> MessagesFragment()
            R.id.notification -> NotificationFragment()
            R.id.profile -> ProfileFragment()
            R.id.newpost -> {
                // Launch image picker for new post
                ImagePicker.with(this)
                    .provider(ImageProvider.BOTH)
                    .cropSquare() // Optional: For cropping the image
                    .createIntent { imagePickerLauncher.launch(it) }
                return
            }
            else -> null
        }

        val tag = when (v?.id) {
            R.id.category -> "homeFragment"
            R.id.messages -> "messagesFragment"
            R.id.notification -> "notificationFragment"
            R.id.profile -> "profileFragment"
            else -> null
        }

        if (fragment != null && tag != null) {
            loadFragment(fragment, tag)
        }
    }

    private fun loadFragment(fragment: androidx.fragment.app.Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_fragment, fragment, tag)
            .commit()
    }

    @Deprecated("This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      with the appropriate {@link ActivityResultContract} and handling the result in the\n      {@link ActivityResultCallback#onActivityResult(Object) callback}.")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PRODUCT_POST_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // Get the image URI passed back from ProductPosting
            val imageUri = data?.getStringExtra("imageUri")
            postImageToHomeFragment(imageUri)
        }
    }

    // Function to pass the image URI to HomeFragment
    private fun postImageToHomeFragment(imageUri: String?) {
        // Create a new HomeFragment instance and pass the image URI using a bundle
        val homeFragment = HomeFragment().apply {
            arguments = Bundle().apply {
                putString("imageUri", imageUri)
            }
        }

        // Replace the fragment container in the activity layout
        supportFragmentManager.commit {
            replace(R.id.nav_fragment, homeFragment, "homeFragment") // Replace with correct container ID
            addToBackStack(null)
        }
    }

    companion object {
        const val PRODUCT_POST_REQUEST_CODE = 1001 // Arbitrary request code
    }
}