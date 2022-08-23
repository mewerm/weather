package com.maximmesh.weathergeekbrainsapp.view.weatherlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.maximmesh.weathergeekbrainsapp.R
import com.maximmesh.weathergeekbrainsapp.databinding.FragmentWeatherListBinding
import com.maximmesh.weathergeekbrainsapp.viewmodel.AppState
import com.maximmesh.weathergeekbrainsapp.viewmodel.MainViewModel

class WeatherListFragment : Fragment() {

    //создаем две ссылки binding на один объект: _binding(Null) binding(notNull): чтобы небыло утечки памяти
    private var _binding: FragmentWeatherListBinding? = null //переменная _binding которая может быть null

    private val binding: FragmentWeatherListBinding
        get() {
            return _binding!! //точно не null
        }

    val adapter = WeatherListAdapter()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null //переменная _binding которая может быть null ее можно занулить, ее необходимо занулить
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherListBinding.inflate(inflater, container, false)

        return binding.root //а тут binding возвращается NotNull и ниже используется NotNull binding
    }

    private var isRussian = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val observer = object : Observer<AppState> {
            override fun onChanged(data: AppState) {

                renderData(data)
            }
        }
        viewModel.getData().observe(viewLifecycleOwner, observer)

        binding.floatingActionButton.setOnClickListener {
            isRussian = !isRussian
            if (isRussian) {
                viewModel.getWeatherRussia()
                binding.floatingActionButton.setImageDrawable(
                    ContextCompat.getDrawable(requireContext(), R.drawable.ru)
                )

            } else {
                viewModel.getWeatherWorld()
                binding.floatingActionButton.setImageDrawable(
                    ContextCompat.getDrawable(requireContext(), R.drawable.world)
                )
                viewModel.getWeatherWorld()
            }
        }
        viewModel.getWeatherRussia()
    }

    private fun renderData(data: AppState) {
        when (data) {
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                Snackbar.make(binding.root, "Не получилось ${data.error}", Snackbar.LENGTH_LONG).show()
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE

            }
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                adapter.setData(data.weatherList)

                /* binding.cityName.text = data.weatherData.city.name
                 binding.cityCoordinates.text = "${data.weatherData.city.lat} ${data.weatherData.city.lon}"
                 binding.temperatureValue.text = data.weatherData.temperature.toString()
                 binding.feelsLikeValue.text = data.weatherData.feelsLike.toString()
                 Snackbar.make(binding.mainView, "Получилось", Snackbar.LENGTH_LONG).show()
 */
            }

        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = WeatherListFragment()
    }
}
