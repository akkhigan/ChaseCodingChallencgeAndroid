package com.chase.codechallenge.domain.usecase.city

import com.chase.codechallenge.data.repository.MyCityRepositoryImpl
import javax.inject.Inject

class GetSpecificCityUseCase @Inject constructor(private val myCityRepositoryImpl: MyCityRepositoryImpl) {
    suspend fun getSpecificCityUseCase(cityName: String) =
        myCityRepositoryImpl.getSpecificCity(cityName)
}