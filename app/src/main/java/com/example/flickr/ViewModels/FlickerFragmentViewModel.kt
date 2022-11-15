package com.example.flickr.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.flickr.Objects.PhotoResponse
import com.example.flickr.Objects.PhotosRootResponse
import com.example.flickr.api.FlickrApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create

class FlickerFragmentViewModel : ViewModel() {
    var flikrMutableStateFlow = MutableStateFlow(PhotosRootResponse(PhotoResponse(listOf())))
    var immutableFlickrStateFlow = flikrMutableStateFlow.asStateFlow()
    var retrofit = Retrofit.Builder()
        .baseUrl("https://www.flickr.com/services/rest/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    init {

    }

    suspend fun fetchData(){
        var api = retrofit.create<FlickrApi>()
        Log.d("FlickerFragmentVM",api.fetchContents().toString())
        flikrMutableStateFlow.value = api.fetchContents()
    }
}