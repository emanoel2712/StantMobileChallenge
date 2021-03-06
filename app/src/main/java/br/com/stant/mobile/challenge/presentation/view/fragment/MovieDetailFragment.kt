package br.com.stant.mobile.challenge.presentation.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.stant.mobile.challenge.databinding.FragmentMovieDetailBinding
import br.com.stant.mobile.challenge.resource.utils.Constants
import br.com.stant.mobile.challenge.domain.model.Result
import br.com.stant.mobile.challenge.resource.extension.asDateStr
import br.com.stant.mobile.challenge.resource.extension.hideToolbar
import br.com.stant.mobile.challenge.resource.extension.setupToolbarWithNavController
import com.bumptech.glide.Glide

class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setupUI()
        this.setupInfo()
    }

    private fun setupUI() {
        requireActivity().hideToolbar()
        this.setupToolbarWithNavController(binding.topAppBar)
    }

    private fun setupInfo() {

        this.arguments?.let {

            val movie = it.getParcelable<Result>(Constants.DETAIL_MOVIE)

            Glide.with(binding.ivMovie).load("http://image.tmdb.org/t/p/w500/" + movie?.poster_path)
                .into(binding.ivMovie)

            binding.tvTitle.text = movie?.title
            binding.tvDate.text = movie?.release_date?.asDateStr()
            binding.tvOverview.text = movie?.overview
        }
    }
}