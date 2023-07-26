package com.chase.codechallenge.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chase.codechallenge.domain.model.City
import com.chase.codechallenge.domain.model.Forecast
import com.chase.codechallenge.domain.usecase.forecast.*
import com.chase.codechallenge.domain.usecase.location.GetLocationUseCase
import com.chase.codechallenge.common.Resource
import com.chase.codechallenge.common.utils.ExceptionTitles
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val addForecastDb: AddForecastToDbUseCase,
    private val addCityDb: AddCityToDbUseCase,
    private val updateCityDbUseCase: UpdateCityDbUseCase,
    private val getForecastDb: GetForecastFromDbUseCase,
    private val updateForecastDb: UpdateForecastDbUseCase,
    private val getForecast: GetForecastUseCase,
    private val getCurrentLocation: GetLocationUseCase
) : ViewModel() {

    private val _weatherState = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val currentWetherState = _weatherState.asStateFlow()

    fun loadLocation() {
        _weatherState.value = WeatherState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val locationData = getCurrentLocation.getLocation()
                if (locationData != null) {
                    fetchForecast(locationData.latitude, locationData.longitude)
                } else if (isForecastCached()) {
                    getCachedForecast()
                } else {
                    _weatherState.value = WeatherState.Error(ExceptionTitles.NO_INTERNET_CONNECTION)
                }
            } catch (e: Exception) {
                if (isForecastCached()) {
                    getCachedForecast()
                } else {
                    _weatherState.value = WeatherState.Error(e.message)
                }
            }
        }
    }

    private suspend fun fetchForecast(latitude: Double, longitude: Double) {
        when (val result = getForecast.getForecast(latitude, longitude)) {
            is Resource.Success -> {
                _weatherState.value = WeatherState.Success(result.data)
                if (result.data != null) {
                    if (!isForecastCached()) {
                        cacheForecast(result.data, result.data.cityDtoData)
                    } else {
                        updateCachedForecast(result.data, result.data.cityDtoData)
                    }
                }
            }
            is Resource.Error -> {
                _weatherState.value = WeatherState.Error(result.message)
            }
        }
    }

    private suspend fun cacheForecast(forecast: Forecast, city: City) {
        addForecastDb.addForecastToDbUseCase(
            forecast,
            forecast.weatherList.size
        )
        addCityDb.addCityDb(city)
    }

    private suspend fun updateCachedForecast(forecast: Forecast, city: City) {
        updateForecastDb.updateForecastDbUseCase(
            forecast,
            forecast.weatherList.size
        )
        updateCityDbUseCase.updateCityDb(city)
    }

    // Data cannot be null.
    // Because before this function is called, it is checked for null with the isForecastCached() function.
    private fun getCachedForecast() {
        _weatherState.value =
            WeatherState.Success(getForecastDb.getForecastFromDbUseCase())
    }

    private fun isForecastCached(): Boolean {
        return getForecastDb.getForecastFromDbUseCase() != null
    }
}