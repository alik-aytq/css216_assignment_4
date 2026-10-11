package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                ArtSpaceLayout()
            }
        }
    }
}

@Composable
fun ArtSpaceLayout() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Art Space",
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        Image(
            painter=painterResource(R.drawable.fallen_angel),
            contentDescription="Fallen Angel by Alexandre Cabanel",
            modifier=Modifier
                .fillMaxWidth()
                .height(350.dp),
            contentScale=ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Fallen Angel",
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Alexandre Cabanel (1868)",
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { },
                modifier = Modifier.width(120.dp)
            ) {
                Text("Previous")
            }

            Button(
                onClick = { },
                modifier = Modifier.width(120.dp)
            ) {
                Text("Next")
            }
        }

    }
}
