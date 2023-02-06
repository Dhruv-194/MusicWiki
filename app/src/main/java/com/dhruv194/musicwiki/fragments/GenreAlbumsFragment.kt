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
import com.dhruv194.musicwiki.adapters.GenreAlbumsAdapter
import com.dhruv194.musicwiki.api.RetrofitInstance
import com.dhruv194.musicwiki.databinding.FragmentGenreAlbumsBinding
import retrofit2.HttpException
import java.io.IOException


class GenreAlbumsFragment(var genreName: String) : Fragment() {

    private lateinit var binding: FragmentGenreAlbumsBinding
    private lateinit var genreAlbumsAdapter: GenreAlbumsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =FragmentGenreAlbumsBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        lifecycleScope.launchWhenCreated {
            binding.albumsRvPb.isVisible = true
            val response  = try {
                RetrofitInstance.api.getTopAlbums(genreName)
            }catch (e: IOException){
                binding.albumsRvPb.isVisible = false
                return@launchWhenCreated
            }catch (e : HttpException){
                Log.d("TASG", "HttpException "+e)
                binding.albumsRvPb.isVisible = false
                return@launchWhenCreated
            }

            if(response.isSuccessful && response.body()!=null){
                genreAlbumsAdapter.genreAlbums = response.body()!!.albums.album
            }else{
                Log.d("TASG", "Response not successful")
            }
            binding.albumsRvPb.isVisible = false
        }
    }

    private fun setupRecyclerView() =binding.albumsRv.apply {
        genreAlbumsAdapter = GenreAlbumsAdapter(context)
        adapter = genreAlbumsAdapter
        layoutManager = LinearLayoutManager(context)
    }
}