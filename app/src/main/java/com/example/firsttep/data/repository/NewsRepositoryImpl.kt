package com.example.firsttep.data.repository

import com.example.firsttep.data.remote.NewsApi
import com.example.firsttep.data.remote.dto.Article
import com.example.firsttep.domain.repository.NewsRepository
import com.example.firsttep.utility.Constants
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi
): NewsRepository {

    override suspend fun getHeadLine(country: String): List<Article> {
        val news = api.getTopHeadlines(Constants.API_KEY, country = country)
        return news.articles
    }

    override suspend fun getEveryNews(query: String): List<Article> {
        val news = api.getEveryNews(Constants.API_KEY, query = query)
        return news.articles
    }
}