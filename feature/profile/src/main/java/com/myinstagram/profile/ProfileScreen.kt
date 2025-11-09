package com.myinstagram.profile

// ProfileScreen.kt - Displays user profile UI using Jetpack Compose
// Author: Abifarhan
// Last updated: Nov 9, 2025
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest


@Composable
// Main composable for the profile screen
fun ProfileScreen(
    username: String = "User Name",
    email: String = "user@email.com",
    bio: String = "This is your bio."
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Avatar placeholder
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://i.pinimg.com/564x/99/36/77/993677105264906899.jpg")
                    .crossfade(true)
                    .build(),
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(96.dp)
                    .clip(CircleShape)
            )

            // Display user information
            Text(text = username, style = MaterialTheme.typography.titleLarge)
            Text(text = email, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = bio, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(24.dp))
            // Edit profile button
            Button(onClick = { /* TODO: Edit profile */ }) {
                Text("Edit Profile")
            }
        }
    }
}
