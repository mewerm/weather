package com.maximmesh.weathergeekbrainsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Thread.sleep

class MainViewModel(private val liveData:MutableLiveData<AppState> = MutableLiveData()): ViewModel() { //Android для контекста
    fun getData(): LiveData<AppState>{
        return liveData
    }

    fun getWeather(){
        Thread{
            liveData.postValue(AppState.Loading)
            sleep(2000L)
            if((0..10).random()>5){
                    liveData.postValue(AppState.Success(Any()))
                }else{
                liveData.postValue(AppState.Error(IllegalAccessError("ОШИБКА НЕУСПЕШНОЙ НЕУДАЧНОСТИ")))
            }
        }.start()
    }

}