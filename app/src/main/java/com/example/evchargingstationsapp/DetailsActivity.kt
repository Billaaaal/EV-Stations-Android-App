@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.evchargingstationsapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import coil.compose.rememberAsyncImagePainter
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import com.example.evchargingstationsapp.B.Companion.jsonObject
import kotlin.math.max
import com.example.evchargingstationsapp.ui.RatingBarView
import com.example.evchargingstationsapp.ui.detailsactivity.circularButton
import com.example.evchargingstationsapp.ui.detailsactivity.circularTopButton
import com.example.evchargingstationsapp.ui.detailsactivity.intentCard
import com.example.evchargingstationsapp.ui.detailsactivity.openingHours
import com.example.evchargingstationsapp.ui.detailsactivity.sectionButton
import com.example.evchargingstationsapp.ui.detailsactivity.stationImage
import com.example.evchargingstationsapp.ui.detailsactivity.stationNameTitle


class DetailsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        setContent {
            var context = this
            App(context)


        }
    }
}





















@SuppressLint("UnrememberedMutableState")
@Composable
fun App(
    activityContext:DetailsActivity

){

    var spotsAvailable = max((0..9).random(), 1)
    var totalSpots = (spotsAvailable..10).random()

    var stationImage by remember {mutableStateOf("") }
    var stationName by remember {mutableStateOf("") }
    var stationRating by remember {mutableStateOf(5) }
    var stationOpeningHours by remember {mutableStateOf("") }
    var stationIsOpen by remember {mutableStateOf(false) }
    var stationAddress by remember {mutableStateOf("") }
    var stationMapsLink by remember {mutableStateOf("") }
    var stationWebsite by remember {mutableStateOf("") }
    var stationPlaceId by remember {mutableStateOf("") }






    try {
        var stationJsonObject = JSONObject(activityContext.intent.getStringExtra("jsonObject"))
        //var stationJsonObject = JSONObject(jsonObject)
        stationPlaceId = stationJsonObject.getString("google_place_id")
        stationName = stationJsonObject.getString("name")
        stationImage = stationJsonObject.getString("photo")
        if(stationImage.contains("streetview")){
            stationImage = stationJsonObject.getString("photo").replace("&w=86&h=86&","&w=1080&h=1920&")
        }


        if (stationJsonObject.has("rating") && !stationJsonObject.isNull("rating")) {
            stationRating = stationJsonObject.getInt("rating")

            // Do something with object.

        }else{
            stationRating = 5
        }


        val calendar = Calendar.getInstance()
        val date: Date = calendar.time
        var todaysDayOfTheWeek =  SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime())



        var openingHoursObject = stationJsonObject.getJSONObject("opening_hours")

        if(openingHoursObject.toString() != "{}"){
            if(openingHoursObject.has(todaysDayOfTheWeek) && !openingHoursObject.isNull(todaysDayOfTheWeek)){
                if(openingHoursObject.getJSONArray(todaysDayOfTheWeek).getString(0) == "Open 24 hours"){
                    stationOpeningHours = "24h/24"
                    stationIsOpen = true
                }else{
                    stationOpeningHours = openingHoursObject.getJSONArray(todaysDayOfTheWeek).getString(0)
                    stationIsOpen = true

                }

            }else{
                stationOpeningHours = ""
                stationIsOpen = false
            }


        }else{
            stationOpeningHours = ""
            stationIsOpen = false
        }

        var stationAddressObject = stationJsonObject.getJSONObject("address_components")

        stationAddress = stationAddressObject.getString("street_address") + ", " + stationAddressObject.getString("city")

        stationMapsLink = stationJsonObject.getString("place_link")

        if (stationJsonObject.has("website") && !stationJsonObject.isNull("website")) {
            stationWebsite = stationJsonObject.getString("website")

            // Do something with object.

        }else{
            //stationRating = 5
        }







    } catch (e: JSONException) {
       e.printStackTrace()
    }

    //392/300 = 1.306 = px Photoshop (cropped.png) -> dp Android
    //60/13.5 = 0.225 = dp Android -> sp android





    Box(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        ,contentAlignment = Alignment.TopCenter
    ){
        SideEffect {
                Log.i("Composition suiiiiii", "Example fun")
               }
        stationImage(stationImage)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp)
                .height(45.dp)
                .zIndex(2f),
            horizontalArrangement = Arrangement.spacedBy(280.dp, Alignment.CenterHorizontally),
        ) {
            circularTopButton(icon=R.drawable.crossicon, size=15.dp, onClick = {

                activityContext.finish()
            })
            circularTopButton(icon=R.drawable.shareicon, size=25.dp, onClick = {
                val intent= Intent()
                intent.action=Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT,"Check the $stationName : $stationMapsLink")
                intent.type="text/plain"
                activityContext.startActivity(Intent.createChooser(intent,"Share To :"))

            })

        }
        Column(
            modifier = Modifier
                .padding(top = 360.dp)
                .shadow(25.dp, RoundedCornerShape(25.dp))
                .fillMaxWidth()
                .height(530.dp)
                .clip(RoundedCornerShape(25.dp, 25.dp, 0.dp, 0.dp))
                .zIndex(2f)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally


        ){
            Row(
                modifier= Modifier
                    .fillMaxWidth(0.88f)
                    .height(60.dp)
                    .padding(top = 30.dp),

            ){
                stationNameTitle(stationName = stationName)

                Spacer(modifier = Modifier.weight(1f))

                Column(
                    modifier = Modifier
                        .padding(end = 0.dp)
                        .height((27.5).dp)
                        .offset(y = (-4.5).dp)
                        .offset(x = (5.dp))
                        .clip(RoundedCornerShape((10).dp, (10).dp, (10).dp, (10).dp))
                        .background(Color.Black)

                    ,

                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center

                ){


                    Text(
                        text = "$spotsAvailable/$totalSpots spots left".uppercase(Locale.getDefault()),
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .padding(end = 10.dp)
                            .offset(y = 0.dp),
                        style = TextStyle(
                            fontFamily = FontFamily(
                                Font(R.font.janabold)
                            ),
                            color = Color.White,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )

                }



            }

            Row(
                modifier= Modifier
                    .fillMaxWidth(0.88f)
                    .height(26.dp)
                    .padding(top = 5.dp)
                    //.offset(y= (-5).dp)
                ,
                verticalAlignment = Alignment.CenterVertically
            ){
                RatingBarView(
                    rating = stationRating

                )




            }

            openingHours(isOpen = stationIsOpen, hours = stationOpeningHours)



            Row(
                modifier= Modifier
                    .padding(top = 25.dp)
                    .fillMaxWidth(0.88f)
                    .height(85.dp),
                horizontalArrangement = Arrangement.spacedBy(26.dp, Alignment.CenterHorizontally)
            ){
                circularButton(
                    icon= R.drawable.starticon, "Start", onClick = {


                        var url = ("https://www.google.com/maps/dir/?api=1&origin=&destination=${stationName.replace(" ", "+")}&destination_place_id=$stationPlaceId").toUri()


                        //Log.i("HEEEEYYYYYYYYYYYYYYY", url.toString())
                        activityContext.startActivity(Intent(Intent.ACTION_VIEW).apply {
                            data = url})






                    }
                )
                circularButton(
                    icon=R.drawable.directionsicon, "Directions", onClick = {
                        activityContext.startActivity(Intent(Intent.ACTION_VIEW).apply {
                            data = stationMapsLink.toUri()
                        })


                    }
                )
                circularButton(
                    icon=R.drawable.reserveicon, "Reserve", onClick = {}
                )
                circularButton(
                    icon=R.drawable.saveicon, "Save", onClick = {}
                )





            }

            Row(
                modifier= Modifier
                    .padding(top = 25.dp)
                    .fillMaxWidth(0.95f)
                    .height(36.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterHorizontally)
            ){
                sectionButton(sectionName = "Overview" , isSelected = true)
                sectionButton(sectionName = "Reviews" , isSelected = false)
                sectionButton(sectionName = "Photos" , isSelected = false)



            }

            Divider(//)
                thickness = (0.1).dp,
                modifier = Modifier
                    .padding(top = 0.dp)
                    //fill the max height
                    .fillMaxWidth(0.88f),
                color = Color(0XFF1b1b1b)

            )

            Column(
                modifier= Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(top = 15.dp)

            ) {
                intentCard(intentText = stationAddress, intentIcon = R.drawable.map_marker, onClick = {
                    //use stationMapsLink to open maps
                    activityContext.startActivity(Intent(Intent.ACTION_VIEW).apply {
                        data = stationMapsLink.toUri()
                    })

                })
                intentCard(intentText = "(+33) 1-24-58-52-18", intentIcon = R.drawable.phonecallicon, onClick = {


                })
                intentCard(intentText = stationWebsite
                    .replace("https://www.", "www.")
                    .replace("http://www", "www.")
                    .replace("https://", "www.")
                    .replace("http://", "www."),
                    intentIcon = R.drawable.websiteicon, onClick = {
                    //do someting with stationWebsite
                        activityContext.startActivity(Intent(Intent.ACTION_VIEW).apply {
                            data = stationWebsite.toUri()
                        })

                })




            }





        }



    }
}
