package com.maximmesh.weathergeekbrainsapp.utils

import com.maximmesh.weathergeekbrainsapp.domain.room.HistoryEntity
import com.maximmesh.weathergeekbrainsapp.repository.City
import com.maximmesh.weathergeekbrainsapp.repository.DTO.FactDTO
import com.maximmesh.weathergeekbrainsapp.repository.DTO.WeatherDTO
import com.maximmesh.weathergeekbrainsapp.repository.Weather
import com.maximmesh.weathergeekbrainsapp.repository.getDefaultCity

//хранилище констант
const val  KEY_BUNDLE_WEATHER = "key"
const val YANDEX_API_KEY = "X-Yandex-API-Key"
const val YANDEX_DOMAIN = "https://api.weather.yandex.ru/"
const val YANDEX_ENDPOINT ="v2/informers?"
const val LAT = "lat"
const val LON = "lon"

    fun convertDtoToModel(weatherDTO: WeatherDTO): Weather{
        val fact: FactDTO = weatherDTO.factDTO
        return (Weather(getDefaultCity(), fact.temp, fact.feelsLike, fact.icon))
    }

fun convertHistoryEntityToWeather(entityList: List<HistoryEntity>): List<Weather> {
    return entityList.map {
        Weather(City(it.city, 0.0, 0.0), it.temperature, it.feelsLike, it.icon)
    }
}

    fun convertWeatherToEntity(weather: Weather): HistoryEntity{
    return HistoryEntity(0,weather.city.name, weather.temperature, weather.feelsLike,weather.icon)
    }
