package com.example.todo3.screen.parts

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo3.R

@Composable
fun AlternativeLogin() {
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 10.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            // Google Login Button
            SocialLoginButton(
                onClick = { Toast.makeText(context, "Bekerja", Toast.LENGTH_SHORT).show() },
                imageId = R.drawable.google,
                description = "Login with Google"
            )

            // Facebook Login Button
            SocialLoginButton(
                onClick = { Toast.makeText(context, "Bekerja", Toast.LENGTH_SHORT).show() },
                imageId = R.drawable.facebook,
                description = "Login with Facebook"
            )

            // Twitter Login Button
            SocialLoginButton(
                onClick = { Toast.makeText(context, "Bekerja", Toast.LENGTH_SHORT).show() },
                imageId = R.drawable.twitter,
                description = "Login with Twitter"
            )
        }

        Row(
            modifier = Modifier.padding(top = 20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "By signing up, you agree with our",
                color = Color.White,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "Terms & Conditions",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun SocialLoginButton(onClick: () -> Unit, imageId: Int, description: String) {
    Box {
        Button(
            onClick = onClick,
            modifier = Modifier
                .size(50.dp), // Ukuran tombol ditingkatkan
            shape = CircleShape,
            contentPadding = PaddingValues(10.dp),
            colors = ButtonDefaults.buttonColors(Color.White)
        ) {
            Image(
                painter = painterResource(id = imageId)
                ,
                modifier = Modifier
                    .size(200.dp),
                contentDescription = description,
            )
        }
    }
}
