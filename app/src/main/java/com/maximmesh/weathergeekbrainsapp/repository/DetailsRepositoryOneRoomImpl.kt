package com.maximmesh.weathergeekbrainsapp.repository

import com.maximmesh.weathergeekbrainsapp.MyApp
import com.maximmesh.weathergeekbrainsapp.utils.convertHistoryEntityToWeather
import com.maximmesh.weathergeekbrainsapp.utils.convertWeatherToEntity
import com.maximmesh.weathergeekbrainsapp.viewmodel.DetailsViewModel
import com.maximmesh.weathergeekbrainsapp.viewmodel.HistoryViewModel

class DetailsRepositoryOneRoomImpl : DetailsRepositoryOne, DetailsRepositoryAll, DetailsRepositoryAdd {
    override fun getAllWeatherDetails(callback: HistoryViewModel.CallbackForAll) {
        Thread{
            callback.onResponse(convertHistoryEntityToWeather(MyApp.getHistoryDao().getAll()))
        }.start()
    }

    override fun getWeatherDetails(city: City, callback: DetailsViewModel.Callback) {
        val list = convertHistoryEntityToWeather(MyApp.getHistoryDao().getHistoryForCity(city.name))
        callback.onResponse(list.last()) // hack
    }

    override fun addWeather(weather: Weather) {
        MyApp.getHistoryDao().insert(convertWeatherToEntity(weather))}

}
