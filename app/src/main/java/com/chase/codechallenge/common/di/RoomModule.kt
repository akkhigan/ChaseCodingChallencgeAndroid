package com.chase.codechallenge.common.di

import android.content.Context
import androidx.room.Room
import com.chase.codechallenge.data.datasource.local.db.room.WeatherDatabase
import com.chase.codechallenge.common.utils.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): WeatherDatabase {
        return Room.databaseBuilder(context, WeatherDatabase::class.java, Database.database_name)
            .allowMainThreadQueries()
            .build()
    }
}