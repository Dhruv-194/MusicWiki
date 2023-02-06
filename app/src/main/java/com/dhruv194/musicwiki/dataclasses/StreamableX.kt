package com.dhruv194.musicwiki.dataclasses


import com.google.gson.annotations.SerializedName

data class StreamableX(
    val fulltrack: String,
    @SerializedName("#text")
    val text: String
)