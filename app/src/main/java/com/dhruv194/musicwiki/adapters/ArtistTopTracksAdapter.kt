package com.dhruv194.musicwiki.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dhruv194.musicwiki.databinding.CardItemBinding
import com.dhruv194.musicwiki.dataclasses.Track
import com.dhruv194.musicwiki.dataclasses.TrackXX
import com.dhruv194.musicwiki.loadImage

class ArtistTopTracksAdapter : RecyclerView.Adapter<ArtistTopTracksAdapter.AristTopTracksViewHolder>() {
    inner class AristTopTracksViewHolder(val binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root)

    var artistInfoTopTracks : List<TrackXX> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AristTopTracksViewHolder {
        return AristTopTracksViewHolder(
            CardItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))
    }

    override fun onBindViewHolder(holder: AristTopTracksViewHolder, position: Int) {
        holder.binding.apply {
            itemTitle.text = artistInfoTopTracks[position].name
            itemArtistName.text = artistInfoTopTracks[position].artist.name
            itemImg.loadImage(artistInfoTopTracks[position].image[0].text)
        }
    }

    override fun getItemCount(): Int {
        return artistInfoTopTracks.size
    }
}
