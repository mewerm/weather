package com.maximmesh.weathergeekbrainsapp.repository

import com.maximmesh.weathergeekbrainsapp.repository.DTO.WeatherDTO
import com.maximmesh.weathergeekbrainsapp.utils.LAT
import com.maximmesh.weathergeekbrainsapp.utils.LON
import com.maximmesh.weathergeekbrainsapp.utils.YANDEX_API_KEY
import com.maximmesh.weathergeekbrainsapp.utils.YANDEX_ENDPOINT
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface WeatherAPI {
    @GET(YANDEX_ENDPOINT) //пишем только endpoint
    fun getWeather(
        @Header(YANDEX_API_KEY) apikey: String,
        @Query(LAT) lat: Double,
        @Query(LON) lon: Double
    ): Call<WeatherDTO>

}