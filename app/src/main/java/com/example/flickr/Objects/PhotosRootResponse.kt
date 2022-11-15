package com.example.flickr.Objects

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotosRootResponse(val photos : PhotoResponse) {
}