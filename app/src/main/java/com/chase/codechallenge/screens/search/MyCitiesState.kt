package com.chase.codechallenge.screens.search

import com.chase.codechallenge.domain.model.MyCity

sealed interface MyCitiesState {
    data class Success(val forecast: List<MyCity>?): MyCitiesState
    data class Error(val errorMessage: String?): MyCitiesState

    object Loading: MyCitiesState
}