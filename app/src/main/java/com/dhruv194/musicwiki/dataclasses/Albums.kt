package com.dhruv194.musicwiki.dataclasses


import com.google.gson.annotations.SerializedName

data class Albums(
    val album: List<Album>,
    @SerializedName("@attr")
    val attr: AttrXX
)