package com.dhruv194.musicwiki.dataclasses


import com.google.gson.annotations.SerializedName

data class Topartists(
    val artist: List<ArtistX>,
    @SerializedName("@attr")
    val attr: AttrXXXX
)