package com.example.firsttep.presentation

sealed class ScreenRoute(val route: String) {
    object NewsListScreen: ScreenRoute("news_list_screen")
    object NewsDetailScreen: ScreenRoute("news_detail_screen?Article={Article}")
}
