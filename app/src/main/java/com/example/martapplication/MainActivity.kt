package com.example.martapplication

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.martapplication.fragment.HomeFragment
import com.example.martapplication.fragment.MessagesFragment
import com.example.martapplication.fragment.NotificationFragment
import com.example.martapplication.fragment.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(),BottomNavigationView.OnNavigationItemSelectedListener {

    lateinit var bottomNavigationView:BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayShowTitleEnabled(false)




        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener(this)

        supportFragmentManager.beginTransaction().replace(R.id.nav_fragment, HomeFragment()).commit()
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.homeFragment -> {
                val fragment = HomeFragment()
                supportFragmentManager.beginTransaction().replace(R.id.nav_fragment, fragment, fragment.javaClass.simpleName)
                    .commit()
                return true
            }
            R.id.messagesFragment -> {
                val fragment = MessagesFragment()
                supportFragmentManager.beginTransaction().replace(R.id.nav_fragment, fragment, fragment.javaClass.simpleName)
                    .commit()
                return true
            }
            R.id.notificationFragment -> {
                val fragment = NotificationFragment()
                supportFragmentManager.beginTransaction().replace(R.id.nav_fragment, fragment, fragment.javaClass.simpleName)
                    .commit()
                return true
            }
            R.id.profileFragment -> {
                val fragment = ProfileFragment()
                supportFragmentManager.beginTransaction().replace(R.id.nav_fragment, fragment, fragment.javaClass.simpleName)
                    .commit()
                return true
            }
        }
        return false
    }
}
