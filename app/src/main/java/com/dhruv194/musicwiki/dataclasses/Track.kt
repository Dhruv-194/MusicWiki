package com.dhruv194.musicwiki.dataclasses


import com.google.gson.annotations.SerializedName

data class Track(
    val artist: ArtistXX,
    @SerializedName("@attr")
    val attr: AttrXXXXXX,
    val duration: String,
    val image: List<ImageXX>,
    val mbid: String,
    val name: String,
    val streamable: Streamable,
    val url: String
)