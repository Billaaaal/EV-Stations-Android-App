package com.example.evchargingstationsapp.ui.mainactivity

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun suggestionsButtons(text:String, bg: Color, fg: Color, onClick: () -> Unit){ //add onclick unit
    Button(modifier = Modifier
        .height(30.dp)
        .padding(end = 15.dp),

        contentPadding = PaddingValues(start = 15.dp, end = 15.dp), ////////////VERY IMPORTANT

        shape = RoundedCornerShape(40.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 1.dp
        ),


        colors = ButtonDefaults.buttonColors(containerColor = bg),
        onClick = {
            onClick()





        }
    ){
        Text(
            text = text,
            modifier = Modifier
                .align(Alignment.CenterVertically),
            style = TextStyle(
                color = fg, //Color(0XFF0273f2)
                fontSize = 15.sp,
                fontWeight = FontWeight.ExtraBold
            )
        )



    }

}