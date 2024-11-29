package com.example.todo3.screen.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.todo3.R

@Composable
fun Logo() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top // Align items to the top of the Logo column
    ) {
        Image(
            painter = painterResource(id = R.drawable.logobawahbiru),
            contentDescription = "Image Judul",
            modifier = Modifier.size(200.dp)
        )
        Text(
            text = "Effortless Task Management\n" +
                    "Streamlined To-Do List.",
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}
