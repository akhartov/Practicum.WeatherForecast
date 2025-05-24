package com.practicum.weatherforecast

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide

class MovieDescriptionViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_movie_description, parent, false)
    ) {
    var image: ImageView = itemView.findViewById(R.id.cover)
    var title: TextView = itemView.findViewById(R.id.title)
    var info: TextView = itemView.findViewById(R.id.description)

    fun bind(movie: Movie) {
        title.setText(movie.title)
        info.setText(movie.description)

        Glide.with(itemView.context)
            .load(Uri.parse(movie.image))
            .fitCenter()
            .into(image)
    }
}