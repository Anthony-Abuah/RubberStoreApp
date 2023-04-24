package com.example.rubberstoreapp.feature_app.presentation.composables.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.rubberstoreapp.R

@Composable
fun CardImage(
    drawableVectorImage: Int,
    contentDescription: String
){
    Image(
        painter = painterResource(id = drawableVectorImage),
        contentDescription = contentDescription,
        modifier = Modifier
            .fillMaxSize()
    )
}

@Composable
fun HomeCardImage(
    drawableVectorImage: Int,
    contentDescription: String
){
    Image(
        painter = painterResource(id = drawableVectorImage),
        contentDescription = contentDescription,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.75f)
    )
}