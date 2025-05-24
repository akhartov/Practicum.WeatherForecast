package com.practicum.weatherforecast

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter() : RecyclerView.Adapter<MovieDescriptionViewHolder>() {
    var movies = ArrayList<Movie>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
        get() = field

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieDescriptionViewHolder {
        return MovieDescriptionViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieDescriptionViewHolder, position: Int) {
        holder.bind(movies[position])
        holder.itemView.setOnClickListener {
            // TODO: implement
        }
    }
}