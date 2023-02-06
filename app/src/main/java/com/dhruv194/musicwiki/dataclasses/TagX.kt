package com.dhruv194.musicwiki.dataclasses


import com.google.gson.annotations.SerializedName

data class TagX(
    val name: String,
    val reach: Int,
    val total: Int,
    val wiki: Wiki
)