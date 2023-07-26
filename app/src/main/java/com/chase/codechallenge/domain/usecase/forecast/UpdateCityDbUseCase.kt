package com.chase.codechallenge.domain.usecase.forecast

import com.chase.codechallenge.data.repository.ForecastRepositoryImpl
import com.chase.codechallenge.domain.model.City
import javax.inject.Inject

class UpdateCityDbUseCase @Inject constructor(private val forecastRepositoryImpl: ForecastRepositoryImpl) {
    suspend fun updateCityDb(city: City) = forecastRepositoryImpl.updateCity(city)
}