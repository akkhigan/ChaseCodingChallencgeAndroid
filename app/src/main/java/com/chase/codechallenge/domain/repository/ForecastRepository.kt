package com.chase.codechallenge.domain.repository

import com.chase.codechallenge.domain.model.City
import com.chase.codechallenge.domain.model.Forecast
import com.chase.codechallenge.common.Resource

interface ForecastRepository {
    suspend fun getForecastData(latitude: Double, longitude: Double, ): Resource<Forecast>

    suspend fun getForecastDataWithCityName(cityName: String): Resource<Forecast>

    suspend fun addForecastWeather(forecast: Forecast)

    suspend fun addCity(city: City)

    fun getForecastWeather() : Forecast?

    fun getCity() : City

    suspend fun updateForecastWeather(forecast: Forecast)

    suspend fun updateCity(city: City)
}