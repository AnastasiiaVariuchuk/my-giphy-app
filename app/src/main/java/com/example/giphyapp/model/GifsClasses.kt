package com.example.giphyapp.model

import com.google.gson.annotations.SerializedName

data class GifResult (
    @SerializedName("data") val res: List<GifObject>
)

data class GifObject (
    @SerializedName("images") val images: GifImage
)

data class GifImage (
    @SerializedName("original") val ogImage: ogImage
)

data class ogImage (
    val url: String
)