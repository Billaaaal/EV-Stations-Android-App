package com.example.evchargingstationsapp.ui.detailsactivity

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.evchargingstationsapp.R

@Composable
fun stationNameTitle(
    stationName:String = "Charging station"

){
    Text(
        text = stationName,
        modifier = Modifier
            .offset(y = (-8).dp)
            .fillMaxWidth(0.65f)
        ,
        maxLines = 1,
        style = TextStyle(
            fontFamily = FontFamily(
                Font(R.font.avenirblack)
            ),
            color = Color(0xCB000000),
            fontSize = (22.sp
                    )
        )
    )


}