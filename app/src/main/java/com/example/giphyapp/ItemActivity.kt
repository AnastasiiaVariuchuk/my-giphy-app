package com.example.giphyapp

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class ItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_activity)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val url = intent.getStringExtra( "url")
        Glide.with(this).load(url).into(imageView)
    }
}