package com.example.myapplication.ui.main.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.FragmentSecondBinding
import com.example.myapplication.ui.main.data.NasaResult

class SecondFragment(nasaResult: NasaResult) : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private val marsData = nasaResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide
            .with(binding.photoFullScreen.context)
            .load(marsData.image)
            .into(binding.photoFullScreen)

        binding.date.text = marsData.earthDate.toString()
        binding.roverName.text = marsData.rover?.name.toString()
        binding.camName.text = marsData.camera?.name.toString()


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}