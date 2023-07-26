package com.chase.codechallenge.data.datasource.local.db

import com.chase.codechallenge.data.datasource.local.db.entity.MyCityEntity
import com.chase.codechallenge.data.datasource.local.db.room.MyCityDao
import javax.inject.Inject

class MyCityLocalDataSource @Inject constructor(private val myCityDao: MyCityDao) {

    suspend fun addMyCity(myCityEntity: MyCityEntity) = myCityDao.addCity(myCityEntity)

    fun getMyCity() = myCityDao.getMyCity()

    fun deleteMyCity(cityName: String) = myCityDao.deleteMyCity(cityName)

    suspend fun updateMyCity(
        temp: Double,
        latitude: Double,
        longitude: Double,
        cityName: String,
        description: String,
    ) = myCityDao.updateMyCity(temp, latitude, longitude, cityName, description)

    suspend fun getSpecificCity(cityName: String) = myCityDao.getSpecificCity(cityName)
}