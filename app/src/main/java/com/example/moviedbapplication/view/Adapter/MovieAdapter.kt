package com.example.moviedbapplication.view.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedbapplication.R
import com.example.moviedbapplication.model.remote.moviedata.MovieData
import com.squareup.picasso.Picasso

class MovieAdapter( private val loadMore: () -> Unit , private val onItemClick : (Int) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
        private var movieList: List<MovieData> = listOf()
    class MovieViewHolder(itemView: View,private val onItemClick : (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val movieImage: ImageView = itemView.findViewById(R.id.movieImage)
        fun loadMovie(movie: MovieData) {
            val posterUrl = movie.posterPath?.let { "https://image.tmdb.org/t/p/w500$it" }
            if (posterUrl != null) {
                Picasso.get().load(posterUrl)
                    .placeholder(R.drawable.placeholder_image)
                    .into(movieImage)
            } else {
                movieImage.setImageResource(R.drawable.placeholder_image)
            }
            itemView.setOnClickListener {
                movie.id.let { id -> id?.let { it1 -> onItemClick(it1) } }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return MovieViewHolder(itemView,onItemClick)
    }
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.loadMovie(movie)
        if(position == movieList.size-4) loadMore()
    }
    override fun getItemCount(): Int {
        return movieList.size
    }
    fun updateMovies(newMovies: List<MovieData>) {
        movieList = newMovies
        notifyDataSetChanged()
    }
}