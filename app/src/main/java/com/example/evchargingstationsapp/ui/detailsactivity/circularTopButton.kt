package com.example.evchargingstationsapp.ui.detailsactivity

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.evchargingstationsapp.R

@Composable
fun circularTopButton(
    icon:Int = R.drawable.currentlocationicon,
    size: Dp = 25.dp,
    onClick: () -> Unit = {}

) {
    Button(modifier = Modifier
        .zIndex(2f)
        .height((42.5).dp)
        .width((42.5).dp) ,
        contentPadding = PaddingValues(0.dp),

        shape = RoundedCornerShape(50.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp
        ),

        colors = ButtonDefaults.buttonColors(containerColor = Color.White),

        onClick = {
            onClick()

        }
    ){
        Icon(
            painterResource(id = icon) ,
            contentDescription = "content description", tint= Color(0xFF000000),
            modifier = Modifier
                .size(size)
        )


    }





}