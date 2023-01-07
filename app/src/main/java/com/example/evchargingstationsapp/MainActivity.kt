@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class
)

package com.example.evchargingstationsapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import coil.compose.rememberAsyncImagePainter
import com.example.evchargingstationsapp.*
import com.example.evchargingstationsapp.A.Companion.Mapstyle
import com.example.evchargingstationsapp.methods.bitmapDescriptorFromVector
import com.example.evchargingstationsapp.methods.switchToDetailsActivity
import com.example.evchargingstationsapp.ui.mainactivity.bottomNavBar
import com.example.evchargingstationsapp.ui.mainactivity.developpedPannel
import com.example.evchargingstationsapp.ui.mainactivity.stationCard
import com.example.evchargingstationsapp.ui.mainactivity.stationCardSearchResult
import com.example.evchargingstationsapp.ui.mainactivity.suggestionsButtons
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.maps.android.PolyUtil
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import java.util.Locale
import java.util.Timer
import java.util.TimerTask
import javax.security.auth.callback.Callback
import kotlin.concurrent.timerTask
import kotlin.math.max
import kotlin.math.min


class MainActivity : ComponentActivity() {
    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        setContent {

            //this.startActivity(Intent(activityContext, DetailsActivity::class.java))

            App(this, LocalContext.current)
        }

    }
}

data class Marker_(
    val name:String,
    val coordinates:LatLng,
    val address:String,
    val photo:Any,
    val jsonObject:JSONObject,
    val isSelected:Boolean
)









///////////////




