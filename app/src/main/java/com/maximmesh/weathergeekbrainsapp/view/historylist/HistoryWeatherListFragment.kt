package com.maximmesh.weathergeekbrainsapp.view.weatherlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.maximmesh.weathergeekbrainsapp.R
import com.maximmesh.weathergeekbrainsapp.databinding.FragmentHistoryWeatherListBinding
import com.maximmesh.weathergeekbrainsapp.databinding.FragmentWeatherListBinding
import com.maximmesh.weathergeekbrainsapp.repository.Weather
import com.maximmesh.weathergeekbrainsapp.utils.KEY_BUNDLE_WEATHER
import com.maximmesh.weathergeekbrainsapp.view.details.DetailsFragment
import com.maximmesh.weathergeekbrainsapp.viewmodel.AppState
import com.maximmesh.weathergeekbrainsapp.viewmodel.HistoryViewModel
import com.maximmesh.weathergeekbrainsapp.viewmodel.MainViewModel

class HistoryWeatherListFragment : Fragment(){

    //создаем две ссылки binding на один объект: _binding(Null) binding(notNull): чтобы небыло утечки памяти
    private var _binding: FragmentHistoryWeatherListBinding? = null //переменная _binding которая может быть null

    private val binding: FragmentHistoryWeatherListBinding
        get() {
            return _binding!! //точно не null
        }

    private val adapter = HistoryWeatherListAdapter()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null //переменная _binding которая может быть null ее можно занулить, ее необходимо занулить
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryWeatherListBinding .inflate(inflater, container, false)

        return binding.root //а тут binding возвращается NotNull и ниже используется NotNull binding
    }

    private var isRussian = true

    private val viewModel:HistoryViewModel by lazy{
        ViewModelProvider (this)[HistoryViewModel::class.java]
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter

        val observer = { data: AppState -> renderData(data) }
        viewModel.getData().observe(viewLifecycleOwner, observer)
        viewModel.getAll()
    }

      private fun renderData(data: AppState) {
        when (data) {
            is AppState.Error -> {
                //binding.loadingLayout.visibility = View.GONE
                Snackbar.make(binding.root, "Не получилось ${data.error}", Snackbar.LENGTH_LONG).show()
            }
            is AppState.Loading -> {
                //binding.loadingLayout.visibility = View.VISIBLE

            }
            is AppState.Success -> {
               //  binding.loadingLayout.visibility = View.GONE
                adapter.setData(data.weatherList)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = HistoryWeatherListFragment()
    }
}



