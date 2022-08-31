package com.maximmesh.weathergeekbrainsapp.repository

import com.google.gson.annotations.SerializedName

data class InfoDTO(

	@field:SerializedName("lon")
	val lon: Int,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("lat")
	val lat: Int
)