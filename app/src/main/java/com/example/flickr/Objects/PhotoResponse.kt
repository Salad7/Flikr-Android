package com.example.flickr.Objects

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoResponse(val photo : List<Photo>) {
}