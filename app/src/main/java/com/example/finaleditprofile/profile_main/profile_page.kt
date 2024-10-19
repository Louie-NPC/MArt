package com.example.finaleditprofile.profile_main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.example.finaleditprofile.R

class ProfilePage : AppCompatActivity(){
    private lateinit var EditProfileButton: AppCompatImageButton
    private lateinit var ActivePostButton: AppCompatImageButton
    private lateinit var InactivePostButton: AppCompatImageButton
    private lateinit var SettingsButton: AppCompatImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_page)

        EditProfileButton = findViewById<AppCompatImageButton>(R.id.btnEditProfile)
        ActivePostButton = findViewById<AppCompatImageButton>(R.id.btnActivepostsfive)
        InactivePostButton = findViewById<AppCompatImageButton>(R.id.btnInactiveposts)
        SettingsButton = findViewById<AppCompatImageButton>(R.id.btnSettings)

        EditProfileButton.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }
        ActivePostButton.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }
        InactivePostButton.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }
        SettingsButton.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }

    }
}
