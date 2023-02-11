package com.dhruv194.musicwiki.viewmodel

import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dhruv194.musicwiki.dataclasses.*
import com.dhruv194.musicwiki.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    //not exposing the MutableLiveData
    private val _topTagResponse: MutableLiveData<List<Tag>> = MutableLiveData()
    private val _tagInfoResponse : MutableLiveData<TagX> = MutableLiveData()
    private val _tagTopAlbumsResponse : MutableLiveData<List<Album>> = MutableLiveData()
    private val _tagTopArtistsResposne : MutableLiveData<List<ArtistX>> = MutableLiveData()
    private val _tagTopTracksResponse : MutableLiveData<List<Track>> = MutableLiveData()
    private val _albumInfoResponse: MutableLiveData<AlbumX> = MutableLiveData()
    private val _artistInfoResponse : MutableLiveData<ArtistXXXX> = MutableLiveData()
    private val _artistTopAlbumsResponse : MutableLiveData<List<AlbumXXX>> = MutableLiveData()
    private val _artistTopTracksResponse : MutableLiveData<List<TrackXX>> = MutableLiveData()

    //only exposing the LiveData
    val topTagResponse : LiveData<List<Tag>> = _topTagResponse
    val tagInfoResponse: LiveData<TagX> = _tagInfoResponse
    val tagTopAlbumsResponse: LiveData<List<Album>> = _tagTopAlbumsResponse
    val tagTopArtistsResposne: LiveData<List<ArtistX>> = _tagTopArtistsResposne
    val tagTopTracksResponse: LiveData<List<Track>> = _tagTopTracksResponse
    val albumInfoResponse: LiveData<AlbumX> = _albumInfoResponse
    val artistInfoResponse: LiveData<ArtistXXXX> = _artistInfoResponse
    val artistTopAlbumsResponse: LiveData<List<AlbumXXX>> = _artistTopAlbumsResponse
    val artistTopTracksResponse: LiveData<List<TrackXX>> = _artistTopTracksResponse

    init {
        reloadTopTags()
    }
    private fun reloadTopTags(){
        viewModelScope.launch {
            val response = repository.getTopTags()
            if(response.isSuccessful){
                _topTagResponse.value = response.body()!!.toptags.tag
            }
        }
    }

    fun getTagInfo(tag : String){
        viewModelScope.launch {
            val response = repository.getTagInfo(tag)
            if(response.isSuccessful){
                _tagInfoResponse.value = response.body()!!.tag
            }

        }
    }

    fun getTagTopAlbums(tag:String){
        viewModelScope.launch {
            val response = repository.getTagAlbum(tag)
            if (response.isSuccessful){
                _tagTopAlbumsResponse.value = response.body()!!.albums.album
            }

        }
    }

    fun getTagTopArtists(tag:String){
        viewModelScope.launch {
            val response = repository.getTagArtist(tag)
            if(response.isSuccessful){
                _tagTopArtistsResposne.value = response.body()!!.topartists.artist
            }

        }
    }

    fun getTagTopTracks(tag: String){
        viewModelScope.launch {
            val response= repository.getTagTracks(tag)
            if(response.isSuccessful){
                _tagTopTracksResponse.value = response.body()!!.tracks.track
            }

        }
    }

    fun getAlbumInfo(artist:String, album:String){
        viewModelScope.launch {
            val response = repository.getAlbumInfo(artist, album)
            if (response.isSuccessful){
                _albumInfoResponse.value = response.body()!!.album
            }

        }
    }

    fun getArtistInfo(artist: String){
        viewModelScope.launch {
            val response = repository.getArtistInfo(artist)
            if (response.isSuccessful){
                _artistInfoResponse.value = response.body()!!.artist
            }

        }
    }

    fun getArtistTopAlbums(artist:String){
        viewModelScope.launch{
            val response = repository.getArtistTopAlbums(artist)
            if (response.isSuccessful){
                _artistTopAlbumsResponse.value = response.body()!!.topalbums.album
            }

        }
    }

    fun getArtistTopTracks(artist:String){
        viewModelScope.launch{
            val response = repository.getArtistTopTracks(artist)
            if(response.isSuccessful){
                _artistTopTracksResponse.value = response.body()!!.toptracks.track
            }

        }
    }
}