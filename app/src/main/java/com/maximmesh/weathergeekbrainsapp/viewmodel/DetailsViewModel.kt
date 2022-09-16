package com.maximmesh.weathergeekbrainsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maximmesh.weathergeekbrainsapp.repository.City
import com.maximmesh.weathergeekbrainsapp.repository.DetailsRepository
import com.maximmesh.weathergeekbrainsapp.repository.DetailsRepositoryRetrofit2Impl
import com.maximmesh.weathergeekbrainsapp.repository.Weather

class DetailsViewModel(
    private val liveData: MutableLiveData<DetailsState> = MutableLiveData(),
    private val repository: DetailsRepository = DetailsRepositoryRetrofit2Impl()
) : ViewModel() {

    fun getLiveData() = liveData

    fun getWeather(city: City) {
        liveData.postValue(DetailsState.Loading)
        repository.getWeatherDetails(city, object : Callback {
            override fun onResponse(weather: Weather) {
                liveData.postValue(DetailsState.Success(weather))
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
