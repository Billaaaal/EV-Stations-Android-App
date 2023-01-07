package com.example.evchargingstationsapp.ui.detailsactivity

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.rememberAsyncImagePainter

@Composable
fun stationImage(
    image:String = "https://lh5.googleusercontent.com/p/AF1QipNS78yccNZjIf6ghUyQGP0VpMsD6r56456JC3No=w1000-h2000-k-no"

){
    Image(
        painter = rememberAsyncImagePainter(image),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(420.dp)
            .zIndex(1f)

        ,
        contentScale = ContentScale.Crop ////////////VERY IMPORTANT

    )

}