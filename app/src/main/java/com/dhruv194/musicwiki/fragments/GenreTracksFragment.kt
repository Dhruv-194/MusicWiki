package com.dhruv194.musicwiki.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dhruv194.musicwiki.R
import com.dhruv194.musicwiki.adapters.GenreArtistsAdapter
import com.dhruv194.musicwiki.adapters.GenreTracksAdapter
import com.dhruv194.musicwiki.api.RetrofitInstance
import com.dhruv194.musicwiki.databinding.FragmentGenreArtistsBinding
import com.dhruv194.musicwiki.databinding.FragmentGenreTracksBinding
import com.dhruv194.musicwiki.dataclasses.genreTracks
import retrofit2.HttpException
import java.io.IOException


class GenreTracksFragment (var genreName: String) : Fragment() {

    private lateinit var binding: FragmentGenreTracksBinding
    private lateinit var genreTracksAdapter: GenreTracksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGenreTracksBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        lifecycleScope.launchWhenCreated {
            binding.tracksRvPb.isVisible = true
            val response1  = try {
                RetrofitInstance.api.getTopTracks(genreName)
            }catch (e: IOException){
                binding.tracksRvPb.isVisible = false
                return@launchWhenCreated
            }catch (e : HttpException){
                Log.d("TASG", "HttpException "+e)
                binding.tracksRvPb.isVisible = false
                return@launchWhenCreated
            }

            if(response1.isSuccessful && response1.body()!=null){
                genreTracksAdapter.genreTracks = response1.body()!!.tracks.track
            }else{
                Log.d("TASG", "Response not successful")
            }
            binding.tracksRvPb.isVisible = false
        }
    }

    private fun setupRecyclerView() =binding.tracksRv.apply {
        genreTracksAdapter = GenreTracksAdapter()
        adapter = genreTracksAdapter
        layoutManager = LinearLayoutManager(context)
    }
}