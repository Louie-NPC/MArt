package com.example.martapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.martapplication.fragment.HomeFragment
import com.example.martapplication.fragment.NotificationFragment
import com.example.martapplication.fragment.ProfileFragment
import com.example.martapplication.fragment.ChatFragment
import com.example.martapplication.fragment.ChatFromHomeFragment
import android.widget.ImageView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    // ImageView variables
    private lateinit var categoryImageView: ImageView
    private lateinit var messagesImageView: ImageView
    private lateinit var notificationImageView: ImageView
    private lateinit var profileImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Hide the action bar title
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Initialize ImageView click listeners for fragment navigation
        categoryImageView = findViewById(R.id.category)
        messagesImageView = findViewById(R.id.messages)
        notificationImageView = findViewById(R.id.notification)
        profileImageView = findViewById(R.id.profile)

        // Set click listeners for each ImageView
        categoryImageView.setOnClickListener(this)
        messagesImageView.setOnClickListener(this)
        notificationImageView.setOnClickListener(this)
        profileImageView.setOnClickListener(this)

        // Load the HomeFragment by default when the activity starts
        if (savedInstanceState == null) {
            loadFragment(HomeFragment(), "homeFragment")
        }
    }

    // Handle image click events
    override fun onClick(v: View?) {
        val fragment = when (v?.id) {
            R.id.category -> HomeFragment()
            R.id.messages -> ChatFromHomeFragment()
            R.id.notification -> NotificationFragment()
            R.id.profile -> ProfileFragment()
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


    // Function to load a fragment and replace the current one
    private fun loadFragment(fragment: androidx.fragment.app.Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_fragment, fragment, tag)
            .commit()
    }
}
