package com.example.firsttep.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.firsttep.data.remote.dto.Article
import com.example.firsttep.presentation.news_detail.NewsDetailsScreen
import com.example.firsttep.presentation.news_list.NewsListScreen
import com.example.firsttep.presentation.news_list.NewsListViewModel
import com.example.firsttep.ui.theme.MyApplicationTheme
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: NewsListViewModel by viewModels()

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApp {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = ScreenRoute.NewsListScreen.route
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

                        println("Articles ${it.arguments}")
                        val article = Gson().fromJson(it.arguments?.getString("Article"), Article::class.java)
                        NewsDetailsScreen(navController, article = article)
                    }
                }
            }
        }
    }
}


@Composable
fun MyApp(content: @Composable () -> Unit) {
    MyApplicationTheme {
        Scaffold(backgroundColor = MaterialTheme.colors.background) {
            Column {
                content()
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    MyApp {
//        Column(modifier = Modifier.fillMaxHeight()) {
//            Column(modifier = Modifier.weight(1f)) {
//                Greeting("Android")
//                Divider()
//                Greeting("Android")
//            }
//            Button(
//                onClick = { println("Button clicked") },
//                modifier = Modifier.align(Alignment.CenterHorizontally)
//            ) {
//                Text("Ready")
//            }
//        }
//    }
//}