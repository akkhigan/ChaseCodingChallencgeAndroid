package com.chase.codechallenge.data.datasource.local.db.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.chase.codechallenge.data.datasource.local.db.entity.MyCityEntity
import com.chase.codechallenge.common.utils.Database

@Dao
interface MyCityDao {
    @Insert
    suspend fun addCity(myCityEntity: MyCityEntity)

    @Query("SELECT * FROM ${Database.my_city_table}")
    fun getMyCity(): List<MyCityEntity>

    @Query("DELETE FROM ${Database.my_city_table} WHERE city = :cityName")
    fun deleteMyCity(cityName: String)

    @Query("UPDATE ${Database.my_city_table} SET `temp` = :temp, latitude = :latitude, longitude = :longitude, description = :description WHERE city = :cityName")
    suspend fun updateMyCity(
        temp: Double,
        latitude: Double,
        longitude: Double,
        cityName: String,
        description: String,
    )

    @Query("SELECT * FROM ${Database.my_city_table} WHERE city = :cityName")
    suspend fun getSpecificCity(cityName: String) : MyCityEntity?
}