package com.example.firsttep.data.remote.dto


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Article(
    val author: Any,
    val content: String? = null,
    val description: String? = null,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String? = null
) : Serializable