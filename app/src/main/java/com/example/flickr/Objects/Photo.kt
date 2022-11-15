package com.example.flickr.Objects

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Photo(@Json(name="id") val photoID :String,
                 @Json(name="owner") val owner :String,
                 @Json(name="secret") val secret :String,
                 @Json(name="farm") val farm :String,
                 @Json(name="server") val server :String)  {
    var pid = photoID

    fun getImageUrl() : String {
        return "https://live.staticflickr.com/"+server+"/"+photoID+"_"+secret+"_w.jpg"
    }

}