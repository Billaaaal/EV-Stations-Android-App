package com.example.evchargingstationsapp.ui.mainactivity

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.evchargingstationsapp.R

@ExperimentalMaterial3Api
@Composable
@Preview
fun stationCardSearchResult(name:String = "Target Parking Lot", address:String= "Target Parking Lot", photo: Any = "https://lh5.googleusercontent.com/p/AF1QipORJvDq5ChaxGO2pjn2LJ9L_8B2UMusI6KjfezY=w3024-h4032-k-no", onClick: () -> Unit = {}) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(10.dp)),
        colors = CardDefaults.cardColors(Color.White),
        onClick = {
            onClick()
        }

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = rememberAsyncImagePainter((photo.toString()).replace("&w=86&h=86&","&w=500&h=500&")),
                contentDescription = null,
                modifier = Modifier
                    .width(80.dp)
                    .height(60.dp)
                    .padding(start = 10.dp)
                    .clip(RoundedCornerShape(6.dp))
                ,
                contentScale = ContentScale.Crop ////////////VERY IMPORTANT

            )

            Column(modifier= Modifier
                .width(225.dp)
                .height(60.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center

            ){
                Text(
                    text = name,
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .offset(y = (-2).dp),
                    style = TextStyle(
                        fontFamily = FontFamily(
                            Font(R.font.janabold)
                        ),
                        color = Color(0XFF000000),
                        fontSize = 15.sp
                    ),
                    maxLines = 1
                )
                Text(
                    text = address,
                    modifier = Modifier
                        .padding(start = 15.dp),


                    style = TextStyle(
                        fontFamily = FontFamily(
                            Font(R.font.janabold)
                        ),
                        color = Color(0XFFc6c8ce),
                        fontSize = 11.sp
                    ),
                    maxLines = 1
                )



            }

            /*
            IconButton(modifier = Modifier
                 .width(15.dp)
                 .height(15.dp)
                 .padding(start = 15.dp),
                 onClick = {
                     true

                 }
             ){
                 Icon(
                     painterResource(id = R.drawable.crossicon),
                     contentDescription = "content description", tint=Color(0xFFCECFD4),
                     modifier = Modifier
                         .size((12.5).dp)
                 )



             }

             */





        }

    }

}