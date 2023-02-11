package com.dhruv194.musicwiki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dhruv194.musicwiki.adapters.*
import com.dhruv194.musicwiki.api.RetrofitInstance
import com.dhruv194.musicwiki.databinding.ActivityAlbumInfoBinding
import com.dhruv194.musicwiki.databinding.ActivityArtistInfoBinding
import com.dhruv194.musicwiki.repository.Repository
import com.dhruv194.musicwiki.viewmodel.MainViewModel
import retrofit2.HttpException
import java.io.IOException

class ArtistInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArtistInfoBinding
    lateinit var albumInfoGenreAdapter: AlbumInfoGenreAdapter
     lateinit var artistTopTracksAdapter: ArtistTopTracksAdapter
     lateinit var artistTopAlbumsAdapter: ArtistTopAlbumsAdapter
     private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtistInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()
        setUpRecyclerView1()
        setUpRecyclerView2()

        val aints = intent
      //  val aname = aints.getStringExtra("ANAME")
        val arname = aints.getStringExtra("AR-NAME")

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getArtistInfo(arname.toString())
        viewModel.artistInfoResponse.observe(this) { response ->

            setUpRecyclerView()
            albumInfoGenreAdapter.albumInfoGenre = response.tags.tag
            binding.artistTitle.text = response.name
            binding.artistPlaycount.text = response.stats.playcount
            binding.artistListeners.text = response.stats.listeners
            binding.artistImg.loadImage(response.image[0].text)

        }
        /*lifecycleScope.launchWhenCreated {
            binding.artistInfoPb.isVisible = true
            val response  = try {
                RetrofitInstance.api.getArtistInfo(arname.toString())
            }catch (e: IOException){
                Log.d("TASG", "IOException "+e)
                binding.artistInfoPb.isVisible = false
                return@launchWhenCreated
            }catch (e : HttpException){
                Log.d("TASG", "HttpException "+e)
                binding.artistInfoPb.isVisible = false
                return@launchWhenCreated
            }

            if(response.isSuccessful && response.body()!=null){
                albumInfoGenreAdapter.albumInfoGenre = response.body()!!.artist.tags.tag
                binding.artistTitle.text = response.body()!!.artist.name
                binding.artistPlaycount.text = response.body()!!.artist.stats.playcount
                binding.artistListeners.text = response.body()!!.artist.stats.listeners
                binding.artistImg.loadImage(response.body()!!.artist.image[0].text)
            }else{
                Log.d("TASG", "Response not successful")
            }
            binding.artistInfoPb.isVisible = false
        }*/

        viewModel.getArtistTopTracks(arname.toString())
        viewModel.artistTopTracksResponse.observe(this) { topTracks ->
            setUpRecyclerView1()
            artistTopTracksAdapter.artistInfoTopTracks = topTracks
        }

        /*lifecycleScope.launchWhenCreated {
          //  binding.artistInfoPb.isVisible = true
            val response1  = try {
                RetrofitInstance.api.getArtistTopTracks(arname.toString())
            }catch (e: IOException){
                Log.d("TASG", "IOException "+e)
            //    binding.artistInfoPb.isVisible = false
                return@launchWhenCreated
            }catch (e : HttpException){
                Log.d("TASG", "HttpException "+e)
             //   binding.artistInfoPb.isVisible = false
                return@launchWhenCreated
            }

            if(response1.isSuccessful && response1.body()!=null){
                setUpRecyclerView1()
                artistTopTracksAdapter.artistInfoTopTracks = response1.body()!!.toptracks.track
            }else{
                Log.d("TASG", "Response not successful")
            }
         //   binding.artistInfoPb.isVisible = false
        }*/

        viewModel.getArtistTopAlbums(arname.toString())
        viewModel.artistTopAlbumsResponse.observe(this) { topAlbums ->
            setUpRecyclerView2()
            artistTopAlbumsAdapter.artistTopAlbums = topAlbums
        }

        /*lifecycleScope.launchWhenCreated {
            //  binding.artistInfoPb.isVisible = true
            val response2 = try {

                RetrofitInstance.api.getArtistTopAlbums(arname.toString())
            }catch (e: IOException){
                Log.d("TASG", "IOException "+e)
                //    binding.artistInfoPb.isVisible = false
                return@launchWhenCreated
            }catch (e : HttpException){
                Log.d("TASG", "HttpException "+e)
                //   binding.artistInfoPb.isVisible = false
                return@launchWhenCreated
            }

            if(response2.isSuccessful && response2.body()!=null){
                setUpRecyclerView2()
                artistTopAlbumsAdapter.artistTopAlbums = response2.body()!!.topalbums.album
            }else{
                Log.d("TASG", "Response not successful")
            }
            //   binding.artistInfoPb.isVisible = false
        }*/
    }
    private fun setUpRecyclerView()  = binding.artistInfoGenreRv.apply {
        albumInfoGenreAdapter = AlbumInfoGenreAdapter(this@ArtistInfoActivity)
        adapter = albumInfoGenreAdapter
        layoutManager = LinearLayoutManager(this@ArtistInfoActivity, LinearLayoutManager.HORIZONTAL,false)
    }

    private fun setUpRecyclerView1() = binding.artistInfoToptracksRv.apply {
        artistTopTracksAdapter = ArtistTopTracksAdapter()
        adapter = artistTopTracksAdapter
        layoutManager = LinearLayoutManager(this@ArtistInfoActivity, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setUpRecyclerView2() = binding.artistInfoTopalbumsRv.apply {
       artistTopAlbumsAdapter = ArtistTopAlbumsAdapter(this@ArtistInfoActivity)
        adapter = artistTopAlbumsAdapter
        layoutManager = LinearLayoutManager(this@ArtistInfoActivity, LinearLayoutManager.HORIZONTAL, false)
    }
}