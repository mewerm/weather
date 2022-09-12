package com.maximmesh.weathergeekbrainsapp.repository

import android.os.Looper
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.maximmesh.weathergeekbrainsapp.BuildConfig
import com.maximmesh.weathergeekbrainsapp.repository.DTO.WeatherDTO
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class WeatherLoader(private val onServerResponseListener: OnServerResponse) {

    fun loadWeather(lat: Double, lon: Double) {


        val urlText = "https://api.weather.yandex.ru/v2/informers?lat=$lat&lon=$lon"
        val uri = URL(urlText)
        val urlConnection: HttpURLConnection =
            (uri.openConnection() as HttpURLConnection).apply {
                connectTimeout = 1000
                readTimeout = 1000
                addRequestProperty("X-Yandex-API-Key", BuildConfig.WEATHER_API_KEY) //скрыл ключ
            }

        Thread {
            try {
                val headers = urlConnection.headerFields
                val buffer = BufferedReader(InputStreamReader(urlConnection.inputStream))
                val weatherDTO: WeatherDTO = Gson().fromJson(buffer, WeatherDTO::class.java)

                android.os.Handler(Looper.getMainLooper()).post { onServerResponseListener.onResponse(weatherDTO) }
                //делаем отложенный вызов, когда будет готов вызов в главном потоке
            } catch (e: JsonSyntaxException) {

            } finally {
                urlConnection.disconnect()
            }
        }.start()
    }
}



