package com.dhruv194.musicwiki.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api : genreApi by lazy{
        Retrofit.Builder()
            .baseUrl("https://ws.audioscrobbler.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(genreApi::class.java)
    }
}