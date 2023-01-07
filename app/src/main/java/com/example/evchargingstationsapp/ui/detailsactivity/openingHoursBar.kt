package com.example.evchargingstationsapp.ui.detailsactivity

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.evchargingstationsapp.R

@Composable
fun openingHours(
    isOpen:Boolean,
    hours:String


){

    var statusColor = Color(0xFF95C99E)
    var statusText = ""

    if(isOpen){
        statusColor = Color(0xFF95C99E)
        statusText = "Open"

    }else{
        statusColor = Color(0xFFD50000)
        statusText = "Closed"


    }




    Row(
        modifier= Modifier
            .fillMaxWidth(0.88f)
            .height((35).dp)
            .padding(top = (15).dp)
        //.offset(y=4.dp)
        ,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = statusText,
            modifier = Modifier
                .offset(y = (-4).dp)
            ,
            style = TextStyle(
                fontFamily = FontFamily(
                    Font(R.font.sofiapromedium)
                ),
                color = statusColor,
                fontSize = (16).sp
            )
        )
        AnimatedVisibility(
            modifier = Modifier,
            visible = isOpen,
            enter = fadeIn(),
            exit = fadeOut()
        ){
            Text(
                text = " . ",
                modifier = Modifier
                    .offset(y = (-7.5).dp)
                ,
                style = TextStyle(
                    fontFamily = FontFamily(
                        Font(R.font.sofiapromedium)
                    ),
                    color = Color(0xFF999CA0),
                    fontSize = (16).sp
                )
            )



        }
        Text(
            text = hours,
            modifier = Modifier
                .offset(y = (-3.5).dp)
            ,
            style = TextStyle(
                fontFamily = FontFamily(
                    Font(R.font.sofiapromedium)
                ),
                color = Color(0xFF999CA0),
                fontSize = (16).sp
            )
        )






    }
}