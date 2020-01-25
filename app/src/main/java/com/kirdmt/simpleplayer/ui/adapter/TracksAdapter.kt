package com.kirdmt.simpleplayer.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.kirdmt.simpleplayer.R
import com.kirdmt.simpleplayer.data.TrackSearchResult
import com.kirdmt.simpleplayer.data.TrackSearchResults
import com.kirdmt.simpleplayer.databinding.TrackItemBinding
import com.squareup.picasso.Picasso


class TracksAdapter(
    private val context: Context,
    private var trackSearchResults: TrackSearchResults,
    private var listener: OnItemClickListener

) :
    RecyclerView.Adapter<TracksAdapter.ViewHolder>() {

    private var items: List<TrackSearchResult> = trackSearchResults.getList()

    /*init{

    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TrackItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Picasso.with(context)
            .load(items.get(position).artworkUrl100)
            .placeholder(R.drawable.ic_search_white_24dp)
            .into(holder.albumImageView)

        holder.bind(items.get(position), listener)
    }

    override fun getItemCount(): Int = items.size

    interface OnItemClickListener {
        fun onItemClick(position: Int, trackSearchResult: TrackSearchResult)
    }

    inner class ViewHolder(private var binding: TrackItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var albumImageView: ImageView = binding.albumImg

        fun bind(tracks: TrackSearchResult?, listener: OnItemClickListener?) {
            binding.track = tracks

            if (listener != null) {
                binding.root.setOnClickListener({ _ -> listener.onItemClick(layoutPosition, items.get(layoutPosition)) })
            }

            binding.executePendingBindings()
        }
    }


}