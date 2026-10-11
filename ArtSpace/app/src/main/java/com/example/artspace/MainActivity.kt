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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableIntStateOf

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


data class Artwork(
    val image: Int,
    val title: String,
    val artist: String,
    val year: Int
)

@Composable
fun ArtSpaceLayout() {
    val artworks = listOf(
        Artwork(
            R.drawable.fallen_angel,
            "Fallen Angel",
            "Alexandre Cabanel",
            1868
        ),
        Artwork(
            R.drawable.creation_of_adam,
            "Creation of Adam",
            "Michelangelo",
            1510
        ),
        Artwork(
            R.drawable.wanderer_above_the_sea,
            "The Wanderer above the sea",
            "Caspar David Friedrich",
            1818
        )
    )

    var currentIndex by remember { mutableIntStateOf(0) }

    val currentArtwork = artworks[currentIndex]

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
            painter = painterResource(currentArtwork.image),
            contentDescription = currentArtwork.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),
            contentScale = ContentScale.Fit
        )


        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = currentArtwork.title,
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${currentArtwork.artist} (${currentArtwork.year})",
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
                onClick = {
                    currentIndex = if (currentIndex > 0) {
                        currentIndex - 1
                    } else {
                        artworks.lastIndex
                    }
                },
                modifier = Modifier.width(120.dp)
            ) {
                Text("Previous")
            }
            Button(
                onClick = {
                    currentIndex = if (currentIndex < artworks.lastIndex) {
                        currentIndex + 1
                    } else {
                        0
                    }
                },
                modifier = Modifier.width(120.dp)
            ) {
                Text("Next")
            }
        }
    }
}
