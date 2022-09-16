package com.maximmesh.weathergeekbrainsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maximmesh.weathergeekbrainsapp.repository.RepositoryImpl

class MainViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: RepositoryImpl = RepositoryImpl()
) :
    ViewModel() {
    fun getData(): LiveData<AppState> {
        return liveData
    }

    fun getWeatherRussia() = getWeather(true)
    fun getWeatherWorld() = getWeather(false)

   private fun getWeather(isRussian:Boolean) {
        Thread {
            liveData.postValue(AppState.Loading)
            if (true) { //TODO:временная заглушка
                val answer = if(!isRussian) repository.getWorldWeatherFromLocalStorage() else repository.getRussianWeatherFromLocalStorage()
                liveData.postValue(AppState.Success(answer))
            } else {
                liveData.postValue(AppState.Error(IllegalAccessError("ОШИБКА НЕУСПЕШНОЙ НЕУДАЧНОСТИ")))
            }
        }.start()
    }
}