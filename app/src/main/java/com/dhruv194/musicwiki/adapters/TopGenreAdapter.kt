package com.dhruv194.musicwiki.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dhruv194.musicwiki.GenreInfoActivity
import com.dhruv194.musicwiki.databinding.GenreItemBinding
import com.dhruv194.musicwiki.dataclasses.Tag
import com.dhruv194.musicwiki.dataclasses.genre

class TopGenreAdapter(var mContext: Context) : RecyclerView.Adapter<TopGenreAdapter.TopGenreViewHolder>() {

    inner class TopGenreViewHolder(val binding: GenreItemBinding) : RecyclerView.ViewHolder(binding.root)



    var genres: List<Tag> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopGenreViewHolder {
        return TopGenreViewHolder(GenreItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: TopGenreViewHolder, position: Int) {
      holder.binding.apply {
          val genre = genres[position]
          genreName.text = genres[position].name
      }
        holder.binding.root.setOnClickListener {
            val mIntent = Intent(mContext, GenreInfoActivity::class.java)
            mIntent.putExtra("NAME",genres[position].name)
            mContext.startActivity(mIntent)
        }
    }

    override fun getItemCount(): Int {
      return genres.size
    }


}
