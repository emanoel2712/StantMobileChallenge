package br.com.stant.mobile.challenge.presenter.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.stant.mobile.challenge.databinding.ItemMovieBinding
import br.com.stant.mobile.challenge.domain.model.Result
import br.com.stant.mobile.challenge.resource.extension.asDate
import com.bumptech.glide.Glide

class MoviesAdapter(private val movies: List<Result>) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemView = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = movies[position]

        with(holder) {
            binding.tvTitle.text = movie.title
            binding.tvReleaseDate.text = movie.release_date?.asDate()
            Glide.with(holder.itemView).load("http://image.tmdb.org/t/p/w500/" + movie.poster_path).into(binding.ivMovie)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class MoviesViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root)
}
