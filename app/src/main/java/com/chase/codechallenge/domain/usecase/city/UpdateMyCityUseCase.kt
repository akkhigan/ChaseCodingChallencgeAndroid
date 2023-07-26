package com.chase.codechallenge.domain.usecase.city

import com.chase.codechallenge.data.repository.MyCityRepositoryImpl
import com.chase.codechallenge.domain.model.MyCity
import javax.inject.Inject

class UpdateMyCityUseCase @Inject constructor(private val myCityRepositoryImpl: MyCityRepositoryImpl) {
    suspend fun updateMyCityUseCase(myCity: MyCity) = myCityRepositoryImpl.updateMyCity(myCity)
}