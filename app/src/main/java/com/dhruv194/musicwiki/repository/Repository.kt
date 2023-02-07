package com.dhruv194.musicwiki.repository

import com.dhruv194.musicwiki.api.RetrofitInstance
import com.dhruv194.musicwiki.dataclasses.*
import retrofit2.Response

class Repository {
    suspend fun getTopTags(): Response<genre>{
        return RetrofitInstance.api.getTopTags()
    }

    suspend fun getTagInfo(tag:String): Response<genreInfo>{
        return RetrofitInstance.api.getTagInfo(tag)
    }

    suspend fun getTagAlbum(tag:String):Response<genreAlbums>{
        return RetrofitInstance.api.getTopAlbums(tag)
    }

    suspend fun getTagArtist(tag:String):Response<genreArtists>{
        return RetrofitInstance.api.getTopArtists(tag)
    }

    suspend fun getTagTracks(tag: String):Response<genreTracks>{
        return RetrofitInstance.api.getTopTracks(tag)
    }

    suspend fun getAlbumInfo(artist:String, album:String):Response<albumInfo>{
        return RetrofitInstance.api.getAlbumInfo(artist, album )
    }

    suspend fun getArtistInfo(artist: String):Response<artistInfo>{
        return RetrofitInstance.api.getArtistInfo(artist)
    }

    suspend fun getArtistTopAlbums(artist: String):Response<artistTopAlbums>{
        return RetrofitInstance.api.getArtistTopAlbums(artist)
    }

    suspend fun getArtistTopTracks(artist: String):Response<artistTopTracks>{
        return RetrofitInstance.api.getArtistTopTracks(artist)
    }
}