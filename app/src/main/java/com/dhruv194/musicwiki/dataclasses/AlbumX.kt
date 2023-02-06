package com.dhruv194.musicwiki.dataclasses


import com.google.gson.annotations.SerializedName

data class AlbumX(
    val artist: String,
    val image: List<ImageXXX>,
    val listeners: String,
    val mbid: String,
    val name: String,
    val playcount: String,
    val tags: Tags,
    val tracks: TracksX,
    val url: String,
    val wiki: WikiX
)