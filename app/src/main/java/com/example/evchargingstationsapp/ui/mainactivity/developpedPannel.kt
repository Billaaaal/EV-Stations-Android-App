package com.example.evchargingstationsapp.ui.mainactivity

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.evchargingstationsapp.Marker_
import com.example.evchargingstationsapp.R
import com.google.android.gms.maps.model.LatLng
import org.json.JSONObject


@ExperimentalMaterial3Api
@Composable
fun developpedPannel(
    activityContext: Context = LocalContext.current,
    onMinimize: () -> Unit,
    developpedPannelShown: Boolean,
    focusRequester : FocusRequester,
    onSearchKeyboard:(text:String) -> Unit,

                         //updateChargingStationsWithQuery(text)

    chargingStationsSearchResultsList: SnapshotStateList<Marker_>,
    onStationCardClick: (jsonObject:JSONObject) -> Unit,
                        // switchToDetailsActivity(jsonObject)


    ) {


    LaunchedEffect(developpedPannelShown){
        if (developpedPannelShown){
            focusRequester.requestFocus()


        }

    }
    var focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .padding(top = 145.dp)
            .shadow(35.dp, RoundedCornerShape(35.dp))
            .width(392.dp)
            .height(730.dp)
            .clip(RoundedCornerShape(35.dp, 35.dp, 0.dp, 0.dp))
            .zIndex(3f)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally


    ){

        Row(modifier = Modifier
            .padding(top = 20.dp)
            .height(50.dp)
            .width(345.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0XFFf3f3f5)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start)


        {





            var text by remember { mutableStateOf(TextFieldValue("")) }

            IconButton(modifier = Modifier
                .height(50.dp)
                .width(50.dp)
                .padding(start = 10.dp),
                onClick = {

                    onMinimize()

                }
            ){
                Icon(
                    painterResource(id = R.drawable.reducearrow),
                    contentDescription = "content description", tint= Color(0xFF000000),
                    modifier = Modifier
                        .size(20.dp)
                )



            }


            BasicTextField(

                modifier = Modifier
                    .focusRequester(focusRequester)
                    .width(250.dp)
                    .padding(start = 10.dp),
                textStyle = TextStyle(
                    color = Color(0xFF000000), //Color(0XFF0273f2)
                    fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraBold
                ),

                value = text,
                onValueChange = {
                    text = it

                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Search),


                keyboardActions = KeyboardActions(onSearch = {
                    focusManager.clearFocus()
                    onSearchKeyboard(text.text)


                    //Toast.makeText(activityContext, text.text, Toast.LENGTH_SHORT).show()
                }),


                singleLine = true,

                decorationBox = { innerTextField ->
                    if(text.text.isEmpty()){
                        Text(
                            text = "Find a charging station",
                            modifier = Modifier
                                .align(Alignment.CenterVertically),
                            style = TextStyle(
                                color = Color(0XFF9d9ea2), //Color(0XFF0273f2)
                                fontSize = 16.sp,
                                fontWeight = FontWeight.ExtraBold
                            ),
                        )

                    }


                    innerTextField()
                }
            )


            //Image

            Divider(
                color = Color(0xFFcdcdcd), //0XFF1b1b1b)
                thickness = 1.dp,
                modifier = Modifier
                    .height(30.dp)  //fill the max height
                    .width(1.dp)
            )
            Image(
                painterResource(id = R.drawable.microphone) ,
                contentDescription = "content description",
                modifier = Modifier
                    .padding(start = 10.dp)
                    .size((20).dp)
            )




        }

        LazyColumn(modifier= Modifier
            .padding(top = 20.dp)
            .width(360.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            chargingStationsSearchResultsList.forEachIndexed { index, (name, coordinates, address,  photo, jsonObject, isSelected) ->

                item {

                    stationCardSearchResult(
                        name = name,
                        address = address,
                        photo = photo,
                        onClick = {
                            onStationCardClick(jsonObject)

                        }
                    )
                    Divider(
                        color = Color(0xFFf5f5f6), //0XFF1b1b1b)
                        thickness = 1.dp,
                        modifier = Modifier
                            .height(1.dp)  //fill the max height
                            .fillMaxWidth(0.8f)
                    )







                }














            }


        }

    }


}