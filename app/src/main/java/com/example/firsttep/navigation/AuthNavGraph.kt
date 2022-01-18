package com.example.firsttep.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.firsttep.presentation.auth.LoginScreen
import com.example.firsttep.presentation.auth.SignupScreen

fun NavGraphBuilder.setupAuthNavGraph(navController : NavHostController) {
    navigation(startDestination = ScreenRoute.LoginScreen.route, route = AUTH_ROUTE) {
        composable(
            route = ScreenRoute.LoginScreen.route
        ) {
            LoginScreen(navController)
        }
        composable(
            route = ScreenRoute.SignupScreen.route
        ) {
            SignupScreen(navController)
        }
    }
}