package com.example.firsttep.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.firsttep.data.remote.dto.Article
import com.example.firsttep.presentation.news_detail.NewsDetailsScreen
import com.example.firsttep.presentation.news_list.NewsListScreen
import com.google.gson.Gson

@ExperimentalMaterialApi
fun NavGraphBuilder.setupHomeNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = ScreenRoute.NewsListScreen.route,
        route = HOME_ROUTE
    ) {
        composable(
            route = ScreenRoute.NewsListScreen.route
        ) {
            NewsListScreen(navController)
        }
        composable(
            route = ScreenRoute.NewsDetailScreen.route,
            arguments = listOf(
                navArgument("Article") {
                    type = NavType.StringType
                }
            )
        ) {
            val article = Gson().fromJson(it.arguments?.getString("Article"), Article::class.java)
            NewsDetailsScreen(navController, article = article)
        }
    }
}