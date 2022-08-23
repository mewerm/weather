package com.maximmesh.weathergeekbrainsapp.view.weatherlist

import com.maximmesh.weathergeekbrainsapp.repository.Weather

interface OnItemListClickListener {
   fun onItemClick(weather: Weather)
}