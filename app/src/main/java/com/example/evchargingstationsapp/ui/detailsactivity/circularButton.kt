package com.example.evchargingstationsapp.ui.detailsactivity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.evchargingstationsapp.R

@Composable
fun circularButton(
    icon:Int,
    buttonTitle:String,
    onClick: () -> Unit = {}


) {
    var rippleColorTheme = object : RippleTheme {
        // Here you should return the ripple color you want
        // and not use the defaultRippleColor extension on RippleTheme.
        // Using that will override the ripple color set in DarkMode
        // or when you set light parameter to false
        @Composable
        override fun defaultColor(): Color = Color(0xFF0091EA)

        @Composable
        override fun rippleAlpha(): RippleAlpha = RippleAlpha(0.75f, 0.75f, 0.75f, 0.75f)
    }
    Column(
        modifier = Modifier
            .height(85.dp)
            .width(65.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CompositionLocalProvider(LocalRippleTheme provides  rippleColorTheme) {
            Button(
                modifier = Modifier
                    .height((60).dp)
                    .width((60).dp)
                    .padding(top = (2.5).dp),
                contentPadding = PaddingValues(0.dp),

                shape = RoundedCornerShape(80.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = (0.25).dp
                ),

                colors = ButtonDefaults.buttonColors(containerColor = if (buttonTitle=="Start") Color(0XFF007aff) else Color(0XFFf4f4f5)),

                onClick = {
                    onClick()

                }
            ){
                Icon(
                    painterResource(id = icon) ,
                    contentDescription = "content description", tint= if (buttonTitle=="Start") Color(
                        0xFFFFFFFF
                    ) else Color(0xFF000000),
                    modifier = Modifier
                        .size(26.dp)
                )






            }
        }


        Text(
            text = buttonTitle,
            modifier = Modifier
                .padding(top=5.dp),

            style = TextStyle(
                fontFamily = FontFamily(
                    Font(R.font.sofiapromedium)
                ),
                color = Color(0xFF999CA0),
                fontSize = (13.5).sp
            )
        )

    }







}