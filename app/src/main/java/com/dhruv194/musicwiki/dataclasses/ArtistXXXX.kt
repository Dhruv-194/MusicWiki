package com.dhruv194.musicwiki.dataclasses


import com.google.gson.annotations.SerializedName

data class ArtistXXXX(
    val bio: Bio,
    val image: List<ImageXXXX>,
    val mbid: String,
    val name: String,
    val ontour: String,
    val similar: Similar,
    val stats: Stats,
    val streamable: String,
    val tags: Tags,
    val url: String
)