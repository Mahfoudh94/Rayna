package com.example.testrayna.Page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ProfileScreen(onLogout: () -> Unit) {
    val user = FirebaseAuth.getInstance().currentUser

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F4F4))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Card(
                shape = CircleShape,
                elevation = CardDefaults.cardElevation(8.dp),
                modifier = Modifier.size(140.dp)
            ) {
                if (user?.photoUrl != null) {
                    Image(
                        painter = rememberAsyncImagePainter(user.photoUrl),
                        contentDescription = "Profile Picture",
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.LightGray),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("👤", fontSize = 48.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))


            Card(
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = user?.displayName ?: "MAMMA_SAI",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF333333)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = user?.email ?: "MAMMASAI@email.com",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {
                    FirebaseAuth.getInstance().signOut()
                    onLogout()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)),
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Logout", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}
