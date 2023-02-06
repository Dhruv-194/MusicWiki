package com.dhruv194.musicwiki.dataclasses


import com.google.gson.annotations.SerializedName

data class Bio(
    val content: String,
    val links: Links,
    val published: String,
    val summary: String
)