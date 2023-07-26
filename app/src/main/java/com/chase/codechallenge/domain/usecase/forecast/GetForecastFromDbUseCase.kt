package com.chase.codechallenge.domain.usecase.forecast

import com.chase.codechallenge.data.repository.ForecastRepositoryImpl
import com.chase.codechallenge.domain.model.Forecast
import javax.inject.Inject

class GetForecastFromDbUseCase @Inject constructor(private val forecastRepositoryImpl: ForecastRepositoryImpl) {
    fun getForecastFromDbUseCase() : Forecast? = forecastRepositoryImpl.getForecastWeather()
}