package com.maximmesh.weathergeekbrainsapp.repository

import com.google.gson.Gson
import com.maximmesh.weathergeekbrainsapp.BuildConfig
import com.maximmesh.weathergeekbrainsapp.repository.DTO.WeatherDTO
import com.maximmesh.weathergeekbrainsapp.utils.YANDEX_API_KEY
import com.maximmesh.weathergeekbrainsapp.utils.YANDEX_DOMAIN
import com.maximmesh.weathergeekbrainsapp.utils.YANDEX_ENDPOINT
import com.maximmesh.weathergeekbrainsapp.utils.convertDtoToModel
import com.maximmesh.weathergeekbrainsapp.viewmodel.DetailsViewModel
import okhttp3.OkHttpClient
import okhttp3.Request

class DetailsRepositoryOkHttpImpl : DetailsRepository {

    override fun getWeatherDetails(city: City, callback: DetailsViewModel.Callback){

        val client = OkHttpClient() //создали клиент
        val builder = Request.Builder()//билдер запроса
        builder.addHeader(YANDEX_API_KEY, BuildConfig.WEATHER_API_KEY)
        builder.url("$YANDEX_DOMAIN${YANDEX_ENDPOINT}lat=${city.lat}&lon=${city.lon}")
        val request = builder.build()
        val call = client.newCall(request)

        Thread {
            val response = call.execute() //- для выполнения кода синхронно (здесь и сейчас)
            if (response.isSuccessful) {
                val serverResponse = response.body()!!.string()
                val weatherDTO: WeatherDTO = Gson().fromJson(serverResponse, WeatherDTO::class.java)
                val weather = convertDtoToModel(weatherDTO)
                callback.onResponse(weather)
            }
        }.start()
    }
}
