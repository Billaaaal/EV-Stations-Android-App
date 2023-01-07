package com.example.evchargingstationsapp.ui.detailsactivity

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.evchargingstationsapp.R

@ExperimentalMaterial3Api
@Composable
fun intentCard(
    intentText:String,
    intentIcon: Int,
    onClick: () -> Unit

){
    var rippleColorTheme = object : RippleTheme {
        // Here you should return the ripple color you want
        // and not use the defaultRippleColor extension on RippleTheme.
        // Using that will override the ripple color set in DarkMode
        // or when you set light parameter to false
        @Composable
        override fun defaultColor(): Color = Color(0xFF0091EA)

        @Composable
        override fun rippleAlpha(): RippleAlpha = RippleAlpha(0.0f, 0.0f, 0.0f, 0.0f)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(Color.White),
        onClick = {
            onClick()
        }

    ){
        Row(
            modifier= Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CompositionLocalProvider(LocalRippleTheme provides  rippleColorTheme) {
                Button(modifier = Modifier
                    .padding(start = 15.dp)
                    .height((30).dp)
                    .width((30).dp)

                    ,
                    contentPadding = PaddingValues(0.dp),

                    shape = RoundedCornerShape(50.dp),

                    colors = ButtonDefaults.buttonColors(containerColor = Color(0XFFf4f4f5)),

                    onClick = {
                        onClick()
                    }
                ){
                    Icon(
                        painterResource(id = intentIcon) ,
                        contentDescription = "content description", tint= Color(0XFFa5a7b1),
                        modifier = Modifier
                            .size(15.dp)
                    )


                }
            }



            Text(
                text = intentText,
                modifier = Modifier
                    .offset(y = (-2.5).dp)
                    .padding(start = 15.dp)
                    .fillMaxWidth(0.75f),

                style = TextStyle(
                    fontFamily = FontFamily(
                        Font(R.font.sofiapromedium)
                    ),
                    color = Color(0xFF000000),
                    fontSize = (16).sp
                ),
                maxLines = 1,

                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                painterResource(id = R.drawable.reducearrow),
                contentDescription = "content description", tint= Color(0xFF999CA0),

                modifier = Modifier
                    .padding(end = 10.dp)
                    .width(15.dp)
                    .height(15.dp)
                    .size(10.dp)
                    .rotate(-90f),

                )




        }

    }

}