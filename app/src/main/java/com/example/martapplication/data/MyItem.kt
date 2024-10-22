package com.example.martapplication.data

import android.net.Uri

data class MyItem(
    val imageUri: Uri?,
    val bioText: String,
    val abstractText: String,
    val forsaleText: String,
    val portraitText:String
)