@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnrememberedMutableState", "CoroutineCreationDuringComposition")
@Composable
fun App(activityContext:MainActivity, currentContext:Context){

    var startingPoint = LatLng(48.84143564459751, 2.2530797322480685)

    var cameraPosition = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(startingPoint, 10f)
    }


    data class LatLngSelfCreated(
        val lat:Float,
        val lon:Float,
    )






    val listOfLocation = remember { mutableStateListOf<LatLngSelfCreated>()}

    var developpedPannelShown by remember {mutableStateOf(false) }

    var currentlyScrollingToItem by remember {mutableStateOf(false) }

    val carouselState = rememberLazyListState()

    val listOfStations = remember { mutableStateListOf<Marker_>()}

    val chargingStationsSearchResultsList = remember { mutableStateListOf<Marker_>()}


    var rescanThisAreaButtonShown by remember {mutableStateOf(false) }

    var stationsCarouselShown by remember {mutableStateOf(false) }

    var localisationButtonPadding by remember {mutableStateOf(535.dp) }

    val coroutineScope = rememberCoroutineScope()

    val snappingLayout = remember(carouselState) { SnapLayoutInfoProvider(carouselState)}
    val flingBehavior = rememberSnapFlingBehavior(snappingLayout)

    val focusRequester = remember { FocusRequester() }












    @SuppressLint("CoroutineCreationDuringComposition")
    fun getLocation(cameraChangeAnimDuration:Int){

        val fusedLocation = LocationServices.getFusedLocationProviderClient(activityContext)

        val locationRequest = LocationRequest.create();
        locationRequest.setInterval(5*1000);

        var locationCallback = object : LocationCallback() {
            fun onLocationResulta(locationResult: LocationResult?) {
                locationResult ?: return
                for (location in locationResult.locations){
                    // Update UI with location data
                    // ...
                }
            }
        }










        if (ActivityCompat.checkSelfPermission(activityContext, android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(activityContext, android.Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activityContext,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 100)

        }else{
            fusedLocation.requestLocationUpdates(locationRequest,
                locationCallback,
                Looper.getMainLooper())
        }

        val location = fusedLocation.lastLocation




        //get latitude and longitude

        location.addOnSuccessListener {
            if(it!=null){


                val uiScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

                fun runOnUiThread(block: suspend () -> Unit) = uiScope.launch { block() }

                runOnUiThread {
                    //Toast.makeText(activityContext, "Location found and updating marker i guess", Toast.LENGTH_SHORT).show()

                    //listOfLocation.clear()




                    if(cameraChangeAnimDuration==1){
                        listOfLocation.add(LatLngSelfCreated(
                            it.latitude.toFloat(), it.longitude.toFloat()
                        ))

                    }else{
                        //listOfLocation.set(1, LatLngSelfCreated(

                        listOfLocation.clear()
                        Timer().schedule(timerTask {
                            // listOfStations.add(Marker(cameraPosition.position.target.latitude.toString(), LatLng(cameraPosition.position.target.latitude+0.01, cameraPosition.position.target.longitude+0.01)))
                            //listOfStations.add(Marker(cameraPosition.position.target.latitude.toString(), LatLng(cameraPosition.position.target.latitude, cameraPosition.position.target.longitude)))
                            //change it to a single one, not a list
                            listOfLocation.add(LatLngSelfCreated(
                                it.latitude.toFloat(), it.longitude.toFloat()
                            ))
                        }, 100)


                    }

                    //why isn't it updating the marker ?
                }

                if(cameraChangeAnimDuration==1){
                    Timer().scheduleAtFixedRate( object : TimerTask() {
                        override fun run() {
                            getLocation(20)
                            //getLocation(20)

                        }
                    }, 0, 10*1000)

                }

                if(cameraChangeAnimDuration==20){

                }else{
                    GlobalScope.launch (Dispatchers.Main) {

                        cameraPosition.animate(
                            update = CameraUpdateFactory.newCameraPosition(
                                CameraPosition(LatLng(
                                    it.latitude, it.longitude
                                ), 15f, 0f, 0f)
                            ),
                            durationMs = cameraChangeAnimDuration
                        )

                    }

                }

                if(cameraChangeAnimDuration==401){
                    GlobalScope.launch (Dispatchers.Main) {

                        cameraPosition.animate(
                            update = CameraUpdateFactory.newCameraPosition(
                                CameraPosition(LatLng(
                                    it.latitude, it.longitude
                                ), 13f, 0f, 0f)
                            ),
                            durationMs = cameraChangeAnimDuration
                        )

                        Handler(Looper.getMainLooper()).postDelayed({
                            rescanThisAreaButtonShown = false
                        }, cameraChangeAnimDuration.toLong())



                    }

                }












            }
        }







    }
    var ranLocation by remember {
        mutableStateOf(false)
    }



    if(ranLocation==false){
        getLocation(1)
        ranLocation = true

    }else{


    }



    fun updateChargingStationsWithCurrentCameraPosition(polyList: MutableList<LatLng>, numberOfStations:Int, lat:Float, lon:Float){





        val client = OkHttpClient()

        val mediaType = "application/json".toMediaTypeOrNull()

        var body = RequestBody.create(
            mediaType,
            ""
        )





        val request = Request.Builder()
            //.url("https://listen-api.listennotes.com/api/v2/podcasts/" + id_ + "?sort=recent_first")
            .url("https://ev-charging-stations-search.p.rapidapi.com/search-by-coordinates-point?lat=$lat&lng=$lon&limit=$numberOfStations")
            .method("GET", null)
            .addHeader("X-RapidAPI-Key", "YOUR API KEY")
            .addHeader("X-RapidAPI-Host", "ev-charging-stations-search.p.rapidapi.com")
            //.header("X-ListenAPI-Key", "c7a88e0f1a17445bb4f14b4212fa161f")
            //.header("Accept", "application/json")
            .build()

        var response__ = ""

        client.newCall(request).enqueue(object : Callback, okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                e.printStackTrace()
            }


            override fun onResponse(call: okhttp3.Call, response_: Response) {
                response_.use {

                    var response_ = response_.body!!.string()

                    val gson_ = GsonBuilder().setPrettyPrinting().create()
                    var prettyJson_ = gson_.toJson(
                        JsonParser.parseString(
                            response_ // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
                        )
                    )



                    var obj_ = JSONObject(prettyJson_)

                    var data = obj_.getJSONArray("data")

                    val uiScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

                    fun runOnUiThread(block: suspend () -> Unit) = uiScope.launch { block() }









                    for (i in 0 until data.length()-1) {
                        //Log.i("DIDREACHED", i.toString())

                        var currentStationObject = data.getJSONObject(i)

                        var stationName = currentStationObject.getString("name")

                        var stationLatitude = currentStationObject.getString("latitude")

                        var stationLongitude = currentStationObject.getString("longitude")

                        var stationAddress = currentStationObject.getString("formatted_address")

                        var stationPhoto = "https://lh5.googleusercontent.com/p/AF1QipORJvDq5ChaxGO2pjn2LJ9L_8B2UMusI6KjfezY=w3024-h4032-k-no"

                        if (currentStationObject.has("photo") && !currentStationObject.isNull("photo")) {

                            // Do something with object.
                            stationPhoto = currentStationObject.getString("photo")
                        }






                        fun test() {
                            runOnUiThread {

                                if(i == max(0, data.length()-2)){


                                    Log.i("haaa", "reached bottom")
                                    localisationButtonPadding = 435.dp
                                    stationsCarouselShown = true
                                }






                                //listOfStations.add(Marker("farLeft", LatLng(cameraPosition.projection?.visibleRegion?.farLeft!!.latitude,
                                //    cameraPosition.projection?.visibleRegion?.farLeft!!.longitude)))
                                //listOfStations.add(Marker("farRight", LatLng(cameraPosition.projection?.visibleRegion?.farRight!!.latitude,
                                //    cameraPosition.projection?.visibleRegion?.farRight!!.longitude)))
                                //listOfStations.add(Marker("nearLeft", LatLng(cameraPosition.projection?.visibleRegion?.nearLeft!!.latitude,
                                    //    cameraPosition.projection?.visibleRegion?.nearLeft!!.longitude)))
                                //listOfStations.add(Marker("nearRight", LatLng(cameraPosition.projection?.visibleRegion?.nearRight!!.latitude,
                                //    cameraPosition.projection?.visibleRegion?.nearRight!!.longitude)))

                                if(PolyUtil.containsLocation(LatLng(stationLatitude.toDouble(), stationLongitude.toDouble()), polyList, true)){
                                    listOfStations.add(Marker_(stationName, LatLng(stationLatitude.toDouble(), stationLongitude.toDouble()), stationAddress, stationPhoto, currentStationObject,false))


                                }else{
                                   // listOfStations.add(Marker("outside", LatLng(stationLatitude.toDouble(), stationLongitude.toDouble())))


                                }
                            }
                        }

                        test()



                        //Log.i("YAAAAAAAY", station.toString())


                    }




                }
            }
        })







    }


    fun updateChargingStationsWithQuery(locationToSearchNear:String){





        val client = OkHttpClient()

        val mediaType = "application/json".toMediaTypeOrNull()

        var body = RequestBody.create(
            mediaType,
            ""
        )

        var url = refactorQuery(locationToSearchNear)





        val request = Request.Builder()
            //.url("https://listen-api.listennotes.com/api/v2/podcasts/" + id_ + "?sort=recent_first")
            .url(url)
            .method("GET", null)
            .addHeader("X-RapidAPI-Key", "YOUR API KEY")
            .addHeader("X-RapidAPI-Host", "ev-charging-stations-search.p.rapidapi.com")
            //.header("X-ListenAPI-Key", "c7a88e0f1a17445bb4f14b4212fa161f")
            //.header("Accept", "application/json")
            .build()

        var response__ = ""

        client.newCall(request).enqueue(object : Callback, okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                e.printStackTrace()
            }


            override fun onResponse(call: okhttp3.Call, response_: Response) {
                response_.use {

                    var response_ = response_.body!!.string()

                    val gson_ = GsonBuilder().setPrettyPrinting().create()
                    var prettyJson_ = gson_.toJson(
                        JsonParser.parseString(
                            response_ // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
                        )
                    )



                    var obj_ = JSONObject(prettyJson_)

                    var data = obj_.getJSONArray("data")

                    val uiScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

                    fun runOnUiThread(block: suspend () -> Unit) = uiScope.launch { block() }

                    chargingStationsSearchResultsList.clear()









                    for (i in 0 until data.length()-1) {
                        //Log.i("DIDREACHED", i.toString())

                        var currentStationObject = data.getJSONObject(i)

                        var stationName = currentStationObject.getString("name")

                        var stationLatitude = currentStationObject.getString("latitude")

                        var stationLongitude = currentStationObject.getString("longitude")

                        var stationAddress = currentStationObject.getString("formatted_address")

                        var stationPhoto = "https://lh5.googleusercontent.com/p/AF1QipORJvDq5ChaxGO2pjn2LJ9L_8B2UMusI6KjfezY=w3024-h4032-k-no"

                        if (currentStationObject.has("photo") && !currentStationObject.isNull("photo")) {

                            // Do something with object.
                            stationPhoto = currentStationObject.getString("photo")
                        }






                        fun test() {
                            runOnUiThread {








                                //listOfStations.add(Marker("farLeft", LatLng(cameraPosition.projection?.visibleRegion?.farLeft!!.latitude,
                                //    cameraPosition.projection?.visibleRegion?.farLeft!!.longitude)))
                                //listOfStations.add(Marker("farRight", LatLng(cameraPosition.projection?.visibleRegion?.farRight!!.latitude,
                                //    cameraPosition.projection?.visibleRegion?.farRight!!.longitude)))
                                //listOfStations.add(Marker("nearLeft", LatLng(cameraPosition.projection?.visibleRegion?.nearLeft!!.latitude,
                                //    cameraPosition.projection?.visibleRegion?.nearLeft!!.longitude)))
                                //listOfStations.add(Marker("nearRight", LatLng(cameraPosition.projection?.visibleRegion?.nearRight!!.latitude,
                                //    cameraPosition.projection?.visibleRegion?.nearRight!!.longitude)))



                                chargingStationsSearchResultsList.add(Marker_(stationName, LatLng(stationLatitude.toDouble(), stationLongitude.toDouble()),
                                    stationAddress, stationPhoto, currentStationObject,false))



                            }
                        }

                        test()



                        //Log.i("YAAAAAAAY", station.toString())


                    }


                }
            }
        })







    }



    var locationEnabled = false







    var properties =  mutableStateOf(MapProperties(mapStyleOptions = MapStyleOptions(Mapstyle)))//,  isMyLocationEnabled = locationEnabled))

    if (cameraPosition.isMoving){
        rescanThisAreaButtonShown = true
        //Log.i("zooooooom", cameraPosition.position.zoom.toString())



    }

    fun buttonTriggersUpdateChargingStations(){
        // listOfStations.clear()

        rescanThisAreaButtonShown = false
        stationsCarouselShown = false
        localisationButtonPadding = 535.dp


        var numberOfStations = 0
        var handlerDuration = 0



        if(cameraPosition.position.zoom<13){
            numberOfStations = 500
            //problematic
            GlobalScope.launch (Dispatchers.Main) {
                cameraPosition.animate(
                    update = CameraUpdateFactory.newCameraPosition(
                        CameraPosition(LatLng(cameraPosition.position.target.latitude, cameraPosition.position.target.longitude), 13f, 0f, 0f)
                    ),
                    durationMs = 200
                )

            }
            handlerDuration = 250
        }else{
            numberOfStations = 200
            handlerDuration = 0

        }
        Handler(Looper.getMainLooper()).postDelayed({
            val polygonPts: MutableList<LatLng> = ArrayList()
            polygonPts.add(LatLng(
                cameraPosition.projection?.visibleRegion?.farLeft!!.latitude,
                cameraPosition.projection?.visibleRegion?.farLeft!!.longitude
            ))
            polygonPts.add(LatLng(
                cameraPosition.projection?.visibleRegion?.farRight!!.latitude,
                cameraPosition.projection?.visibleRegion?.farRight!!.longitude
            ))
            polygonPts.add(LatLng(
                cameraPosition.projection?.visibleRegion?.nearRight!!.latitude,
                cameraPosition.projection?.visibleRegion?.nearRight!!.longitude
            ))
            polygonPts.add(LatLng(
                cameraPosition.projection?.visibleRegion?.nearLeft!!.latitude,
                cameraPosition.projection?.visibleRegion?.nearLeft!!.longitude
            ))



            if (listOfStations.isNotEmpty()){
                listOfStations.clear()
                Timer().schedule(timerTask {
                    // listOfStations.add(Marker(cameraPosition.position.target.latitude.toString(), LatLng(cameraPosition.position.target.latitude+0.01, cameraPosition.position.target.longitude+0.01)))
                    //listOfStations.add(Marker(cameraPosition.position.target.latitude.toString(), LatLng(cameraPosition.position.target.latitude, cameraPosition.position.target.longitude)))
                    updateChargingStationsWithCurrentCameraPosition(polygonPts, numberOfStations, cameraPosition.position.target.latitude.toFloat(), cameraPosition.position.target.longitude.toFloat())

                }, 5)
                //updateChargingStations(cameraPosition.position.target.latitude.toFloat(), cameraPosition.position.target.longitude.toFloat())
                //listOfStations.add(Marker(cameraPosition.position.target.latitude.toString(), LatLng(cameraPosition.position.target.latitude+0.001, cameraPosition.position.target.longitude)))
            }else{
                updateChargingStationsWithCurrentCameraPosition(polygonPts, numberOfStations, cameraPosition.position.target.latitude.toFloat(), cameraPosition.position.target.longitude.toFloat())

                // listOfStations.add(Marker(cameraPosition.position.target.latitude.toString()+0.01, LatLng(cameraPosition.position.target.latitude, cameraPosition.position.target.longitude+0.01)))
                // listOfStations.add(Marker(cameraPosition.position.target.latitude.toString(), LatLng(cameraPosition.position.target.latitude, cameraPosition.position.target.longitude)))

            }

            rescanThisAreaButtonShown = false






            //listOfStations.add(Marker("It works !",LatLng(cameraPosition.position.target.latitude, cameraPosition.position.target.longitude)))
            //Toast.makeText(this@AnimatedVisibility,cameraPosition.position.target.latitude.toString(),Toast.LENGTH_LONG).show();
            //Log.i("What", cameraPosition.position.target.latitude.toString())
            //Log.i("The position", cameraPosition.position.target.latitude.toString())

            //Your code
        }, handlerDuration.toLong()) //millis
    }
    Box(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        ,contentAlignment = Alignment.TopCenter
    ){

        Column(
            modifier = Modifier
                .padding(top = 640.dp)
                .shadow(35.dp, RoundedCornerShape(35.dp))
                .width(392.dp)
                .height(340.dp)
                .clip(RoundedCornerShape(35.dp, 35.dp, 35.dp, 35.dp))
                .zIndex(2f)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally


        ){
            Button(modifier = Modifier

                .padding(top = 20.dp)
                .zIndex(2f)
                .height(50.dp)
                .width(345.dp),

                contentPadding = PaddingValues(0.dp), ////////////VERY IMPORTANT

                shape = RoundedCornerShape(10.dp),


                colors = ButtonDefaults.buttonColors(containerColor = Color(0XFFf0eff2)),
                onClick = {
                    developpedPannelShown = true

                    rescanThisAreaButtonShown = false





                }
            ){
                Row(modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                , verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start)
                {


                    Image(painterResource(id = R.drawable.profilepictureicon) ,
                        contentDescription = "content description",
                        modifier = Modifier
                            .size((45).dp)
                            .padding(start = 10.dp)
                    )
                    //Image
                    Text(
                        text = "Planning a trip ?",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 15.dp),
                        style = TextStyle(
                            color = Color(0XFF9d9ea2), //Color(0XFF0273f2)
                            fontSize = 16.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                    Divider(
                        color = Color(0xFFcdcdcd), //0XFF1b1b1b)
                        thickness = 1.dp,
                        modifier = Modifier
                            .padding(start = 120.dp)
                            .height(30.dp)  //fill the max height
                            .width(1.dp)
                    )
                    Image(painterResource(id = R.drawable.microphone) ,
                        contentDescription = "content description",
                        modifier = Modifier
                            .size((30).dp)
                            .padding(start = 15.dp)
                    )




                }




            }
            //developpedPannelShown = true

            LazyRow(modifier= Modifier
                .height(50.dp)
                .padding(top = 20.dp)
                .padding(start = 20.dp)
                .align(Alignment.Start)
                .width(370.dp)
                ,
            verticalAlignment = Alignment.CenterVertically){
                items(count = 1) {
                    suggestionsButtons("Charging stations near me",
                        Color(0XFFefe0ff),
                        Color(0XFF441170),
                        onClick = {

                            getLocation(401)

                            Handler(Looper.getMainLooper()).postDelayed({
                                buttonTriggersUpdateChargingStations()
                                rescanThisAreaButtonShown = false
                            }, 1200)
                        }
                    )

                    suggestionsButtons("Plan a trip",
                        Color(0XFFfde1ef),
                        Color(0XFF73174b),
                        onClick = {

                        Toast.makeText(activityContext, "Hi !",
                            Toast.LENGTH_SHORT).show()




                    })

                    suggestionsButtons(
                        "Search for a place",
                        Color(0XFFabcafa),
                        Color(0XFF00040f),
                        onClick = {
                            developpedPannelShown = true


                    })



                }

            }




            Spacer(modifier = Modifier.weight(1f))

            bottomNavBar()


        }



        AnimatedVisibility(
            modifier = Modifier
                .zIndex(4f),
            visible = developpedPannelShown,
            enter = slideInVertically(
                initialOffsetY = { fullHeight -> fullHeight },
                animationSpec = tween(
                    durationMillis = 500,
                    easing = LinearOutSlowInEasing
                )
            ),
            exit = slideOutVertically(
                targetOffsetY = { fullHeight -> fullHeight },
                animationSpec = tween(
                    durationMillis = 500,
                    easing = LinearOutSlowInEasing
                )
            )
        ) {
            developpedPannel(
                activityContext,
                onMinimize = {
                    developpedPannelShown = false
                },
                onSearchKeyboard = { query->
                    //developpedPannelShown = false

                    updateChargingStationsWithQuery(query)
                },
                focusRequester = focusRequester,

                chargingStationsSearchResultsList = chargingStationsSearchResultsList,
                developpedPannelShown = developpedPannelShown,
                onStationCardClick = {jsonObject ->
                    switchToDetailsActivity(jsonObject, activityContext)
                }

            )

        }




        Column(modifier = Modifier
            .fillMaxWidth()
            .height(720.dp)
            .zIndex(1f)
            .background(Color.Black)
        ) {
            GoogleMap(
                modifier = Modifier
                    .zIndex(1f),

                cameraPositionState = cameraPosition,
                properties = properties.value,


                ){



                listOfLocation.forEach {(lat, lng) ->
                    Marker(
                        state = rememberMarkerState(
                            position = LatLng(lat.toDouble(), lng.toDouble())
                        ),
                        title = "Your current location",
                        icon = bitmapDescriptorFromVector(
                            LocalContext.current,
                            R.drawable.locationmarker,
                            90
                        )
                    )


                }











                listOfStations.forEachIndexed { index, (name, coordinates, address,  photo, jsonObject, isSelected) ->


                    var stateIcon:Int

                    var size = 100

                    if (isSelected){
                        stateIcon = R.drawable.stationmarkerselected
                        size = 120


                    }else{
                        stateIcon = R.drawable.stationmarker

                    }



                    Marker(
                        state = rememberMarkerState(position = coordinates),
                        title = name,
                        icon = bitmapDescriptorFromVector(
                            LocalContext.current,
                            stateIcon,
                            size
                        ),
                        //add on click listener

                        onClick= {
                            currentlyScrollingToItem = true
                            //Toast.makeText(activityContext, "You clicked on the $index th item", Toast.LENGTH_SHORT).show()
                            coroutineScope.launch {
                                // Animate scroll to the 10th item
                                carouselState.animateScrollToItem(index = index)
                                currentlyScrollingToItem = false
                                if(listOfStations.size >= 1){

                                    for (i in 0 until listOfStations.size-1){
                                        if(listOfStations.elementAtOrNull(i)!!.isSelected == true){
                                            listOfStations.set(i, Marker_(listOfStations.elementAtOrNull(i)!!.name, listOfStations.elementAtOrNull(i)!!!!.coordinates, listOfStations.elementAtOrNull(i)!!.address, listOfStations.elementAtOrNull(i)!!.photo, listOfStations.elementAtOrNull(i)!!.jsonObject,false))

                                        }else{

                                        }


                                    }



                                    //listOfStations.set(max(carouselState.firstVisibleItemIndex-1, 0), Marker_(listOfStations.elementAtOrNull(max(carouselState.firstVisibleItemIndex-1, 0))!!.name, listOfStations.elementAtOrNull(max(carouselState.firstVisibleItemIndex-1, 0))!!.coordinates, listOfStations.elementAtOrNull(max(carouselState.firstVisibleItemIndex-1, 0))!!.address, listOfStations.elementAtOrNull(max(carouselState.firstVisibleItemIndex-1, 0))!!.photo, false))
                                    //listOfStations.set(carouselState.firstVisibleItemIndex+1, Marker_(listOfStations.elementAtOrNull(min(carouselState.firstVisibleItemIndex+1, listOfStations.size-1))!!.name, listOfStations.elementAtOrNull(min(carouselState.firstVisibleItemIndex+1, listOfStations.size-1))!!.coordinates, listOfStations.elementAtOrNull(min(carouselState.firstVisibleItemIndex+1, listOfStations.size-1))!!.address, listOfStations.elementAtOrNull(min(carouselState.firstVisibleItemIndex+1, listOfStations.size-1))!!.photo, false))

                                    listOfStations.set(index, Marker_(listOfStations.elementAtOrNull(index)!!.name, listOfStations.elementAtOrNull(index)!!.coordinates, listOfStations.elementAtOrNull(index)!!.address, listOfStations.elementAtOrNull(index)!!.photo, listOfStations.elementAtOrNull(index)!!.jsonObject,true))



                                    cameraPosition.animate(
                                        update = CameraUpdateFactory.newCameraPosition(
                                            CameraPosition(listOfStations.elementAtOrNull(index)!!.coordinates, 13.5f, 0f, 0f)
                                        ),
                                        durationMs = 1
                                    )






                                }


                            }



                            //activityContext.startActivity(Intent(activityContext, DetailsActivity::class.java))


                            true

                        }

                    )



                    //if(name=="inside"){
                    //    Marker(
                    //       state = rememberMarkerState(position = coordinates),
                    //      title = "inside"
                    //    )

                    // }else{
                    //    Marker(
                            //        state = rememberMarkerState(position = coordinates),
                    //        title = "outside",
                    //        icon = icon
                    //    )

                    //}



                }





            }

        }

        Button(modifier = Modifier
            .padding(top = localisationButtonPadding)
            .offset(x = -150.dp)
            .zIndex(2f)
            .height(60.dp)
            .width(60.dp) ,
            contentPadding = PaddingValues(0.dp),

            shape = RoundedCornerShape(40.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp
            ),

            colors = ButtonDefaults.buttonColors(containerColor = Color.White),

            onClick = {

                getLocation(400)






            }
        ){
            Icon(
                painterResource(id = R.drawable.currentlocationicon) ,
                contentDescription = "content description", tint=Color(0XFF0273f2),
                modifier = Modifier
                    .size(22.dp))


        }

        AnimatedVisibility(
            modifier = Modifier
                .zIndex(3f),
            visible = stationsCarouselShown,
            enter = fadeIn(

            ),
            exit = fadeOut(
            )
        ) {

           //Log.i("HII", carouselState.firstVisibleItemIndex.toString())
            //Toast.makeText(activityContext, carouselState.firstVisibleItemIndex.toString(),
                //Toast.LENGTH_SHORT).show()

            Log.i("HII", carouselState.firstVisibleItemIndex.toString())

            LaunchedEffect(carouselState.firstVisibleItemIndex){

                if(currentlyScrollingToItem){


                }else{
                    if(listOfStations.size >= 1){



                        listOfStations.set(max(carouselState.firstVisibleItemIndex-1, 0), Marker_(listOfStations.elementAtOrNull(max(carouselState.firstVisibleItemIndex-1, 0))!!.name, listOfStations.elementAtOrNull(max(carouselState.firstVisibleItemIndex-1, 0))!!.coordinates, listOfStations.elementAtOrNull(max(carouselState.firstVisibleItemIndex-1, 0))!!.address, listOfStations.elementAtOrNull(max(carouselState.firstVisibleItemIndex-1, 0))!!.photo, listOfStations.elementAtOrNull(max(carouselState.firstVisibleItemIndex-1, 0))!!.jsonObject,false))
                        listOfStations.set(carouselState.firstVisibleItemIndex+1, Marker_(listOfStations.elementAtOrNull(min(carouselState.firstVisibleItemIndex+1, listOfStations.size-1))!!.name, listOfStations.elementAtOrNull(min(carouselState.firstVisibleItemIndex+1, listOfStations.size-1))!!.coordinates, listOfStations.elementAtOrNull(min(carouselState.firstVisibleItemIndex+1, listOfStations.size-1))!!.address, listOfStations.elementAtOrNull(min(carouselState.firstVisibleItemIndex+1, listOfStations.size-1))!!.photo, listOfStations.elementAtOrNull(min(carouselState.firstVisibleItemIndex+1, listOfStations.size-1))!!.jsonObject,false))

                        listOfStations.set(carouselState.firstVisibleItemIndex, Marker_(listOfStations.elementAtOrNull(carouselState.firstVisibleItemIndex)!!.name, listOfStations.elementAtOrNull(carouselState.firstVisibleItemIndex)!!.coordinates, listOfStations.elementAtOrNull(carouselState.firstVisibleItemIndex)!!.address, listOfStations.elementAtOrNull(carouselState.firstVisibleItemIndex)!!.photo,listOfStations.elementAtOrNull(carouselState.firstVisibleItemIndex)!!.jsonObject, true))

                        GlobalScope.launch (Dispatchers.Main) {

                            cameraPosition.animate(
                                update = CameraUpdateFactory.newCameraPosition(
                                    CameraPosition(listOfStations.elementAtOrNull(carouselState.firstVisibleItemIndex)!!.coordinates, 13.5f, 0f, 0f)
                                ),
                                durationMs = 1
                            )

                        }





                    }

                }





            }





            //listOfStations.add(Marker_(stationName, LatLng(stationLatitude.toDouble(), stationLongitude.toDouble()), stationAddress, stationPhoto, false))












            LazyRow(

                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier= Modifier
                    .width(380.dp)
                    .padding(top = 510.dp)
                    .padding(start = 20.dp)
                    .padding(end = 20.dp)

                    .height(110.dp)
               // .width(377.dp),
                ,
                verticalAlignment = Alignment.CenterVertically,
                state = carouselState,
                flingBehavior = flingBehavior
            ){
                listOfStations.forEach { (name, coordinates, address,  photo, jsonObject, isSelected) ->
                    //if(name=="inside"){
                    //    Marker(
                    //       state = rememberMarkerState(position = coordinates),
                    //      title = "inside"
                    //    )

                    // }else{
                    //    Marker(
                    //        state = rememberMarkerState(position = coordinates),
                    //        title = "outside",
                    //        icon = icon
                    //    )

                    //}

                    item {
                        stationCard(name, address, photo, onClick = {
                            switchToDetailsActivity(jsonObject, activityContext)
                        })
                    }





                }

            }



        }










        AnimatedVisibility(
            modifier = Modifier
                .zIndex(2f),
            visible = rescanThisAreaButtonShown,
            enter = fadeIn(

            ),
            exit = fadeOut(
            )
        ) {

            Button(modifier = Modifier

                .padding(top = 50.dp)
                .zIndex(2f)
                .height(35.dp)
                .width(185.dp),

                contentPadding = PaddingValues(0.dp), ////////////VERY IMPORTANT

                shape = RoundedCornerShape(40.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 10.dp
                ),


                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                onClick = {
                    buttonTriggersUpdateChargingStations()





                }
            ){
                Image(painterResource(id = R.drawable.flashicon) ,
                    contentDescription = "content description",
                    modifier = Modifier
                        .size((27.5).dp)
                        .offset(x = -(17.5).dp)
                )
                //Image
                Text(
                    text = "Rescan this area",
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    style = TextStyle(
                        color = Color(0XFF000000), //Color(0XFF0273f2)
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                )


            }



        }















    }



}


