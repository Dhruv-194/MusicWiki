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
import com.dhruv194.musicwiki.api.RetrofitInstance
import com.dhruv194.musicwiki.databinding.FragmentGenreAlbumsBinding
import com.dhruv194.musicwiki.repository.Repository
import com.dhruv194.musicwiki.viewmodel.MainViewModel
import retrofit2.HttpException
import java.io.IOException


class GenreAlbumsFragment(var genreName: String) : Fragment() {

    private lateinit var binding: FragmentGenreAlbumsBinding
    private lateinit var genreAlbumsAdapter: GenreAlbumsAdapter
    private lateinit var viewModel: MainViewModel

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

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getTagTopAlbums(genreName.toString())
        viewModel.tagTopAlbumsResponse.observe(viewLifecycleOwner, Observer { response->
            if(response.isSuccessful){
                setupRecyclerView()
                genreAlbumsAdapter.genreAlbums = response.body()!!.albums.album
            }
            else{
                Log.d("TAG", "error"+response.code())
            }
        })

       /* lifecycleScope.launchWhenCreated {
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
        }*/
    }

    private fun setupRecyclerView() =binding.albumsRv.apply {
        genreAlbumsAdapter = GenreAlbumsAdapter(context)
        adapter = genreAlbumsAdapter
        layoutManager = LinearLayoutManager(context)
    }
}