package com.dhruv194.musicwiki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dhruv194.musicwiki.adapters.TopGenreAdapter
import com.dhruv194.musicwiki.api.RetrofitInstance
import com.dhruv194.musicwiki.databinding.ActivityMainBinding
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpRetryException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var genreAdapter: TopGenreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()

        lifecycleScope.launchWhenCreated {
            binding.genreRvPb.isVisible = true
            val response  = try {
                RetrofitInstance.api.getTopTags()
            }catch (e: IOException){
                Log.d("TASG", "IOException "+e)
                binding.genreRvPb.isVisible = false
                return@launchWhenCreated
            }catch (e : HttpException){
                Log.d("TASG", "HttpException "+e)
                binding.genreRvPb.isVisible = false
                return@launchWhenCreated
            }

            if(response.isSuccessful && response.body()!=null){
                genreAdapter.genres = response.body()!!.toptags.tag
            }else{
                Log.d("TASG", "Response not successful")
            }
            binding.genreRvPb.isVisible = false
        }
    }

    private fun setUpRecyclerView()  = binding.genreRv.apply {
        genreAdapter = TopGenreAdapter(context)
        adapter = genreAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }
}