package com.maximmesh.weathergeekbrainsapp.domain.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_entity")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long, //внутри sql это будет все равно Integer
    val city: String,
    val temperature: Int,
    val feelsLike: Int,
    val icon: String
)


