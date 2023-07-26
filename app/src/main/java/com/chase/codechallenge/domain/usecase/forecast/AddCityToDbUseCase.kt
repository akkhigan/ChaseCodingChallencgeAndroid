package com.chase.codechallenge.domain.usecase.forecast

import com.chase.codechallenge.data.repository.ForecastRepositoryImpl
import com.chase.codechallenge.domain.model.City
import javax.inject.Inject

class AddCityToDbUseCase @Inject constructor(private val forecastRepositoryImpl: ForecastRepositoryImpl) {
    suspend fun addCityDb(city: City) = forecastRepositoryImpl.addCity(city)
}