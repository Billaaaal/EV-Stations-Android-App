package com.example.evchargingstationsapp.ui.mainactivity

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.evchargingstationsapp.R
import java.util.Locale

@ExperimentalMaterial3Api
@Composable
fun stationCard(name:String, address:String, photo:Any, onClick: () -> Unit) {

    Card(
        modifier = Modifier
            .width(340.dp)
            .height(100.dp)
            .clip(RoundedCornerShape(10.dp)),
        colors = CardDefaults.cardColors(Color.White),
        onClick = {
            onClick()
        }

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = rememberAsyncImagePainter((photo.toString()).replace("&w=86&h=86&","&w=500&h=500&")),
                contentDescription = null,
                modifier = Modifier
                    .width(90.dp)
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop ////////////VERY IMPORTANT

            )

            Column(modifier= Modifier
                .fillMaxWidth()
                .height(90.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceEvenly

            ){
                Text(
                    text = name,
                    modifier = Modifier
                        .padding(
                            start = 10.dp
                        )
                        .fillMaxWidth(),
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
                        .padding(start = 10.dp)
                        .fillMaxWidth()
                        .offset(y = (-5).dp),

                    style = TextStyle(
                        fontFamily = FontFamily(
                            Font(R.font.janabold)
                        ),
                        color = Color(0XFFc6c8ce),
                        fontSize = 11.sp
                    ),
                    maxLines = 1
                )
                Column(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .height(20.dp)
                        .offset(y = (-2).dp)
                        .clip(RoundedCornerShape(8.dp, 8.dp, 8.dp, 8.dp))
                        .background(Color.Black)



                    ,

                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center

                ){
                    Text(
                        text = "2/3 spots left".uppercase(Locale.getDefault()),
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .padding(end = 10.dp)
                            .offset(y = 0.dp),
                        style = TextStyle(
                            fontFamily = FontFamily(
                                Font(R.font.janabold)
                            ),
                            color = Color.White,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )

                }


            }



        }

    }
}