package com.example.firsttep.presentation.news_list

import com.example.firsttep.data.remote.dto.Article

sealed class MainState {
    object Loading: MainState()
    data class ReceiveNews(val news: List<Article>? = null): MainState()
    data class OnError(val error: ErrorState? = null): MainState()
}

data class ErrorState(
    val error: String? = null,
    val code: Int = -1,
    val displayMessage: String? = null
)