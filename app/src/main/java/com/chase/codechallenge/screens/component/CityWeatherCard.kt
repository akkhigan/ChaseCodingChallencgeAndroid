package com.chase.codechallenge.screens.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.chase.codechallenge.ui.theme.DarkBlue

@Composable
fun CityWeatherCard(
    modifier: Modifier = Modifier,
    degree: String,
    latitude: Double,
    longitude: Double,
    city: String,
    country: String,
    description: String,
    icon:String
) {
    Card(
        modifier = modifier,
        backgroundColor = DarkBlue,
        shape = MaterialTheme.shapes.medium
    ) {
        WeatherInfo(degree, latitude, longitude, city, country, description,icon)
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
    icon:String
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        DegreeAndButtonSection(degree = degree, icon = icon)
        Text(modifier = Modifier.padding(horizontal = 16.dp), text = "${city}, $country", fontSize = 24.sp)
        LocationAndDescription(latitude, longitude, description)
    }
}

@Composable
private fun DegreeAndButtonSection(
    degree: String,
    icon:String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val painter = rememberAsyncImagePainter("https://openweathermap.org/img/wn/$icon@2x.png")
        Text(modifier = Modifier.padding(start = 16.dp), text = degree, fontSize = 60.sp)
        Image(
            modifier = Modifier.size(80.dp),
            painter = painter,
            contentDescription = null
        )
    }
}

@Composable
private fun LocationAndDescription(
    latitude: Double,
    longitude: Double,
    description: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.padding(start = 16.dp), horizontalAlignment = Alignment.Start) {
            Text(text = "H:${latitude}  L:${longitude}")
        }
        Text(modifier = Modifier.padding(end = 16.dp), text = description)
    }
}