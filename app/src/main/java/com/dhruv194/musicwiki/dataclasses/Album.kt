package com.dhruv194.musicwiki.dataclasses


import com.google.gson.annotations.SerializedName

data class Album(
    val artist: Artist,
    @SerializedName("@attr")
    val attr: AttrX,
    val image: List<Image>,
    val mbid: String,
    val name: String,
    val url: String
)