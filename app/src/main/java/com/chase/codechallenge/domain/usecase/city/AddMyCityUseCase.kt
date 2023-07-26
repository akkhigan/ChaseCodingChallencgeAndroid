package com.chase.codechallenge.domain.usecase.city

import com.chase.codechallenge.data.repository.MyCityRepositoryImpl
import com.chase.codechallenge.domain.model.MyCity
import javax.inject.Inject

class AddMyCityUseCase @Inject constructor(private val myCityRepositoryImpl: MyCityRepositoryImpl) {
    suspend fun addMyCity(myCity: MyCity) = myCityRepositoryImpl.addMyCity(myCity)
}