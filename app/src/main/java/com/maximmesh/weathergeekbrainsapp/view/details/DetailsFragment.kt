package com.maximmesh.weathergeekbrainsapp.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import coil.request.ImageRequest
import com.maximmesh.weathergeekbrainsapp.databinding.FragmentDetailsBinding
import com.maximmesh.weathergeekbrainsapp.repository.Weather
import com.maximmesh.weathergeekbrainsapp.utils.KEY_BUNDLE_WEATHER
import com.maximmesh.weathergeekbrainsapp.viewmodel.DetailsState
import com.maximmesh.weathergeekbrainsapp.viewmodel.DetailsState.Success
import com.maximmesh.weathergeekbrainsapp.viewmodel.DetailsViewModel

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

    private val viewModel: DetailsViewModel by lazy {
        ViewModelProvider(this).get(DetailsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLiveData().observe(
            viewLifecycleOwner
        ) { t -> renderData(t) }

        arguments?.getParcelable<Weather>(KEY_BUNDLE_WEATHER)?.let {
            viewModel.getWeather(it.city)
        }
    }

    private fun renderData(detailsState: DetailsState) {

        when (detailsState) {

           is DetailsState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }

            is DetailsState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                Toast.makeText(requireContext(),
                    "Упс... Ошибка...\n${detailsState.error}",
                    Toast.LENGTH_LONG)
                    .show()
            }

            is Success -> {
                val weather = detailsState.weather
                with(binding) {
                    loadingLayout.visibility = View.GONE
                    cityName.text = weather.city.name
                    temperatureValue.text = weather.temperature.toString()
                    feelsLikeValue.text = weather.feelsLike.toString()
                    cityCoordinates.text = "${weather.city.lat} ${weather.city.lon}"

/*                    Glide.with(requireContext()) //загружаем картинку с помощью Glide
                        .load("https://freepngclipart.com/download/building/49257-building-city-silhouette-skyline-york-cityscape.png")
                        .into(headerIcon)*/

                    /* Picasso.get()?.load("https://freepngclipart.com/download/building/49257-building-city-silhouette-skyline-york-cityscape.png")
                        ?.into(headerIcon)   //загружаем картинку с помощью Picasso*/

                    headerCityIcon.load("https://freepngclipart.com/download/building/49257-building-city-silhouette-skyline-york-cityscape.png")
                    //загрузил картинку с помощью Coil

                    icon.loadSvg("https://yastatic.net/weather/i/icons/blueye/color/svg/${weather.icon}.svg")
                    //загрузил svg картинку с помощью Coil благодаря функции loadSvg
                }
            }
        }
    }

        private fun ImageView.loadSvg(url:String){
            val imageLoader = ImageLoader.Builder(this.context)
                .componentRegistry { add(SvgDecoder(this@loadSvg.context)) }
                .build()
            val request = ImageRequest.Builder(this.context)
                .crossfade(true)
                .crossfade(500)
                .data(url)
                .target(this)
                .build()
            imageLoader.enqueue(request)
        }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
