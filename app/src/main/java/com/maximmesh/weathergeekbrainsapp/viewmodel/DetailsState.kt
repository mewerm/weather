package com.maximmesh.weathergeekbrainsapp.viewmodel

import com.maximmesh.weathergeekbrainsapp.repository.Weather

sealed class DetailsState { //запечатанный класс
    object Loading:DetailsState()
    data class Success(val weather: Weather):DetailsState()
    data class Error(val error: Throwable):DetailsState()


}
