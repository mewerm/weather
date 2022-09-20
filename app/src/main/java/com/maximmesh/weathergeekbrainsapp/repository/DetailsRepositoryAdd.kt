package com.maximmesh.weathergeekbrainsapp.repository

import com.maximmesh.weathergeekbrainsapp.viewmodel.DetailsViewModel

interface DetailsRepositoryAdd {
    fun addWeather(weather: Weather)
}