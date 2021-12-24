package br.com.stant.mobile.challenge.presenter.view.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.stant.mobile.challenge.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import br.com.stant.mobile.challenge.databinding.FragmentMoviesBinding
import br.com.stant.mobile.challenge.domain.model.Result
import br.com.stant.mobile.challenge.presenter.view.adapter.MoviesAdapter
import br.com.stant.mobile.challenge.presenter.viewmodel.MoviesViewModel
import br.com.stant.mobile.challenge.resource.utils.Values
import br.com.stant.mobile.challenge.resource.extension.hideToolbar


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.loadMoreMoviesOnRV()
        this.setupUI()
    }

    private fun setupUI() {
        requireActivity().hideToolbar()
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(binding.topAppBar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setupObservers() {

        viewModel.movie.observe(viewLifecycleOwner) {
            this.setupMoviesRV(it.results ?: emptyList())
        }

        viewModel.moviesFilteredList.observe(viewLifecycleOwner) {
            this.setupMoviesRV(it)
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

    private fun loadMoreMoviesOnRV() {

        binding.rvMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val pageIn = viewModel.movie.value?.page
                if (!recyclerView.canScrollVertically(1) && dy != 0) {
                    viewModel.getMovies(pageIn?.plus(1))
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        requireActivity().menuInflater.inflate(R.menu.top_app_bar, menu);
        val searchItem = menu.findItem(R.id.search)
        val searchView: SearchView = searchItem.actionView as SearchView
        searchView.queryHint = getString(R.string.search_favorite_movie)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.filterMovie(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.filterMovie(newText ?: "")
                return true
            }
        })
    }
}