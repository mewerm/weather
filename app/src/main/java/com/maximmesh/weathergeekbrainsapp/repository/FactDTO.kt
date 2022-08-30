package com.maximmesh.weathergeekbrainsapp.repository

import com.google.gson.annotations.SerializedName

data class FactDTO(
    @SerializedName("obs_time")
    val obsTime: Int,
    @SerializedName("uptime")
    val uptime: Int,
    @SerializedName("temp")
    val temp: Int,
    @SerializedName("feels_like")
    val feelsLike: Int,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("condition")
    val condition: String,
    @SerializedName("cloudness")
    val cloudness: Int,
    @SerializedName("prec_type")
    val precType: Int,
    @SerializedName("prec_prob")
    val precProb: Int,
    @SerializedName("prec_strength")
    val precStrength: Int,
    @SerializedName("is_thunder")
    val isThunder: Boolean,
    @SerializedName("wind_speed")
    val windSpeed: Double,
    @SerializedName("wind_dir")
    val windDir: String,
    @SerializedName("pressure_mm")
    val pressureMm: Int,
    @SerializedName("pressure_pa")
    val pressurePa: Int,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("daytime")
    val daytime: String,
    @SerializedName("polar")
    val polar: Boolean,
    @SerializedName("season")
    val season: String,
    @SerializedName("source")
    val source: String,
    @SerializedName("soil_moisture")
    val soilMoisture: Int,
    @SerializedName("soil_temp")
    val soilTemp: Int,
    @SerializedName("uv_index")
    val uvIndex: Int,
    @SerializedName("wind_gust")
    val windGust: Double
)
