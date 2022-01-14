package com.example.firsttep.domain.repository

import com.example.firsttep.data.remote.dto.Article

interface NewsRepository {
    suspend fun getHeadLine(country: String): List<Article>
    suspend fun getEveryNews(country: String): List<Article>
}