package com.example.flickr.api

import com.example.flickr.Objects.FlickrResponse
import com.example.flickr.Objects.PhotosRootResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val apiKey = "e06eaecc33ddf4604ee6dc1c9cea0879"

interface FlickrApi {

//    flikerandroid
//    Key:
//    e06eaecc33ddf4604ee6dc1c9cea0879

//    Secret:
//    c06434bcffd4af08

    //?method=flickr.interestingness.getList&api_key=e06eaecc33ddf4604ee6dc1c9cea0879&format=json&nojsoncallback=1

    @GET("?method=flickr.interestingness.getList&api_key="+apiKey+"&format=json&nojsoncallback=1")
    suspend fun fetchContents() : PhotosRootResponse

    @GET("https://www.flickr.com/services/rest/?method=flickr.photos.search&api_key="+apiKey+"&format=json&nojsoncallback=1")
    suspend fun fetchByQuery(@Query("tags") query :String) : PhotosRootResponse
}