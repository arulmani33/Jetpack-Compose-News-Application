package com.example.firsttep.presentation.news_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.firsttep.data.remote.dto.Article
import com.example.firsttep.data.remote.dto.Source
import com.example.firsttep.presentation.MyApp
import com.example.firsttep.utility.Constants
import com.example.firsttep.utility.dateToTimeAgo

@ExperimentalMaterialApi
@Composable
fun NewsDetailsScreen(
    navController: NavController,
    article: Article,
    viewModel: NewsDetailViewModel = hiltViewModel()
) {
    Column {
        TitleImage(article = article, navController)
        ArticleContent(article, navController)
    }

}

@Composable
fun ArticleContent(article: Article, navController: NavController) {
    Row(modifier = Modifier.fillMaxWidth()) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "", tint = Color.Black)
        }
        Text(
            text = article.title,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }
    Divider(
        startIndent = 14.dp,
        thickness = 1.dp
    )
    Spacer(Modifier.height(8.dp))
    Text(
        text = article.content,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
        ,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    )
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(6.dp), contentAlignment = Alignment.BottomEnd) {
        Text(text = article.publishedAt.dateToTimeAgo() ?: article.publishedAt, fontSize = 14.sp, fontWeight = FontWeight.Light)
    }
}

@Composable
fun TitleImage(article: Article, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        Image(
            painter = rememberImagePainter(
                data = if (article.urlToImage == null || article.urlToImage.isEmpty()) Constants.DEFAULT_IMAGE else article.urlToImage,
                builder = {
                    crossfade(true)
                }
            ),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxSize(), contentAlignment = Alignment.BottomEnd
        ) {
            IconButton(onClick = { print("CLicked") }) {
                Icon(
                    Icons.Outlined.ThumbUp,

                    "contentDescription",
                    modifier = Modifier.size(26.dp),
                    tint = Color.LightGray
                )
            }
        }
    }
}


@Composable
@Preview
fun DefaultPreview() {
    MyApp {
        val ars = Article(
            "Shady",
            "Shady author b=make book",
            "Shady author b=make bookShady author b=make book",
            "today",
            Source("", "Art"),
            "Subtle art of Lose Yourself",
            "",
            "https://images.unsplash.com/photo-1613467663837-e4a6be2014b6?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1219&q=80"
        )
        Column {
//            TitleImage(
//                article = ars,
//                navController = navController
//            )
//            ArticleContent(article = ars, navController = navController)
        }
    }

}