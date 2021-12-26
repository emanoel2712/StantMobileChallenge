package br.com.stant.mobile.challenge.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.stant.mobile.challenge.databinding.ItemMovieBinding
import br.com.stant.mobile.challenge.domain.model.Result
import br.com.stant.mobile.challenge.resource.extension.asDateStr
import com.bumptech.glide.Glide

class MoviesAdapter(private val movies: List<Result>) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    var clickInfo: (result: Result) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemView = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = movies[position]

        with(holder) {
            binding.tvTitle.text = movie.title
            binding.tvReleaseDate.text = movie.release_date?.asDateStr()
            Glide.with(holder.itemView).load("http://image.tmdb.org/t/p/w500/" + movie.poster_path)
                .into(binding.ivMovie)
        }

        holder.itemView.setOnClickListener {
            this.clickInfo(movie)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class MoviesViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root)
}
