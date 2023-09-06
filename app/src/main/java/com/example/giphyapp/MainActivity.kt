package com.example.giphyapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.giphyapp.adapter.GifsAdapter
import com.example.giphyapp.model.GifObject
import com.example.giphyapp.model.GifResult
import com.example.giphyapp.service.GifsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.giphy.com/v1/"
const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val gifs = mutableListOf<GifObject>()

        val adapter = GifsAdapter(this, gifs)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        //setting up onItemClickListener
        adapter.setOnItemClickListener(object : GifsAdapter. OnItemClickListener {
            override fun onItemClick (position: Int) {
                val intent = Intent(this@MainActivity, ItemActivity::class.java)
                intent.putExtra("url", gifs[position].images.ogImage.url)
                startActivity(intent)
            }
        })

        // retrofit instance
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // service
        val retroService = retrofit.create(GifsService::class.java)

        val apiKey = getString(R.string.api_key)
        val query = getString(R.string.query)
        val limit = Integer.parseInt(getString(R.string.limit))
        val offset = Integer.parseInt(getString(R.string.offset))
        val rating = getString(R.string.rating)
        val lang = getString(R.string.lang)


        retroService.searchGifs(apiKey, query, limit, offset, rating, lang).enqueue(object: Callback<GifResult?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse (call: Call<GifResult?>, response: Response<GifResult?>) {
                val body = response.body()
                Log.d(TAG,body.toString())
                if (body == null) {
                    Log.d(TAG,"onResponse: No response")
                }
                gifs.addAll(body!!.res)
                adapter.notifyDataSetChanged()
            }
            override fun onFailure(call: Call<GifResult?>, t: Throwable) {

            }
        })
    }
}