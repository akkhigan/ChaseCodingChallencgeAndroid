package com.chase.codechallenge.domain.usecase.city

import com.chase.codechallenge.data.repository.MyCityRepositoryImpl
import javax.inject.Inject

class GetMyCityUseCase @Inject constructor(private val myCityRepositoryImpl: MyCityRepositoryImpl) {
    fun getMyCity() = myCityRepositoryImpl.getMyCity()
}