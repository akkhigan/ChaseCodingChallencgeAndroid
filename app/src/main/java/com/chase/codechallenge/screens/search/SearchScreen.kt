package com.chase.codechallenge.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.chase.codechallenge.R
import com.chase.codechallenge.common.utils.AppStrings
import com.chase.codechallenge.domain.model.Forecast
import com.chase.codechallenge.domain.model.MyCity
import com.chase.codechallenge.screens.component.CircularProgressBar
import com.chase.codechallenge.screens.component.CityWeatherCard
import com.chase.codechallenge.ui.theme.*

@Composable
fun SearchScreen(viewModel: SearchViewModel, onNavigateToHomeScreen: () -> Unit) {
    val searchState by viewModel.searchCityState.collectAsState()
    val myCitiesState by viewModel.myCitiesState.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(DarkBlue, Blue, LightBlue))),
        topBar = { SearchTopBar(onNavigateToHomeScreen) },
        backgroundColor = Color.Transparent
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
        ) {
            SearchCityScreenContent(
                viewModel = viewModel,
                searchState = searchState,
                myCitiesState = myCitiesState
            )
        }
    }
}

@Composable
private fun SearchCityScreenContent(
    viewModel: SearchViewModel,
    searchState: SearchCityState,
    myCitiesState: MyCitiesState
) {
    SearchField(viewModel)
    if (viewModel.isCitySearched) {
        when (searchState) {
            is SearchCityState.Loading -> {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressBar(
                        modifier = Modifier
                            .size(LocalConfiguration.current.screenWidthDp.dp / 3)
                            .padding(top = 16.dp),
                    )
                }
            }
            is SearchCityState.Success -> {
                viewModel.updateSearchField("")
                if (searchState.forecast != null) {
                    WantedCityWeatherSection(searchState.forecast, viewModel)

                    //Add the resulted city to Local storage
                    viewModel.addMyCity(
                        MyCity(
                            temp = searchState.forecast.weatherList[0].weatherData.temp,
                            latitude = searchState.forecast.cityDtoData.coordinate.latitude,
                            longitude = searchState.forecast.cityDtoData.coordinate.longitude,
                            cityName = searchState.forecast.cityDtoData.cityName,
                            country = searchState.forecast.cityDtoData.country,
                            description = searchState.forecast.weatherList[0].weatherStatus[0].description,
                            icon = searchState.forecast.weatherList[0].weatherStatus[0].icon,
                        )
                    )
                }
            }
            is SearchCityState.Error -> {
                //TODO handle error case
            }
        }
        MyCities(myCitiesState, viewModel)
    } else {
        MyCities(myCitiesState, viewModel)
    }
}

@Composable
private fun SearchTopBar(onBackClick: () -> Unit) {
    TopAppBar(
        modifier = Modifier.statusBarsPadding(),
        title = { Text(text = AppStrings.search_title, style = MaterialTheme.typography.h2) },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back_24),
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
private fun SearchField(viewModel: SearchViewModel) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = viewModel.searchFieldValue,
        onValueChange = { viewModel.updateSearchField(it) },
        label = {
            Text(text = AppStrings.placeholder)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        maxLines = 1,
        trailingIcon = {
            IconButton(onClick = { viewModel.searchCityClick() }) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_search),
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
private fun WantedCityWeatherSection(forecast: Forecast, viewModel: SearchViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalConfiguration.current.screenHeightDp.dp / 4)
            .padding(top = 16.dp)
    ) {
        Text(text = AppStrings.subtitle2, style = MaterialTheme.typography.h2)
        CityWeatherCard(
            modifier = Modifier
                .padding(top = 16.dp),
            degree = "${forecast.weatherList[0].weatherData.temp.toInt()}${AppStrings.degree}",
            latitude = forecast.cityDtoData.coordinate.latitude,
            longitude = forecast.cityDtoData.coordinate.longitude,
            city = forecast.cityDtoData.cityName,
            country = forecast.cityDtoData.country,
            description = forecast.weatherList[0].weatherStatus[0].description,
            icon = forecast.weatherList[0].weatherStatus[0].icon
        )
    }
}

@Composable
private fun MyCities(myCitiesState: MyCitiesState, viewModel: SearchViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        horizontalAlignment = Alignment.Start
    ) {
        when (myCitiesState) {
            is MyCitiesState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressBar(modifier = Modifier.size(LocalConfiguration.current.screenWidthDp.dp / 3))
                }
            }
            is MyCitiesState.Success -> {
                if (myCitiesState.forecast.isNullOrEmpty()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = AppStrings.emptyScreen)
                    }
                } else {
                    CityListSection(myCitiesState.forecast, viewModel)
                }
            }
            is MyCitiesState.Error -> {
               //TODO error case myCitiesState.errorMessage
            }
        }
    }
}

@Composable
private fun CityListSection(cityList: List<MyCity>, viewModel: SearchViewModel) {
    Text(
        text = AppStrings.subtitle1,
        style = MaterialTheme.typography.h2
    )
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        items(cityList) {
            CityWeatherCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(LocalConfiguration.current.screenHeightDp.dp / 4)
                    .padding(top = 16.dp),
                degree = "${it.temp.toInt()}${AppStrings.degree}",
                latitude = it.latitude,
                longitude = it.longitude,
                city = it.cityName,
                country = it.country,
                description = it.description,
                icon =  it.icon
            )
        }
    }
}
