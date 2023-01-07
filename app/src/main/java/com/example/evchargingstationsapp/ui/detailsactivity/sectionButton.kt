package com.example.evchargingstationsapp.ui.detailsactivity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.evchargingstationsapp.R

@Composable
fun sectionButton(
    sectionName:String,
    isSelected:Boolean

) {



    Button(
        modifier = Modifier
            .height((30).dp)
            .width((100).dp),
        contentPadding = PaddingValues(0.dp),
        shape = RoundedCornerShape((2.5).dp),




        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),

        onClick = {
            true

        }
    ){
        Column(
            modifier = Modifier
                .height((30).dp)
                .width((100).dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = sectionName,
                modifier = Modifier
                    .height((15).dp),


                style = TextStyle(
                    fontFamily = FontFamily(
                        Font(R.font.avenirheavy)
                    ),
                    color = Color(0xFF999CA0),
                    fontSize = (14.7).sp
                )
            )

            if(isSelected == true){
                Box(
                    modifier = Modifier
                        .height((15).dp)
                        .fillMaxWidth()
                        .offset(y = (11).dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFF0277ff)),
                ){









                }

            }





        }









    }













}