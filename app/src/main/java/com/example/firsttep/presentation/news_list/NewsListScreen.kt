package com.example.firsttep.presentation.news_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import coil.compose.rememberImagePainter
import com.example.firsttep.common.AppTheme.defaultPadding
import com.example.firsttep.common.CustomComp
import com.example.firsttep.common.CustomTimer
import com.example.firsttep.data.remote.dto.Article
import com.example.firsttep.navigation.ScreenRoute
import com.example.firsttep.presentation.news_list.components.HeadlineNewsItem
import com.example.firsttep.presentation.news_list.components.NewsItem
import com.example.firsttep.ui.theme.Typography
import com.example.firsttep.utility.toJsonString

@ExperimentalMaterialApi
@Composable
fun NewsListScreen(
    navController: NavController,
    viewModel: NewsListViewModel = hiltViewModel()
) {
    val headLineState by remember {
        mutableStateOf(viewModel.headlineState)
    }
    val recommendNewsState by remember {
        mutableStateOf(viewModel.recommendNewsState)
    }
    LazyColumn {
        item {
            Toolbar()
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column {
                    Spacer(Modifier.height(8.dp))
                    Text(
                        modifier = defaultPadding(),
                        text = "Today Date",
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Light,
                        fontSize = 14.sp
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        modifier = defaultPadding(),
                        text = "Welcome Back, \nArul",
                        style = Typography.h2
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(
                        modifier = defaultPadding(),
                        text = "Headline",
                        fontFamily = FontFamily.Cursive,
                        fontSize = 16.sp
                    )
                    NewsContent(state = headLineState, navController = navController)
                    Spacer(Modifier.height(8.dp))
                    Row(
                        defaultPadding()
                    ) {
                        Text(
                            text = "Just For You",
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.weight(1F)
                        )
                        Text(text = "See More", color = Color.Blue, fontSize = 14.sp)
                    }
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
        item {
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                if (recommendNewsState.value is MainState.Loading) {
                    CircularProgressIndicator()
                } else if (recommendNewsState.value is MainState.OnError) {
                    Text(
                        text = (recommendNewsState.value as MainState.OnError).error?.displayMessage!!,
                        color = MaterialTheme.colors.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    )
                }
            }
        }
        if (recommendNewsState.value is MainState.ReceiveNews) {
            val news: List<Article> = (recommendNewsState.value as MainState.ReceiveNews).news!!
            items(items = news) { article ->
                NewsItem(article = article) { selectedArticle ->
                    val articleObjString = selectedArticle.toJsonString()
                    val action =
                        ScreenRoute.NewsDetailScreen.route.replace(
                            "{Article}",
                            articleObjString
                        )
                    navController.navigate(action)
                }
            }
        }
    }
}

@Composable
fun Toolbar() {
    TopAppBar(elevation = 0.dp) {
        Text(
            text = "News.IO",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp), contentAlignment = Alignment.CenterEnd
        ) {
            Image(
                painter = rememberImagePainter("https://image.shutterstock.com/z/stock-photo-upset-uneasy-cute-ginger-gitl-long-natural-red-hair-frowning-smirking-hesitant-complain-looking-2095529050.jpg"),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,            // crop the image if it's not a square
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)                       // clip to the circle shape
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun NewsContent(state: State<MainState>, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {

        when (val value = state.value) {
            MainState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            is MainState.OnError -> {
                Text(
                    text = value.error?.displayMessage!!,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )
            }
            is MainState.ReceiveNews -> {
                value.news?.let {
                    if (it.isEmpty())
                        Text(
                            text = "Empty news :)",
                            color = MaterialTheme.colors.error,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                        )
                    else
                        LazyRow(
                            modifier = Modifier.padding(bottom = 14.dp, top = 6.dp)
                        ) {
                            val news = value.news
                            items(items = news) { article ->
                                HeadlineNewsItem(article = article) { selectedArticle ->
                                    val articleObjString = selectedArticle.toJsonString()
                                    val action = ScreenRoute.NewsDetailScreen.route.replace(
                                        "{Article}",
                                            articleObjString
                                    )
                                    navController.navigate(action)
                                }
                            }
                        }
                }

            }
        }
    }
}