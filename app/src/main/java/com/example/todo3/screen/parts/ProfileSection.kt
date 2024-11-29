package com.example.todo3.screen.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todo3.R

@Composable

fun ProfileSection(username: String, nim: String, vintageFont: androidx.compose.ui.text.font.FontFamily, navController: NavController) {
    // MainRow
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // RowLeft
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile), // Replace with your profile picture resource
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(Color.Gray) // Placeholder background color
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = username,
                    fontSize = 24.sp,
                    fontFamily = vintageFont,
                    color = Color.White,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Text(
                    text = nim,
                    fontSize = 16.sp,
                    fontFamily = vintageFont,
                    color = Color.White,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
        // Button to navigate to ProfilePage
        IconButton(
            onClick = {
                // Navigate to ProfilePage
                navController.navigate("finish")
            },
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(Color.Gray)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.seting), // Replace with your profile picture resource
                contentDescription = "Go to Profile Page",
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}