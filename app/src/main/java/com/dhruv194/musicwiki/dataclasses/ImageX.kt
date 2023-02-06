package com.dhruv194.musicwiki.dataclasses


import com.google.gson.annotations.SerializedName

data class ImageX(
    val size: String,
    @SerializedName("#text")
    val text: String
)