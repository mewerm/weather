package com.maximmesh.weathergeekbrainsapp.domain.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HistoryEntity::class], version = 1,  )
abstract class WeatherAppDB: RoomDatabase() {
    abstract fun getHistoryDao():HistoryDao
}