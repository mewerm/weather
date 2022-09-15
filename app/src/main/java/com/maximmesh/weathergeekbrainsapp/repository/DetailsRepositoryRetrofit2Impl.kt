package com.maximmesh.weathergeekbrainsapp.repository

import com.google.gson.GsonBuilder
import com.maximmesh.weathergeekbrainsapp.BuildConfig
import com.maximmesh.weathergeekbrainsapp.repository.DTO.WeatherDTO
import com.maximmesh.weathergeekbrainsapp.utils.YANDEX_DOMAIN
import com.maximmesh.weathergeekbrainsapp.utils.convertDtoToModel
import com.maximmesh.weathergeekbrainsapp.viewmodel.DetailsViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsRepositoryRetrofit2Impl : DetailsRepository {

    override fun getWeatherDetails(city: City, callback: DetailsViewModel.Callback) {
        val weatherAPI = Retrofit.Builder().apply {
            baseUrl(YANDEX_DOMAIN)
            addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        }.build().create(WeatherAPI::class.java) //запрос впитал

        weatherAPI.getWeather(BuildConfig.WEATHER_API_KEY, city.lat, city.lon)
            .enqueue(object : Callback<WeatherDTO> { //асинхронное выполнение
                override fun onResponse(call: Call<WeatherDTO>, response: Response<WeatherDTO>) {
                    if (response.isSuccessful){
                        response.body()?.let {
                            val weather = convertDtoToModel(it)
                            weather.city = city
                            callback.onResponse(weather)
                        }
                    }
                }

                override fun onFailure(call: Call<WeatherDTO>, t: Throwable) {
                }
            })
    }
}