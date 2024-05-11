package com.febi.flowerapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.febi.flowerapp.R
import com.febi.flowerapp.ui.components.TextInfoProfile

@Composable
fun AboutScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp)
                .verticalScroll(rememberScrollState())
        ) {
            val borderWidth = 4.dp
            Image(
                painter = painterResource(id = R.drawable.a),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(150.dp)
//                    .border(
////                        BorderStroke(borderWidth, Color.White),
//                        CircleShape
//                    )
                    .padding(borderWidth)
                    .clip(CircleShape)
            )
            TextInfoProfile(first = "Nama", second = "Febiyanti W" )
            TextInfoProfile(first = "Email", second = "febiyantiw96@gmail.com" )
            TextInfoProfile(first = "Perguruan Tinggi", second = "Universitas Sriwijaya" )
            TextInfoProfile(first = "Jurusan", second = "Teknik Informatika" )
        }
    }
}