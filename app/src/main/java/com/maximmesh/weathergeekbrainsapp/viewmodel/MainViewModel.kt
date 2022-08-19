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

    fun getWeather() {
        Thread {
            liveData.postValue(AppState.Loading)
            if ((0..10).random() > 5) {
                liveData.postValue(AppState.Success(repository.getWeatherFromServer()))
            } else {
                liveData.postValue(AppState.Error(IllegalAccessError("ОШИБКА НЕУСПЕШНОЙ НЕУДАЧНОСТИ")))
            }
        }.start()
    }

}