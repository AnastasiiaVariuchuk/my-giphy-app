package com.example.giphyapp.service

import com.example.giphyapp.model.GifResult
import retrofit2.http.GET
import retrofit2.http.Query

//All configure data tou can add in res/values/strings.xml
interface GifsService {
    @GET("gifs/search")
    fun searchGifs(
        @Query("api_key") apiKey: String,
        @Query("q") query: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("rating") rating: String,
        @Query("lang") lang: String
    ): retrofit2.Call<GifResult>
}