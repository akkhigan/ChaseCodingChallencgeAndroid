package com.chase.codechallenge.domain.usecase.location

import com.chase.codechallenge.data.location.DefaultLocationTracker
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(private val defaultLocationTracker: DefaultLocationTracker) {
    suspend fun getLocation() = defaultLocationTracker.getCurrentLocation()
}