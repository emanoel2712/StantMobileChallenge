package br.com.stant.mobile.challenge.presentation.view.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.stant.mobile.challenge.R
import br.com.stant.mobile.challenge.databinding.FragmentMoviesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import br.com.stant.mobile.challenge.domain.model.Result
import br.com.stant.mobile.challenge.presentation.view.adapter.MoviesAdapter
import br.com.stant.mobile.challenge.presentation.viewmodel.MoviesViewModel
import br.com.stant.mobile.challenge.resource.extension.*
import br.com.stant.mobile.challenge.resource.utils.Constants
import br.com.stant.mobile.challenge.resource.utils.TypeNav
import br.com.stant.mobile.challenge.resource.utils.UIState

class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private var isScrollToTheEnd: Boolean = false

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
        this.setupListeners()
    }

    private fun setupUI() {
        requireActivity().hideToolbar()
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(binding.topAppBar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setupObservers() {

        viewModel.uiState.observe(viewLifecycleOwner) {

            when (it) {

                is UIState.Error -> {
                    this.showSnackBar(it.uiText.asString(requireContext()))
                }

                is UIState.Loading -> {
                    if (it.isLoading) adjustUI(true) else adjustUI(false)
                }
            }
        }

        viewModel.moviesList.observe(viewLifecycleOwner) {
            this.setupMoviesRV(it)
            if (isScrollToTheEnd) {
                this.binding.rvMovies.smoothScrollToPosition(viewModel.lastPosition)
                isScrollToTheEnd = false
            }
        }

        viewModel.moviesFilteredList.observe(viewLifecycleOwner) {
            this.setupMoviesRV(it)
        }

        viewModel.getMovies()
    }

    private fun setupListeners() {

        var isClick = false
        var typeNav: Enum<TypeNav>

        binding.topAppBar.setOnMenuItemClickListener { menu ->

            when (menu.itemId) {

                R.id.dynamicOp -> {
                    isClick = !isClick

                    typeNav = if (isClick) TypeNav.MOVIES_DOWNLOADED else TypeNav.HOME

                    when (typeNav) {

                        TypeNav.MOVIES_DOWNLOADED -> {
                            menu.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_home)
                            viewModel.getMoviesDb()
                            binding.tvMoviesViewed.visible()
                        }

                        TypeNav.HOME -> {
                            menu.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_cloud)
                            binding.tvMoviesViewed.gone()
                            viewModel.getMovies()

                        }
                    }

                    true
                }

                else -> {
                    true
                }
            }
        }
    }

    private fun setupMoviesRV(moviesList: List<Result>) {

        val moviesAdapter = MoviesAdapter(moviesList)
        binding.rvMovies.adapter = moviesAdapter

        moviesAdapter.clickInfo = {
            val bundle = Bundle()
            bundle.putParcelable(Constants.DETAIL_MOVIE, it)
            this.findNavController()
                .navigate(MoviesFragmentDirections.actionMoviesToMovieDetail().actionId, bundle)
        }
    }

    private fun loadMoreMoviesOnRV() {

        binding.rvMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val pageIn = viewModel.movie.value?.page
                viewModel.lastPosition = viewModel.moviesList.value?.size ?: 0

                if (!recyclerView.canScrollVertically(1) && dy != 0) {
                    isScrollToTheEnd = true
                    viewModel.getMovies(pageIn?.plus(1), true)
                }
            }
        })
    }

    private fun adjustUI(isLoading: Boolean) {

        if (isLoading) {
            binding.loading.startShimmer()
            binding.rvMovies.gone()
            binding.loading.visible()
        } else {
            binding.loading.stopShimmer()
            binding.rvMovies.visible()
            binding.loading.gone()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        requireActivity().menuInflater.inflate(R.menu.menu_app_bar, menu);
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