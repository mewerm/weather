package com.maximmesh.weathergeekbrainsapp.viewmodel

import com.maximmesh.weathergeekbrainsapp.repository.Weather

sealed class AppState { //запечатанный класс
    object Loading:AppState()
    data class Success(val weatherList: List<Weather>):AppState()
    data class Error(val error: Throwable):AppState()


}
