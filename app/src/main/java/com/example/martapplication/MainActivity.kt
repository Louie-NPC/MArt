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
import com.example.martapplication.fragment.HomeFragment
import com.example.martapplication.fragment.MessagesFragment
import com.example.martapplication.fragment.NotificationFragment
import com.example.martapplication.fragment.ProfileFragment
import com.github.dhaval2404.imagepicker.ImagePicker
import com.github.dhaval2404.imagepicker.constant.ImageProvider

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var categoryImageView: ImageView
    private lateinit var messagesImageView: ImageView
    private lateinit var notificationImageView: ImageView
    private lateinit var profileImageView: ImageView
    private lateinit var newPostImageView: ImageView

    private lateinit var imagePickerLauncher: ActivityResultLauncher<Intent>
    private lateinit var homeFragment: HomeFragment  // Store a reference to HomeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        categoryImageView = findViewById(R.id.category)
        messagesImageView = findViewById(R.id.messages)
        notificationImageView = findViewById(R.id.notification)
        profileImageView = findViewById(R.id.profile)
        newPostImageView = findViewById(R.id.newpost)

        // Set click listeners for ImageView
        categoryImageView.setOnClickListener(this)
        messagesImageView.setOnClickListener(this)
        notificationImageView.setOnClickListener(this)
        profileImageView.setOnClickListener(this)
        newPostImageView.setOnClickListener(this)

        // Initialize image picker launcher
        imagePickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val uri: Uri? = result.data?.data
                uri?.let {
                    val intent = Intent(this, ProductPosting::class.java).apply {
                        putExtra("imageUri", it.toString())
                    }
                    startActivityForResult(intent, PRODUCT_POST_REQUEST_CODE)
                }
            }
        }

        // Initialize or find the existing HomeFragment
        if (savedInstanceState == null) {
            homeFragment = HomeFragment()
            loadFragment(homeFragment, "homeFragment")
        } else {
            homeFragment = supportFragmentManager.findFragmentByTag("homeFragment") as HomeFragment
        }
    }

    override fun onClick(v: View?) {
        val fragment = when (v?.id) {
            R.id.category -> homeFragment
            R.id.messages -> MessagesFragment()
            R.id.notification -> NotificationFragment()
            R.id.profile -> ProfileFragment()
            R.id.newpost -> {
                // Launch image picker for new post
                ImagePicker.with(this)
                    .provider(ImageProvider.BOTH)
                    .cropSquare()  // Optional cropping
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
        supportFragmentManager.commit {
            replace(R.id.nav_fragment, fragment, tag)
            addToBackStack(null)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PRODUCT_POST_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val imageUri = data?.getStringExtra("imageUri")?.let { Uri.parse(it) }
            imageUri?.let { uri ->
                homeFragment.addProduct(uri)  // Pass image URI to HomeFragment
            }
        }
    }

    companion object {
        const val PRODUCT_POST_REQUEST_CODE = 1001  // Arbitrary request code
    }
}
