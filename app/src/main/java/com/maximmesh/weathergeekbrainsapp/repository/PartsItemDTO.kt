package com.maximmesh.weathergeekbrainsapp.repository

import com.google.gson.annotations.SerializedName

data class PartsItemDTO(

	@field:SerializedName("polar")
	val polar: Boolean,

	@field:SerializedName("pressure_mm")
	val pressureMm: Int,

	@field:SerializedName("icon")
	val icon: String,

	@field:SerializedName("prec_period")
	val precPeriod: Int,

	@field:SerializedName("wind_dir")
	val windDir: String,

	@field:SerializedName("temp_max")
	val tempMax: Int,

	@field:SerializedName("feels_like")
	val feelsLike: Int,

	@field:SerializedName("wind_gust")
	val windGust: Double,

	@field:SerializedName("temp_min")
	val tempMin: Int,

	@field:SerializedName("condition")
	val condition: String,

	@field:SerializedName("temp_avg")
	val tempAvg: Int,

	@field:SerializedName("pressure_pa")
	val pressurePa: Int,

	@field:SerializedName("humidity")
	val humidity: Int,

	@field:SerializedName("wind_speed")
	val windSpeed: Double,

	@field:SerializedName("daytime")
	val daytime: String,

	@field:SerializedName("part_name")
	val partName: String,

	@field:SerializedName("prec_mm")
	val precMm: Int,

	@field:SerializedName("prec_prob")
	val precProb: Int
)