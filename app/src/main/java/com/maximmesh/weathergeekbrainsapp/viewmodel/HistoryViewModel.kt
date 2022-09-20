package com.maximmesh.weathergeekbrainsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maximmesh.weathergeekbrainsapp.repository.DetailsRepositoryOneRoomImpl
import com.maximmesh.weathergeekbrainsapp.repository.RepositoryImpl
import com.maximmesh.weathergeekbrainsapp.repository.Weather

class HistoryViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: DetailsRepositoryOneRoomImpl = DetailsRepositoryOneRoomImpl()
) :
    ViewModel() {
    fun getData(): LiveData<AppState> {
        return liveData
    }

    fun getAll(){
         repository.getAllWeatherDetails(object : CallbackForAll{
             override fun onResponse(listWeather: List<Weather>) {
                 liveData.postValue(AppState.Success(listWeather))
             }

             override fun onError(error: Throwable) {
                 liveData.postValue(AppState.Error(error))
             }

         })
    }

    interface CallbackForAll {

        fun onResponse(listWeather: List<Weather>)

        fun onError(error: Throwable)
    }
}