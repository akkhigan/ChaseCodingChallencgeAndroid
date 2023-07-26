package com.chase.codechallenge.screens.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chase.codechallenge.common.utils.HourConverter
import com.chase.codechallenge.domain.model.ForecastWeather

@Composable
fun ForecastLazyRow(forecasts: List<ForecastWeather>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(forecasts) {
            if (forecasts.size == 8) {
                WeatherCard(
                    time = HourConverter.convertHour(it.date.substring(11, 13)),
                    degree = "${it.weatherData.temp.toInt()}°"
                )
            } else {
                WeatherCard(
                    date = it.date.substring(5, 10).replace('-', '/'),
                    time = HourConverter.convertHour(it.date.substring(11, 13)),
                    degree = "${it.weatherData.temp.toInt()}°"
                )
            }
        }
    }
}

@Composable
private fun WeatherCard(date: String? = null, time: String, degree: String) {
    Card(
        modifier = Modifier,
        shape = MaterialTheme.shapes.large,
        backgroundColor = MaterialTheme.colors.secondary
    ) {
        Column(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                if (date != null) {
                    Text(text = date, style = MaterialTheme.typography.h3.copy(fontSize = 18.sp))
                }
                Text(text = time, style = MaterialTheme.typography.h3.copy(fontSize = 18.sp))
            }
            Text(text = degree, style = MaterialTheme.typography.h3.copy(fontSize = 24.sp))
        }
    }
}