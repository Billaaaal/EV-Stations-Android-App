package com.example.evchargingstationsapp.methods

import android.content.Intent
import android.util.Log
import com.example.evchargingstationsapp.DetailsActivity
import com.example.evchargingstationsapp.MainActivity
import org.json.JSONObject

fun switchToDetailsActivity(jsonObject: JSONObject, activityContext: MainActivity){




    val intent = Intent(activityContext, DetailsActivity::class.java)

    Log.i("Super important lol", jsonObject.toString())


    intent.putExtra("jsonObject", jsonObject.toString())
    activityContext.startActivity(intent)

    //to receive it : val receivedObject:MyPojo=intent?.getSerializableExtra(Obj) as MyPojo
}