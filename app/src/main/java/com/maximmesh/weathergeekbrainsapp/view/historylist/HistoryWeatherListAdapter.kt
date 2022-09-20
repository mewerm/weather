package com.maximmesh.weathergeekbrainsapp.view.weatherlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.maximmesh.weathergeekbrainsapp.databinding.FragmentHistoryWeatherListRecyclerItemBinding
import com.maximmesh.weathergeekbrainsapp.repository.Weather
import kotlinx.android.synthetic.main.fragment_details.view.*

class HistoryWeatherListAdapter(
    private var data: List<Weather> = listOf()
) :
    RecyclerView.Adapter<HistoryWeatherListAdapter.CityHolder>() {

    fun setData(dataNew: List<Weather>) {
        this.data = dataNew
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
        val binding = FragmentHistoryWeatherListRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class CityHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(weather: Weather) {
            FragmentHistoryWeatherListRecyclerItemBinding.bind(itemView).apply {
                tvCityName.text = weather.city.name
                tvTemperature.text = weather.temperature.toString()
                tvFeelsLike.text = weather.feelsLike.toString()
            }
        }
    }
}