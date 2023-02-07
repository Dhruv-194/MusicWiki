package com.dhruv194.musicwiki.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dhruv194.musicwiki.dataclasses.*
import com.dhruv194.musicwiki.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    val topTagResponse: MutableLiveData<Response<genre>> = MutableLiveData()
    val tagInfoResponse : MutableLiveData<Response<genreInfo>> = MutableLiveData()
    val tagTopAlbumsResponse : MutableLiveData<Response<genreAlbums>> = MutableLiveData()
    val tagTopArtistsResposne : MutableLiveData<Response<genreArtists>> = MutableLiveData()
    val tagTopTracksResponse : MutableLiveData<Response<genreTracks>> = MutableLiveData()
    val albumInfoResponse: MutableLiveData<Response<albumInfo>> = MutableLiveData()
    val artistInfoResponse : MutableLiveData<Response<artistInfo>> = MutableLiveData()
    val artistTopAlbumsResponse : MutableLiveData<Response<artistTopAlbums>> = MutableLiveData()
    val artistTopTracksResponse : MutableLiveData<Response<artistTopTracks>> = MutableLiveData()

    fun getTopTags(){
        viewModelScope.launch {
            val response = repository.getTopTags()
            topTagResponse.value = response
        }
    }

    fun getTagInfo(tag : String){
        viewModelScope.launch {
            val response = repository.getTagInfo(tag)
            tagInfoResponse.value = response
        }
    }

    fun getTagTopAlbums(tag:String){
        viewModelScope.launch {
            val response = repository.getTagAlbum(tag)
            tagTopAlbumsResponse.value = response
        }
    }

    fun getTagTopArtists(tag:String){
        viewModelScope.launch {
            val response = repository.getTagArtist(tag)
            tagTopArtistsResposne.value = response
        }
    }

    fun getTagTopTracks(tag: String){
        viewModelScope.launch {
            val response= repository.getTagTracks(tag)
            tagTopTracksResponse.value = response
        }
    }

    fun getAlbumInfo(artist:String, album:String){
        viewModelScope.launch {
            val response = repository.getAlbumInfo(artist, album)
            albumInfoResponse.value=response
        }
    }

    fun getArtistInfo(artist: String){
        viewModelScope.launch {
            val response = repository.getArtistInfo(artist)
            artistInfoResponse.value = response
        }
    }

    fun getArtistTopAlbums(artist:String){
        viewModelScope.launch{
            val response = repository.getArtistTopAlbums(artist)
            artistTopAlbumsResponse.value = response
        }
    }

    fun getArtistTopTracks(artist:String){
        viewModelScope.launch{
            val response = repository.getArtistTopTracks(artist)
            artistTopTracksResponse.value = response
        }
    }
}