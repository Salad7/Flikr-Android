package com.example.flickr.ViewModels

import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class FlickerFragmentViewModelTest {
    var viewModel = FlickerFragmentViewModel()
    @Test
    suspend fun isFlikrImageReturned() {
//        var photoResponse = viewModel.fetchData()
        viewModel.immutableFlickrStateFlow.collect {
            assertEquals(it.photos.photo.size > 0, true)
        }
    }

    @Test
    fun quickTest() {
        assertEquals(0,0)
    }

    @Test
    suspend fun isFlick(): Nothing = runBlocking {
        var viewModel = FlickerFragmentViewModel()
        assertEquals(0,0)
        viewModel.immutableFlickrStateFlow.collect {
            assertEquals(it.photos.photo.size > 0, true)
        }

    }
}