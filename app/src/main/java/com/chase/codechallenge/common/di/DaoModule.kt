package com.chase.codechallenge.common.di

import com.chase.codechallenge.data.datasource.local.db.room.CityDao
import com.chase.codechallenge.data.datasource.local.db.room.ForecastDao
import com.chase.codechallenge.data.datasource.local.db.room.MyCityDao
import com.chase.codechallenge.data.datasource.local.db.room.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    @Singleton
    fun bindCurrentWeatherDao(weatherDatabase: WeatherDatabase): CityDao =
        weatherDatabase.cityDao()

    @Provides
    @Singleton
    fun bindForecastDao(weatherDatabase: WeatherDatabase): ForecastDao =
        weatherDatabase.forecastWeatherDao()

    @Provides
    @Singleton
    fun bindMyCityDao(weatherDatabase: WeatherDatabase): MyCityDao =
        weatherDatabase.myCityDao()
}