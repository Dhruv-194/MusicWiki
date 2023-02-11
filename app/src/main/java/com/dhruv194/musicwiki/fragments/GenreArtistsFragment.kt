package com.dhruv194.musicwiki.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dhruv194.musicwiki.MainViewModelFactory
import com.dhruv194.musicwiki.R
import com.dhruv194.musicwiki.adapters.GenreAlbumsAdapter
import com.dhruv194.musicwiki.adapters.GenreArtistsAdapter
import com.dhruv194.musicwiki.api.RetrofitInstance
import com.dhruv194.musicwiki.databinding.FragmentGenreArtistsBinding
import com.dhruv194.musicwiki.repository.Repository
import com.dhruv194.musicwiki.viewmodel.MainViewModel
import retrofit2.HttpException
import java.io.IOException


class GenreArtistsFragment(var genreName: String) : Fragment() {

    private lateinit var binding: FragmentGenreArtistsBinding
    private lateinit var genreArtistsAdapter: GenreArtistsAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      binding = FragmentGenreArtistsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getTagTopArtists(genreName.toString())
        viewModel.tagTopArtistsResposne.observe(viewLifecycleOwner) { topArtists ->
            setupRecyclerView()
            genreArtistsAdapter.genreArtists = topArtists
        }

        /* lifecycleScope.launchWhenCreated {
             binding.artistsRvPb.isVisible = true
             val response  = try {
                 RetrofitInstance.api.getTopArtists(genreName)
             }catch (e: IOException){
                 binding.artistsRvPb.isVisible = false
                 return@launchWhenCreated
             }catch (e : HttpException){
                 Log.d("TASG", "HttpException "+e)
                 binding.artistsRvPb.isVisible = false
                 return@launchWhenCreated
             }

             if(response.isSuccessful && response.body()!=null){
                 genreArtistsAdapter.genreArtists = response.body()!!.topartists.artist
             }else{
                 Log.d("TASG", "Response not successful")
             }
             binding.artistsRvPb.isVisible = false
         }*/
    }

    private fun setupRecyclerView() =binding.artistsRv.apply {
        genreArtistsAdapter = GenreArtistsAdapter(context)
        adapter = genreArtistsAdapter
        layoutManager = LinearLayoutManager(context)
    }
}