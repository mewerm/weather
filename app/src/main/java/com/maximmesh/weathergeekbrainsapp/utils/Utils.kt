package com.maximmesh.weathergeekbrainsapp.utils

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
class Utils {}

    fun convertDtoToModel(weatherDTO: WeatherDTO): Weather{
        val fact: FactDTO = weatherDTO.factDTO
        return (Weather(getDefaultCity(), fact.temp, fact.feelsLike, fact.icon))
    }
