package com.chase.codechallenge.domain.usecase.forecast

import com.chase.codechallenge.data.repository.ForecastRepositoryImpl
import com.chase.codechallenge.domain.model.Forecast
import com.chase.codechallenge.common.Resource
import javax.inject.Inject

class GetForecastUseCase @Inject constructor(private val forecastRepositoryImpl: ForecastRepositoryImpl) {
    suspend fun getForecast(latitude: Double, longitude: Double): Resource<Forecast> =
        forecastRepositoryImpl.getForecastData(latitude, longitude)
}