package com.maximmesh.weathergeekbrainsapp.repository.DTO

import com.google.gson.annotations.SerializedName

data class ForecastDTO(

    @field:SerializedName("date")
	val date: String,

    @field:SerializedName("sunrise")
	val sunrise: String,

    @field:SerializedName("week")
	val week: Int,

    @field:SerializedName("moon_text")
	val moonText: String,

    @field:SerializedName("date_ts")
	val dateTs: Int,

    @field:SerializedName("sunset")
	val sunset: String,

    @field:SerializedName("parts")
	val parts: List<PartsItemDTO>,

    @field:SerializedName("moon_code")
	val moonCode: Int
)