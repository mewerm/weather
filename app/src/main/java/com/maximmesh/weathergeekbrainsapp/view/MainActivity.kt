package com.maximmesh.weathergeekbrainsapp.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.maximmesh.weathergeekbrainsapp.MyApp
import com.maximmesh.weathergeekbrainsapp.R
import com.maximmesh.weathergeekbrainsapp.view.weatherlist.HistoryWeatherListFragment
import com.maximmesh.weathergeekbrainsapp.view.weatherlist.WeatherListFragment

@Suppress("UNREACHABLE_CODE")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WeatherListFragment.newInstance())
                .commit()
        }
        MyApp.getHistoryDao().getAll()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_history -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, HistoryWeatherListFragment.newInstance())
                    .addToBackStack("")
                    .commit()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}