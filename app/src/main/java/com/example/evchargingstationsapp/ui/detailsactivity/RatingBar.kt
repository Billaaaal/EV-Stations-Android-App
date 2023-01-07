package com.example.evchargingstationsapp.ui

import android.view.MotionEvent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.evchargingstationsapp.R


/**
 * Created by Mahmoud Alim on 22/01/2022.
 */

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RatingBarView(
    modifier: Modifier = Modifier.fillMaxHeight(),
    rating: Int = 0,
    isRatingEditable: Boolean = false,
    starSize: Dp = (26).dp,
    starsPadding: Dp = (5).dp,
    isViewAnimated: Boolean = true,
    numberOfStars: Int = 5,
    contentDescription: String = "rating",
    ratedStarsColor: Color = Color(255, 220, 0),
    unRatedStarsColor: Color = Color.LightGray,
) {

    var selected by remember {
        mutableStateOf(false)
    }
    val size by animateDpAsState(
        targetValue = if (selected) (starSize + 6.dp) else starSize,
        spring(Spring.DampingRatioLowBouncy),
    )

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        for (star in 1..numberOfStars) {
            Icon(
                painter = painterResource(id = R.drawable.staricon),
                contentDescription = contentDescription,
                modifier = Modifier
                    .size(if (isViewAnimated) size else starSize)
                    .padding(end = starsPadding)
                    .pointerInteropFilter {
                        when (isRatingEditable) {
                            true -> {
                                when (it.action) {
                                    MotionEvent.ACTION_DOWN -> {
                                        selected = true
                                        //rating = star
                                    }
                                    MotionEvent.ACTION_UP -> {
                                        selected = false
                                    }
                                    MotionEvent.ACTION_CANCEL -> {
                                        selected = false
                                    }
                                }
                            }
                            false -> {}
                        }
                        isRatingEditable
                    },

                tint = if (star <= rating) ratedStarsColor else unRatedStarsColor
            )
        }
    }
}