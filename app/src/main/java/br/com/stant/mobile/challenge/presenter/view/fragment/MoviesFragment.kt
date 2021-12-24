package br.com.stant.mobile.challenge.presenter.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import br.com.stant.mobile.challenge.databinding.FragmentMoviesBinding
import br.com.stant.mobile.challenge.domain.model.Result
import br.com.stant.mobile.challenge.presenter.view.adapter.MoviesAdapter
import br.com.stant.mobile.challenge.presenter.viewmodel.MoviesViewModel
import br.com.stant.mobile.challenge.resource.utils.Values

class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MoviesViewModel by viewModel()

    override fun onResume() {
        super.onResume()
        this.setupObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun setupObservers() {

        viewModel.movieList.observe(viewLifecycleOwner) {
            this.setupMoviesRV(it.results ?: emptyList())
        }

        viewModel.getMovies()
    }

    private fun setupMoviesRV(moviesList: List<Result>) {

        val moviesAdapter = MoviesAdapter(moviesList)
        binding.rvMovies.adapter = moviesAdapter

        moviesAdapter.clickInfo = {
            val bundle = Bundle()
            bundle.putParcelable(Values.DETAIL_MOVIE, it)
            this.findNavController()
                .navigate(MoviesFragmentDirections.actionMoviesToMovieDetail().actionId, bundle)
        }
    }
}