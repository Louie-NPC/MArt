package com.example.finaleditprofile.profile_main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import com.example.finaleditprofile.R

class SettingsPage: AppCompatActivity() {
    private lateinit var SecurityButton: AppCompatImageButton
    private lateinit var NotificationButton: AppCompatImageButton
    private lateinit var PrivacyButton: AppCompatImageButton
    private lateinit var MySubscriptionButton: AppCompatImageButton
    private lateinit var HelpANDSupportButton: AppCompatImageButton
    private lateinit var TermsANDPoliciesButton: AppCompatImageButton
    private lateinit var FreeUpSpaceButton: AppCompatImageButton
    private lateinit var DataSaverButton: AppCompatImageButton
    private lateinit var ReportAProblemButton: AppCompatImageButton
    private lateinit var LogOutButton: AppCompatImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_page) // Use the layout file for settings page

        SecurityButton = findViewById<AppCompatImageButton>(R.id.Security)
        NotificationButton = findViewById<AppCompatImageButton>(R.id.Notification)
        PrivacyButton = findViewById<AppCompatImageButton>(R.id.Privacy)
        MySubscriptionButton = findViewById<AppCompatImageButton>(R.id.MySubscription)
        HelpANDSupportButton = findViewById<AppCompatImageButton>(R.id.HelpANDSupport)
        TermsANDPoliciesButton = findViewById<AppCompatImageButton>(R.id.TermsANDPolicies)
        FreeUpSpaceButton = findViewById<AppCompatImageButton>(R.id.FreeUpSpace)
        DataSaverButton = findViewById<AppCompatImageButton>(R.id.DataSaver)
        ReportAProblemButton = findViewById<AppCompatImageButton>(R.id.ReportAProblem)
        LogOutButton = findViewById<AppCompatImageButton>(R.id.LogOut)

        SecurityButton .setOnClickListener {
            val intent = Intent(this, ProfilePage::class.java)
            startActivity(intent)
        }
        NotificationButton.setOnClickListener {
            val intent = Intent(this, NotificationPage::class.java)
            startActivity(intent)
        }
        PrivacyButton.setOnClickListener {
            val intent = Intent(this, PrivacyPolicyPage::class.java)
            startActivity(intent)
        }
        MySubscriptionButton.setOnClickListener {
            val intent = Intent(this, ProfilePage::class.java)
            startActivity(intent)
        }
        HelpANDSupportButton.setOnClickListener {
            val intent = Intent(this, ProfilePage::class.java)
            startActivity(intent)
        }
        TermsANDPoliciesButton.setOnClickListener {
            val intent = Intent(this, TermsANDPoliciesButton::class.java)
            startActivity(intent)
        }
        FreeUpSpaceButton.setOnClickListener {
            val intent = Intent(this, ProfilePage::class.java)
            startActivity(intent)
        }
        DataSaverButton.setOnClickListener {
            val intent = Intent(this, ProfilePage::class.java)
            startActivity(intent)
        }
        ReportAProblemButton.setOnClickListener {
            val intent = Intent(this, ProfilePage::class.java)
            startActivity(intent)
        }
        LogOutButton.setOnClickListener {
            val intent = Intent(this, ProfilePage::class.java)
            startActivity(intent)
        }

    }
}