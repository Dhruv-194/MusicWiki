package com.dhruv194.musicwiki.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dhruv194.musicwiki.databinding.CardItemBinding
import com.dhruv194.musicwiki.dataclasses.ArtistX
import com.dhruv194.musicwiki.dataclasses.Track
import com.dhruv194.musicwiki.dataclasses.TrackXX
import com.dhruv194.musicwiki.dataclasses.artistTopTracks
import com.dhruv194.musicwiki.loadImage

class GenreTracksAdapter : RecyclerView.Adapter<GenreTracksAdapter.GenreTracksViewHolder>() {
    inner class GenreTracksViewHolder(val binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root)
    var genreTracks : List<Track> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreTracksViewHolder {
        return GenreTracksViewHolder(
            CardItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: GenreTracksViewHolder, position: Int) {



        holder.binding.apply {
            itemTitle.text = genreTracks[position].name
            itemArtistName.text = genreTracks[position].artist.name
            itemImg.loadImage(genreTracks[position].image[0].text)
        }
    }

    override fun getItemCount(): Int {
        return genreTracks.size
    }
}