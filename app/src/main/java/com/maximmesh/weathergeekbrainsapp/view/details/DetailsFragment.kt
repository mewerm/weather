package com.maximmesh.weathergeekbrainsapp.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.maximmesh.weathergeekbrainsapp.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

/*
    private fun renderData(data: AppState) {
        when (data) {
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                Snackbar.make(binding.mainView, "Не получилось ${data.error}", Snackbar.LENGTH_LONG).show()
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE

            }
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                binding.cityName.text = data.weatherData.city.name
                binding.cityCoordinates.text = "${data.weatherData.city.lat} ${data.weatherData.city.lon}"
                binding.temperatureValue.text = data.weatherData.temperature.toString()
                binding.feelsLikeValue.text = data.weatherData.feelsLike.toString()
                Snackbar.make(binding.mainView, "Получилось", Snackbar.LENGTH_LONG).show()

            }

        }

    }
*/

    companion object {
        @JvmStatic
        fun newInstance() = DetailsFragment()
    }
}
