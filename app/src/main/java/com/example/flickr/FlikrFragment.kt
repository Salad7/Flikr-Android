package com.example.flickr

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.flickr.ViewModels.FlickerFragmentViewModel
import com.example.flickr.databinding.FragmentFlickrBinding

class FlikrFragment : Fragment() {
    lateinit var binding : FragmentFlickrBinding
    lateinit var viewModel :FlickerFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v = inflater.inflate(R.layout.fragment_flickr,container,false)
        binding = FragmentFlickrBinding.bind(v)
        viewModel = FlickerFragmentViewModel()
        return binding.root
    }
}