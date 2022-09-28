package com.maximmesh.weathergeekbrainsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maximmesh.weathergeekbrainsapp.repository.*

class DetailsViewModel(
    private val liveData: MutableLiveData<DetailsState> = MutableLiveData(),
    private val repositoryOne: DetailsRepositoryOne = DetailsRepositoryOneRetrofit2Impl(),
    private val repositoryAdd: DetailsRepositoryAdd = DetailsRepositoryOneRoomImpl()
) : ViewModel() {

    fun getLiveData() = liveData

    fun getWeather(city: City) {
        liveData.postValue(DetailsState.Loading)
        repositoryOne.getWeatherDetails(city, object : Callback {
            override fun onResponse(weather: Weather) {
                liveData.postValue(DetailsState.Success(weather))
                Thread{
                    repositoryAdd.addWeather(weather)
                }.start()
            }

            override fun onError(error: Throwable) {
                liveData.postValue(DetailsState.Error(error))
            }
        })
    }

    interface Callback {

        fun onResponse(weather: Weather)

        fun onError(error: Throwable)
    }
}
