package br.com.stant.mobile.challenge.presenter.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.stant.mobile.challenge.databinding.FragmentMovieDetailBinding
import br.com.stant.mobile.challenge.resource.utils.Values
import br.com.stant.mobile.challenge.domain.model.Result
import br.com.stant.mobile.challenge.resource.extension.showToolbar
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
        requireActivity().showToolbar()
    }

    private fun setupInfo() {

        this.arguments?.let {

            val movie = it.getParcelable<Result>(Values.DETAIL_MOVIE)

            Glide.with(binding.ivMovie).load("http://image.tmdb.org/t/p/w500/" + movie?.poster_path)
                .into(binding.ivMovie)
        }
    }
}