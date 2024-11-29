package com.example.todo3.screen.pages

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.todo3.R
import com.example.todo3.model.AuthViewModel
import com.example.todo3.model.TaskViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePage(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel,
    taskViewModel: TaskViewModel = viewModel()
) {
    val username by authViewModel.username.observeAsState("")
    val nim by authViewModel.nim.observeAsState("")
    val profileImageUri by authViewModel.profileImageUri.observeAsState(null)
    var newUsername by remember { mutableStateOf(username) }
    var newNim by remember { mutableStateOf(nim) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            imageUri = it
            authViewModel.uploadProfileImage(it)
        }
    }

    val vintageFont = FontFamily(
        Font(R.font.gamaamli_regular, FontWeight.Normal)
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF6BF6C3), Color(0xFF3D79F8))
                )
            ),


    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(35.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            Text(
                text = "Profile",
                fontSize = 40.sp,
                fontFamily = vintageFont,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
                    .clickable { imageLauncher.launch("image/*") },
                contentAlignment = Alignment.Center
            ) {
                if (imageUri != null || profileImageUri != null) {
                    val painter: Painter = rememberAsyncImagePainter(model = imageUri ?: profileImageUri)
                    Image(
                        painter = painter,
                        contentDescription = "Profile Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(120.dp)
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Add Photo",
                        modifier = Modifier.size(48.dp),
                        tint = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = newUsername,
                onValueChange = { newUsername = it },
                label = { Text("Username") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White.copy(alpha = 0.8f),
                    cursorColor = Color.White,
                    selectionColors = TextSelectionColors(
                        handleColor = Color.White,
                        backgroundColor = Color.Blue.copy(alpha = 0.4f)
                    ),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                shape = RoundedCornerShape(16.dp)
            )

            TextField(
                value = newNim,
                onValueChange = { newNim = it },
                label = { Text("NIM") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White.copy(alpha = 0.8f),
                    cursorColor = Color.White,
                    selectionColors = TextSelectionColors(
                        handleColor = Color.White,
                        backgroundColor = Color.Blue.copy(alpha = 0.4f)
                    ),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                shape = RoundedCornerShape(16.dp)
            )

            Button(
                onClick = {

                    authViewModel.updateProfile(newUsername, newNim)
                    navController.navigate("home")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color(0xFF38A1F3))
            ) {
                Text("Save Changes")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    authViewModel.signout()
                    navController.navigate("login") {
                        popUpTo("profile") { inclusive = true }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Sign Out")
            }
        }
    }

}

