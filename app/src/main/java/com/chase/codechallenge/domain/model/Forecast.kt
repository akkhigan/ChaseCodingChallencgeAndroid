package com.chase.codechallenge.domain.model

data class Forecast(
    val weatherList: List<ForecastWeather>,
    val cityDtoData: City
)
