package com.example.firsttep.navigation


const val ROOT_ROUTE = "root_route"
const val HOME_ROUTE = "main_route"
const val AUTH_ROUTE = "auth_route"

sealed class ScreenRoute(val route: String) {
    object NewsListScreen: ScreenRoute("news_list_screen")
    object NewsDetailScreen: ScreenRoute("news_detail_screen?Article={Article}")

    object LoginScreen: ScreenRoute("login_screen")
    object SignupScreen: ScreenRoute("signup_screen")
}
