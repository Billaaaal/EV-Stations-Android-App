package com.example.evchargingstationsapp.ui.mainactivity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.evchargingstationsapp.R



@Composable
fun bottomNavBar(

) {
    data class Section(
        val name:String,
        val icon:Int,
    )

    val items = listOf(
        Section("Favorites", R.drawable.favoriteicon),
        Section("Trips", R.drawable.tripicon),
        Section("Explore", R.drawable.exploreicon),
        Section("Profile", R.drawable.profileicon)
    )
    var selectedItem by remember { mutableStateOf(0) }


    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth(0.90f)
            .padding(bottom = 20.dp),
        elevation = 0.dp,
        backgroundColor = Color.Transparent

    ) {
        items.forEachIndexed { index, (name, icon) ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = icon) ,
                        contentDescription = "content description", tint= Color(0XFF9d9ea2),
                        modifier = Modifier
                            .size((22.5).dp)
                    )


                },
                label = {
                    Text(
                        text = name,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .background(Color.Transparent)
                            .padding(top=10.dp)
                        ,
                        style = TextStyle(
                            color = Color(0XFF9d9ea2), //Color(0XFF0273f2)
                            fontSize = 10.sp,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily(
                                Font(R.font.aveniregular)
                            ),
                        ),
                    )
                },
                selected = selectedItem == index,
                onClick = { selectedItem = index }
            )
        }
    }
}