package com.maximmesh.weathergeekbrainsapp.repository

interface Repository {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocalStorage(): Weather
}