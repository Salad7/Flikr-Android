package com.example.flickr.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.flickr.api.FlickrApi
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create

class FlickerFragmentViewModel : ViewModel() {
    var retrofit = Retrofit.Builder()
        .baseUrl("https://www.flickr.com/services/rest/")
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()
    init {

    }

    suspend fun fetchData(){
        var api = retrofit.create<FlickrApi>()
        Log.d("FlickerFragmentVM",api.fetchContents())
    }
}