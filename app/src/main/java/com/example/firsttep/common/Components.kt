package com.example.firsttep.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Tag(text: String) {
    Card(
        shape = CircleShape.copy(CornerSize(30))
    ) {
        Text(text = text, fontWeight = FontWeight.Normal, fontSize = 12.sp, modifier = Modifier
            .background(Color.LightGray)
            .padding(vertical = 2.dp, horizontal = 18.dp))
    }
}

@Composable
fun Loader() = CircularProgressIndicator()
