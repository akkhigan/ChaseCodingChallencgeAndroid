package com.chase.codechallenge.screens.home

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.chase.codechallenge.R
import com.chase.codechallenge.common.utils.AppStrings
import com.chase.codechallenge.domain.model.Forecast
import com.chase.codechallenge.screens.component.*
import com.chase.codechallenge.ui.theme.*

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
        title = { Text(text = AppStrings.home_title, style = MaterialTheme.typography.h2) },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_menu_24),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        backgroundColor = Purple200,
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
            //Error case to handle
            Button(
                modifier = Modifier.padding(top = 50.dp),
                onClick = { errorCardOnClick }) {
                Text(text = "Error! Click to Exit", style = MaterialTheme.typography.h3)
            }
        }
    }
}

@Composable
private fun CurrentWeatherSection(todayWeather: Forecast, onNavigateToSearchScreen: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 20.dp, vertical = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var icon = todayWeather.weatherList[0].weatherStatus[0].icon
        val painter = rememberAsyncImagePainter("https://openweathermap.org/img/wn/$icon@2x.png")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "${todayWeather.weatherList[0].weatherData.temp.toInt()}${AppStrings.degree}",
                    style = MaterialTheme.typography.h1
                )
                Text(
                    text = todayWeather.weatherList[0].weatherStatus[0].mainDescription,
                    style = MaterialTheme.typography.h3
                )
            }

            Image(
                modifier = Modifier.size(150.dp),
                painter = painter,
                contentDescription = null
            )
        }

        Row(modifier = Modifier.padding(top = 40.dp)) {
            Text(
                text = todayWeather.cityDtoData.cityName,
                style = MaterialTheme.typography.h2
            )

            Image(
                modifier = Modifier.size(40.dp),
                painter = painterResource(id = R.drawable.ic_location),
                contentDescription = null
            )
        }



        Text(
            modifier = Modifier.padding(vertical = 16.dp),
            text = todayWeather.weatherList[0].weatherStatus[0].description,
            style = MaterialTheme.typography.h3,
        )
        Text(
            text = "H:${todayWeather.cityDtoData.coordinate.longitude}°  L:${todayWeather.cityDtoData.coordinate.latitude}°",
            style = MaterialTheme.typography.h3
        )

        Button(
            modifier = Modifier.padding(top = 50.dp),
            onClick = { onNavigateToSearchScreen() }) {
            Text(text = "Search Weather for City", style = MaterialTheme.typography.h3)
        }
    }
}
