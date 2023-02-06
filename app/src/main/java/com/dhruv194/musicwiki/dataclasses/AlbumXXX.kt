package com.dhruv194.musicwiki.dataclasses


import com.google.gson.annotations.SerializedName

data class AlbumXXX(
    val artist: ArtistXXXXXXXX,
    val image: List<ImageXXXXXXX>,
    val mbid: String,
    val name: String,
    val playcount: Int,
    val url: String
)