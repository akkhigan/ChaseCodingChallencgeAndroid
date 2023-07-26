package com.chase.codechallenge.screens.home

import android.R
import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.chase.codechallenge.common.utils.AppStrings
import com.chase.codechallenge.domain.model.Forecast
import com.chase.codechallenge.screens.component.*
import com.chase.codechallenge.ui.theme.Blue
import com.chase.codechallenge.ui.theme.DarkBlue
import com.chase.codechallenge.ui.theme.LightBlue

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(viewModel: HomeViewModel, onNavigateToSearchScreen: () -> Unit) {
    val currentWeatherState by viewModel.currentWetherState.collectAsState()
    val activity = (LocalContext.current as? Activity)

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(DarkBlue, Blue, LightBlue))),
        topBar = { HomeTopBar() },
        backgroundColor = Color.Transparent
    ) {
        CurrentWeather(currentWeatherState, onNavigateToSearchScreen) { activity?.finish() }
    }
}
@Composable
private fun HomeTopBar() {
    TopAppBar(
        modifier = Modifier.statusBarsPadding(),
        title = { Text(text = AppStrings.home_topbar_title, style = MaterialTheme.typography.h2) },
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    )
}
@Composable
private fun CurrentWeather(
    currentWeatherState: WeatherState,
    onNavigateToSearchScreen: () -> Unit,
    errorCardOnClick: () -> Unit
) {
    when (currentWeatherState) {
        is WeatherState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressBar(modifier = Modifier.size(LocalConfiguration.current.screenWidthDp.dp / 3))
            }
        }
        is WeatherState.Success -> {
            if (currentWeatherState.forecast != null) {
                CurrentWeatherSection(currentWeatherState.forecast, onNavigateToSearchScreen)
            }
        }
        is WeatherState.Error -> {
            //TODO handle error case
        }
    }
}

@Composable
private fun CurrentWeatherSection(todayWeather: Forecast, onNavigateToSearchScreen: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(top = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = todayWeather.cityDtoData.cityName,
            style = MaterialTheme.typography.h2
        )
        Text(
            text = "${todayWeather.weatherList[0].weatherData.temp.toInt()}${AppStrings.degree}",
            style = MaterialTheme.typography.h1
        )
        Text(
            text = todayWeather.weatherList[0].weatherStatus[0].description,
            style = MaterialTheme.typography.h3,
            color = Color.Gray
        )
        Text(
            text = "H:${todayWeather.cityDtoData.coordinate.longitude}°  L:${todayWeather.cityDtoData.coordinate.latitude}°",
            style = MaterialTheme.typography.h3
        )
        Button(
            modifier = Modifier.padding(top = 50.dp),
            onClick = { onNavigateToSearchScreen() }) {
            Text(text = "Search Weather by City")
        }
    }
}
