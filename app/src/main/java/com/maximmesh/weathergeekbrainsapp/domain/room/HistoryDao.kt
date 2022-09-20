package com.maximmesh.weathergeekbrainsapp.domain.room

import androidx.room.*

@Dao
interface HistoryDao { //интерфейс для работы с таблицей

    @Query("INSERT INTO history_entity(city,temperature,feelsLike,icon) VALUES(:city, :temperature,:feelsLike,:icon)")
fun nativeInsert(city: String, temperature: Int, feelsLike: Int, icon: String)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: HistoryEntity)

    @Delete
    fun delete(entity: HistoryEntity)

    @Update
    fun update(entity: HistoryEntity)

    @Query("SELECT * FROM history_entity")
    fun getAll(): List<HistoryEntity>

    @Query("SELECT * FROM history_entity WHERE city=:city")
    fun getHistoryForCity(city:String): List<HistoryEntity>
}