package com.chase.codechallenge.data.datasource.remote.api.entity

import com.google.gson.annotations.SerializedName

data class CloudsDto(
    @SerializedName("all") val cloudiness: Int
)
