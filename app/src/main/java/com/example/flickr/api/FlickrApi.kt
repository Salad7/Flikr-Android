package com.example.flickr.api

import retrofit2.http.GET

interface FlickrApi {

//    flikerandroid
//    Key:
//    e06eaecc33ddf4604ee6dc1c9cea0879

//    Secret:
//    c06434bcffd4af08

    //?method=flickr.interestingness.getList&api_key=e06eaecc33ddf4604ee6dc1c9cea0879&format=json&nojsoncallback=1

    @GET("?method=flickr.interestingness.getList&api_key=e06eaecc33ddf4604ee6dc1c9cea0879&format=json&nojsoncallback=1")
    suspend fun fetchContents() :String

}