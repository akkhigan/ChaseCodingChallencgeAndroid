package com.chase.codechallenge.data.repository

import com.chase.codechallenge.data.datasource.local.db.MyCityLocalDataSource
import com.chase.codechallenge.data.mapper.MyCityEntityMapper
import com.chase.codechallenge.domain.model.MyCity
import com.chase.codechallenge.domain.repository.MyCityRepository
import javax.inject.Inject

class MyCityRepositoryImpl @Inject constructor(
    private val myCityLocalDataSource: MyCityLocalDataSource,
    private val myCityEntityMapper: MyCityEntityMapper
) : MyCityRepository {

    override suspend fun addMyCity(myCity: MyCity) {
        myCityLocalDataSource.addMyCity(
            myCityEntityMapper.entityFromModel(
                myCity
            )
        )
    }

    override fun getMyCity(): List<MyCity> {
        return myCityEntityMapper.mapFromEntity(
            myCityLocalDataSource.getMyCity()
        )
    }

    override fun deleteMyCity(cityName: String) =
        myCityLocalDataSource.deleteMyCity(cityName)

    override suspend fun updateMyCity(myCity: MyCity) {
        myCityLocalDataSource.updateMyCity(
            temp = myCity.temp,
            latitude = myCity.latitude,
            longitude = myCity.longitude,
            cityName = myCity.cityName,
            description = myCity.description,
        )
    }

    override suspend fun getSpecificCity(cityName: String): Boolean =
        myCityLocalDataSource.getSpecificCity(cityName) != null

}