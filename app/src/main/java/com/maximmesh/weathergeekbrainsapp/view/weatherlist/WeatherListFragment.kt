package com.maximmesh.weathergeekbrainsapp.view.weatherlist

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.maximmesh.weathergeekbrainsapp.R
import com.maximmesh.weathergeekbrainsapp.databinding.FragmentWeatherListBinding
import com.maximmesh.weathergeekbrainsapp.repository.City
import com.maximmesh.weathergeekbrainsapp.repository.Weather
import com.maximmesh.weathergeekbrainsapp.utils.KEY_BUNDLE_WEATHER
import com.maximmesh.weathergeekbrainsapp.view.details.DetailsFragment
import com.maximmesh.weathergeekbrainsapp.viewmodel.AppState
import com.maximmesh.weathergeekbrainsapp.viewmodel.MainViewModel
import java.util.*

class WeatherListFragment : Fragment(), OnItemListClickListener {

    //создаем две ссылки binding на один объект: _binding(Null) binding(notNull): чтобы небыло утечки памяти
    private var _binding: FragmentWeatherListBinding? = null //переменная _binding которая может быть null

    private val binding: FragmentWeatherListBinding
        get() {
            return _binding!! //точно не null
        }

    private val adapter = WeatherListAdapter(this)

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

    private val viewModel:MainViewModel by lazy{
        ViewModelProvider (this)[MainViewModel::class.java]
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter

        val observer = { data: AppState -> renderData(data) }
        viewModel.getData().observe(viewLifecycleOwner, observer)

        setUpFabCities()
        setUpFabLocation()
        viewModel.getWeatherRussia()
    }

    private fun setUpFabLocation(){
        binding.mainFragmentFABLocation.setOnClickListener {
                checkPermission() //спрашиваем разрешение на каждое нажатие кнопки, тк пользователь мог в настройках это разрешение убрать
        }
    }

    private fun checkPermission() {
        // а есть ли разрешение?
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            getLocation()
        } else if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)) {
            // важно написать убедительную просьбу
            explain()
        } else {
            mRequestPermission()
        }
    }

    private fun explain() {
        AlertDialog.Builder(requireContext())
            .setTitle(resources.getString(R.string.dialog_rationale_title))
            .setMessage(resources.getString(R.string.dialog_rationale_message))
            .setPositiveButton(resources.getString(R.string.dialog_rationale_give_access)) { _, _ ->
                mRequestPermission()
            }
            .setNegativeButton(getString(R.string.dialog_rationale_decline)) { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

    private val REQUEST_CODE = 998
    private fun mRequestPermission() {
        requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE) {
            for (i in permissions.indices) {
                if (permissions[i] == Manifest.permission.ACCESS_FINE_LOCATION && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    getLocation()
                } else {
                    explain()
                }
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    fun getAddressByLocation(location: Location){
        val geocoder = Geocoder(requireContext(), Locale.getDefault())
        val timeStump = System.currentTimeMillis()
        Thread{
            val addressText = geocoder.getFromLocation(location.latitude,location.longitude,10000)[0].locality
            requireActivity().runOnUiThread {
                showAddressDialog(addressText,location)
            }
        }.start()
        Log.d("@@@"," прошло ${System.currentTimeMillis() - timeStump}")
    }

    private val locationListenerTime = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            Log.d("@@@",location.toString())
                getAddressByLocation(location)
        }
        override fun onProviderDisabled(provider: String) {
            super.onProviderDisabled(provider)
        }
        override fun onProviderEnabled(provider: String) {
            super.onProviderEnabled(provider)
        }

    }

    private val locationListenerDistance = object : LocationListener{
        override fun onLocationChanged(location: Location) {
            getAddressByLocation(location)
        }
        override fun onProviderDisabled(provider: String) {
            super.onProviderDisabled(provider)
        }
        override fun onProviderEnabled(provider: String) {
            super.onProviderEnabled(provider)
        }

    }

    @SuppressLint("MissingPermission")
    private fun getLocation(){
        context?.let {
            val locationManager = it.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                val providerGPS = locationManager.getProvider(LocationManager.GPS_PROVIDER) // можно использовать BestProvider
                providerGPS?.let{     //локация по времени
/*                    locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        10000L,
                        0f,
                        locationListenerTime
                    )*/
                }
                providerGPS?.let{
                    locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,          //локация по расстоянию
                        0,
                        100f,
                        locationListenerDistance
                    )
                }
            }
        }
    }

    private fun setUpFabCities() {
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
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = WeatherListFragment()
    }

    override fun onItemClick(weather: Weather) {
        requireActivity().supportFragmentManager.beginTransaction().add(
            R.id.container,
            DetailsFragment.newInstance(Bundle().apply {
                putParcelable(KEY_BUNDLE_WEATHER, weather)
            })
        ).addToBackStack("").commit()
    }

    private fun showAddressDialog(address: String, location: Location) {
        activity?.let {
            AlertDialog.Builder(it)
                .setTitle(getString(R.string.dialog_address_title))
                .setMessage(address)
                .setPositiveButton(getString(R.string.dialog_address_get_weather)) { _, _ ->
                    onItemClick(
                        Weather(
                            City(
                                address,
                                location.latitude,
                                location.longitude
                            )
                        )
                    )
                }
                .setNegativeButton(getString(R.string.dialog_button_close)) { dialog, _ -> dialog.dismiss() }
                .create()
                .show()
        }
    }

}



