package com.chase.codechallenge.screens.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CityWeatherCard(
    modifier: Modifier = Modifier,
    degree: String,
    latitude: Double,
    longitude: Double,
    city: String,
    country: String,
    description: String
) {
    Card(
        modifier = modifier,
        backgroundColor = Color.LightGray,
        shape = MaterialTheme.shapes.medium
    ) {
        WeatherInfo(degree, latitude, longitude, city, country, description)
    }
}


@Composable
private fun WeatherInfo(
    degree: String,
    latitude: Double,
    longitude: Double,
    city: String,
    country: String,
    description: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        DegreeAndButtonSection(degree = degree)
        Text(modifier = Modifier.padding(16.dp), text = "${city}, $country", fontSize = 24.sp)
        LocationAndDescription(latitude, longitude, description)
    }
}

@Composable
private fun DegreeAndButtonSection(
    degree: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(modifier = Modifier.padding(start = 16.dp), text = degree, fontSize = 76.sp)
    }
}

@Composable
private fun LocationAndDescription(
    latitude: Double,
    longitude: Double,
    description: String,
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp, top = 40.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.padding(start = 16.dp), horizontalAlignment = Alignment.Start) {
            Text(text = "H:${latitude}  L:${longitude}")
        }
        Text(modifier = Modifier.padding(end = 16.dp), text = description)
    }
}