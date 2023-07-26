package com.chase.codechallenge.data.datasource.remote.api.entity

import com.google.gson.annotations.SerializedName

data class SysDto(
    @SerializedName("sunrise") val sunrise: Long,
    @SerializedName("sunset") val sunset: Long,
)