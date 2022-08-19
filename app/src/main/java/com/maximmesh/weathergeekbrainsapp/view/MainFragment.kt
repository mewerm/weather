package com.maximmesh.weathergeekbrainsapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.maximmesh.weathergeekbrainsapp.R
import com.maximmesh.weathergeekbrainsapp.databinding.FragmentMainBinding
import com.maximmesh.weathergeekbrainsapp.viewmodel.MainViewModel

class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding //утечка памяти

    override fun onDestroy() {
        super.onDestroy()
//        binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMainBinding.inflate(inflater,container,false)
        binding.btnOne.setOnClickListener{}

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java )

        val observer = object: Observer<Any> {
            override fun onChanged(data: Any) {

                renderData(data)
            }
        }
        viewModel.getData().observe(viewLifecycleOwner, observer)

        viewModel.getWeather()
    }

    private fun renderData(data:Any){
        Toast.makeText(requireContext(), "Работает", Toast.LENGTH_SHORT).show()

    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}
