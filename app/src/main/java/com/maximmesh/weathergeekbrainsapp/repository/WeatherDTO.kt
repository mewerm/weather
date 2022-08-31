package com.maximmesh.weathergeekbrainsapp.repository

import com.google.gson.annotations.SerializedName

data class WeatherDTO(

	@field:SerializedName("now_dt")
	val nowDt: String,

	@field:SerializedName("fact")
	val factDTO: FactDTO,

	@field:SerializedName("now")
	val now: Int,

	@field:SerializedName("forecast")
	val forecastDTO: ForecastDTO,

	@field:SerializedName("info")
	val infoDTO: InfoDTO
)