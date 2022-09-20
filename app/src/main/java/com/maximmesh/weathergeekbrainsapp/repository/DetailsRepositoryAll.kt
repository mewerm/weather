package com.maximmesh.weathergeekbrainsapp.repository

import com.maximmesh.weathergeekbrainsapp.viewmodel.HistoryViewModel

interface DetailsRepositoryAll {
    fun getAllWeatherDetails(callback: HistoryViewModel.CallbackForAll)
}