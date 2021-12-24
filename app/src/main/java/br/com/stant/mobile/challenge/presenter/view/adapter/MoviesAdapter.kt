package br.com.stant.mobile.challenge.presenter.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.stant.mobile.challenge.databinding.ItemMovieBinding
import br.com.stant.mobile.challenge.domain.model.Result
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
            Glide.with(holder.itemView).load("http://goo.gl/gEgYUd").into(binding.ivMovie)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class MoviesViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root)
}
