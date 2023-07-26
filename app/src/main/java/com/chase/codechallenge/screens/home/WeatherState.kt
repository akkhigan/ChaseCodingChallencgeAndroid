package com.chase.codechallenge.screens.home

import com.chase.codechallenge.domain.model.Forecast

sealed interface WeatherState {
    data class Success(val forecast: Forecast?): WeatherState
    data class Error(val errorMessage: String?): WeatherState

    object Loading: WeatherState
}