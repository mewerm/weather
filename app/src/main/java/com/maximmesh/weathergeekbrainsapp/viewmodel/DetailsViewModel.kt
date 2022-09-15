package com.maximmesh.weathergeekbrainsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maximmesh.weathergeekbrainsapp.repository.City
import com.maximmesh.weathergeekbrainsapp.repository.DetailsRepository
import com.maximmesh.weathergeekbrainsapp.repository.DetailsRepositoryOkHttpImpl
import com.maximmesh.weathergeekbrainsapp.repository.Weather

class DetailsViewModel(
    private val liveData: MutableLiveData<DetailsState> = MutableLiveData(),
    private var repository: DetailsRepository = DetailsRepositoryOkHttpImpl()
) : ViewModel() {

    fun getLiveData() = liveData

    fun getWeather(city: City) {
        liveData.postValue(DetailsState.Loading)
        repository.getWeatherDetails(city, object : Callback {
            override fun onResponse(weather: Weather) {
                liveData.postValue(DetailsState.Success(weather))
            }
        })
    }


    interface Callback {

        fun onResponse(weather: Weather)
    }
}
