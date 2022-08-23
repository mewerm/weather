package com.maximmesh.weathergeekbrainsapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maximmesh.weathergeekbrainsapp.R
import com.maximmesh.weathergeekbrainsapp.view.weatherlist.WeatherListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WeatherListFragment.newInstance())
                .commit()
        }
    }
}