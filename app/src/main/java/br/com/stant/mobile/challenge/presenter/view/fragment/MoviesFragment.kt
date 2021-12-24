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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setupObservers()
    }

    private fun setupObservers() {

        viewModel.movieList.observe(viewLifecycleOwner) {
//            populateMovies(it.results ?: emptyList())
            this.setupRV(it.results ?: emptyList())
        }

        viewModel.getMovies()
    }

    private fun populateMovies(moviesList: List<Result>) {
        binding.rvMovies.adapter = MoviesAdapter(moviesList)
    }

    private fun setupRV(moviesList: List<Result>) {

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