package com.dhruv194.musicwiki.dataclasses


import com.google.gson.annotations.SerializedName

data class TrackXX(
    val artist: ArtistXXXXXX,
    @SerializedName("@attr")
    val attr: AttrXXXXXXXXX,
    val image: List<ImageXXXXX>,
    val listeners: String,
    val mbid: String,
    val name: String,
    val playcount: String,
    val streamable: String,
    val url: String
)