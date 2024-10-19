package com.example.finaleditprofile.profile_main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import com.example.finaleditprofile.R

class EditProfileActivity: AppCompatActivity() {
    private lateinit var SaveChangesButton: AppCompatImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.anader_edit_profile) // Use the layout file for editing profile

        SaveChangesButton = findViewById<AppCompatImageButton>(R.id.btnSaveChanges)

        SaveChangesButton.setOnClickListener {
            val intent = Intent(this, ProfilePage::class.java)
            startActivity(intent)
        }
    }
}