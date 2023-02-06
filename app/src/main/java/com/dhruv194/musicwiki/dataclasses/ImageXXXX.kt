package com.dhruv194.musicwiki.dataclasses


import com.google.gson.annotations.SerializedName

data class ImageXXXX(
    val size: String,
    @SerializedName("#text")
    val text: String
)