package com.example.firsttep.presentation.news_list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.firsttep.presentation.MyApp
import com.example.firsttep.ui.theme.Typography

@Composable
fun NewsContent(text: String) {
    SelectionContainer {
        Text(text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.sp,
                    color = Color.Black
                )
            ) {
                append(text[0])
            }
            withStyle(
                style = SpanStyle(
                    fontSize = 18.sp, fontWeight = FontWeight.Normal
                )
            ) {
                append(text.removeRange(IntRange(0, 1)))
            }
        })
    }
}

@Preview
@Composable
fun PreviewTest() {
    MyApp {
        Column {
            NewsContent(text = "Test im not working... im the world")
        }
    }
}
