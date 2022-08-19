package com.maximmesh.weathergeekbrainsapp.viewmodel

sealed class AppState { //запечатанный класс
    object Loading:AppState()
    data class Success(val weatherData: Any):AppState()
    data class Error(val error: Throwable):AppState()


}
