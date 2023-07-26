package com.chase.codechallenge.domain.usecase.forecast

import com.chase.codechallenge.data.repository.ForecastRepositoryImpl
import javax.inject.Inject

class GetWetherByCityNameUseCase @Inject constructor(private val forecastRepositoryImpl: ForecastRepositoryImpl) {
    suspend fun getForecast(cityName: String) =
        forecastRepositoryImpl.getForecastDataWithCityName(cityName)
}