package com.maximmesh.weathergeekbrainsapp.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.maximmesh.weathergeekbrainsapp.databinding.FragmentDetailsBinding
import com.maximmesh.weathergeekbrainsapp.repository.Weather
import com.maximmesh.weathergeekbrainsapp.repository.DTO.WeatherDTO
import com.maximmesh.weathergeekbrainsapp.repository.OnServerResponse
import com.maximmesh.weathergeekbrainsapp.repository.WeatherLoader
import com.maximmesh.weathergeekbrainsapp.utils.KEY_BUNDLE_WEATHER

class DetailsFragment : Fragment(), OnServerResponse {

    //создаем две ссылки binding на один объект: _binding(Null) binding(notNull): чтобы небыло утечки памяти
    private var _binding: FragmentDetailsBinding? = null //переменная _binding которая может быть null

    private val binding: FragmentDetailsBinding
        get() {
            return _binding!! //точно не null
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null //переменная _binding которая может быть null ее можно занулить, ее необходимо занулить
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        return binding.root //а тут binding возвращается NotNull и ниже используется NotNull binding
    }

    lateinit var currentCityName:String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Weather>(KEY_BUNDLE_WEATHER)?.let {
            currentCityName = it.city.name
            WeatherLoader(this@DetailsFragment).loadWeather(it.city.lat, it.city.lon)
        }
    }

    private fun renderData(weather: WeatherDTO) {
        with(binding){
            loadingLayout.visibility = View.GONE
            cityName.text = currentCityName
            temperatureValue.text = weather.factDTO.temp.toString()
            feelsLikeValue.text = weather.factDTO.feelsLike.toString()
            cityCoordinates.text = "${weather.infoDTO.lat} ${weather.infoDTO.lon}"
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment

        }
    }

    override fun onResponse(weatherDTO: WeatherDTO) { //отложенный вызов
        renderData(weatherDTO)
    }
}
