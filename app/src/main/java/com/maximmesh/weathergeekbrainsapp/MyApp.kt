package com.maximmesh.weathergeekbrainsapp

import android.app.Application
import androidx.room.Room
import com.maximmesh.weathergeekbrainsapp.domain.room.HistoryDao
import com.maximmesh.weathergeekbrainsapp.domain.room.WeatherAppDB

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this
    }

    companion object { //тк хочу room вынести на уровень всего приложения
        private var db: WeatherAppDB? = null
        private var appContext: MyApp? = null
        fun getHistoryDao(): HistoryDao {
            if (db == null) {
                if (appContext != null) {

                        db = Room.databaseBuilder(appContext!!, WeatherAppDB::class.java, "emmm")
                            .build()
                } else {
                    throw IllegalStateException("что-то пошло не так")
                }
            }
            return db!!.getHistoryDao()
        }
    }
}