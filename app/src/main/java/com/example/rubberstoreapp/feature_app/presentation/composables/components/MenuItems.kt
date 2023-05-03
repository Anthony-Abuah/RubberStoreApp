package com.example.rubberstoreapp.feature_app.presentation.composables.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.rubberstoreapp.feature_app.presentation.theme.TextFieldBackground

@Composable
fun MenuItem(
    modifier: Modifier,
    label: String,
    drawableResource: Int,
    contentDescription: String,
){
    Column(
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.75f)
                .background(Color.Transparent, shape = RoundedCornerShape(15.dp)),
            contentAlignment = Alignment.Center) {
            CardImage(drawableVectorImage = drawableResource, contentDescription = contentDescription)
        }
        Text(
            text = label,
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Light,
            style = MaterialTheme.typography.body2

        )
    }

}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeCard(
    navigate: () -> Unit,
    label: String,
    drawableResource: Int
) {
    Card(
        shape = RoundedCornerShape(0.dp),
        backgroundColor= TextFieldBackground,
        modifier = Modifier
            .padding(
                start = 1.dp,
                end = 1.dp,
                top = 1.dp,
                bottom = 1.dp
            )
            .fillMaxSize(),
        elevation = 3.dp
    ) {
        Card(
            shape = androidx.compose.material3.MaterialTheme.shapes.small,
            modifier = Modifier
                //.background(TextFieldBackground)
                .padding(3.dp)
                .fillMaxSize(),
            elevation = 2.dp,
            backgroundColor= TextFieldBackground,
            onClick = navigate
        ) {
            Column(modifier = Modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                HomeCardImage(drawableVectorImage = drawableResource, contentDescription = "")
                Spacer(modifier = Modifier.height(8.dp))
                CardDescription(info = label)
            }

        }
    }
}