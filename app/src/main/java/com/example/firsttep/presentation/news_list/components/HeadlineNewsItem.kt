package com.example.firsttep.presentation.news_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.firsttep.data.remote.dto.Article
import com.example.firsttep.data.remote.dto.Source
import com.example.firsttep.utility.Constants

@ExperimentalMaterialApi
@Composable
fun HeadlineNewsItem(
    article: Article,
    onItemClick: (Article) -> Unit
) {
    Row {
        Card(
            shape = CircleShape.copy(CornerSize(6)),
            elevation = 10.dp,
            onClick = { onItemClick(article) }
        ) {
            Box(
                modifier = Modifier.width(310.dp)
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
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Transparent,
                                    Color.Black
                                ),
                                startY = 30f
                            )
                        )
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Text(
                        text = article.title,
                        style = TextStyle(color = Color.White, fontWeight = FontWeight.SemiBold),
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 20.sp
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(20.dp))
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun Preview() {
    HeadlineNewsItem(
        article = Article(
            "Shady",
            "Shady author b=make book",
            "Shady author b=make bookShady author b=make book",
            "today",
            Source("", "Art"),
            "Subtle art of Lose Yourself",
            "",
            ""
        ), onItemClick = {

        })
}