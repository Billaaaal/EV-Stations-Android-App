package com.example.evchargingstationsapp.methods

import android.content.Context
import android.graphics.Bitmap
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory

fun bitmapDescriptorFromVector(
    context: Context,
    vectorResId: Int,
    size:Int
): BitmapDescriptor? {



    // retrieve the actual drawable
    val drawable = ContextCompat.getDrawable(context, vectorResId) ?: return null
    drawable.setBounds(0, 0, size, size)
    val bm = Bitmap.createBitmap(
        size,
        size,
        Bitmap.Config.ARGB_8888
    )

    // draw it onto the bitmap
    val canvas = android.graphics.Canvas(bm)
    drawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bm)
}
