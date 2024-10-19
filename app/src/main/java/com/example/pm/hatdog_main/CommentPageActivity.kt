package com.example.pm.hatdog_main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pm.R
import com.example.pm.adapter.comment_adapter
import com.example.pm.data.comment_profile

private lateinit var BackArrowProduct: AppCompatImageButton

class CommentPageActivity : AppCompatActivity() {

    private lateinit var commentRecyclerView: RecyclerView
    private lateinit var commentAdapter: comment_adapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.comment_section)

        // Set up the back button
        BackArrowProduct = findViewById(R.id.comment_button_back)
        BackArrowProduct.setOnClickListener {
            val intent = Intent(this, face_product_template::class.java)
            startActivity(intent)
        }

        // Sample data for testing the adapter
        val sampleComments = listOf(
            comment_profile("User1", "10:00 AM", "Great product!", R.drawable.placeholder),
            comment_profile("User2", "10:05 AM", "I love it!", R.drawable.prof_pic_holder),
            comment_profile("User3", "10:10 AM", "Highly recommend!", R.drawable.prof_pic_holder)
        )
        //!!!! Gap is too big in simulation !!!!
        //!!!! Gap is too big in simulation !!!!
        //!!!! Gap is too big in simulation !!!!
        //!!!! Gap is too big in simulation !!!!
        //!!!! Gap is too big in simulation !!!!

        // Set up the RecyclerView
        commentRecyclerView = findViewById(R.id.comment_recycler)
        commentRecyclerView.layoutManager = LinearLayoutManager(this)

        // Set up the adapter with the sample comments
        commentAdapter = comment_adapter(sampleComments)
        commentRecyclerView.adapter = commentAdapter
    }
}