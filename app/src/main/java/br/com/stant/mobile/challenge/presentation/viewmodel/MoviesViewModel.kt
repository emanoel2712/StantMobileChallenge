package br.com.stant.mobile.challenge.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.stant.mobile.challenge.data.model.ResultDto
import br.com.stant.mobile.challenge.domain.model.Movie
import br.com.stant.mobile.challenge.domain.use_case.GetMoviesUseCase
import br.com.stant.mobile.challenge.resource.utils.Resource
import br.com.stant.mobile.challenge.domain.model.Result
import br.com.stant.mobile.challenge.domain.use_case.InsertMoviesUseCase
import br.com.stant.mobile.challenge.resource.utils.UIState
import kotlinx.coroutines.launch

class MoviesViewModel(
    var getMoviesUseCase: GetMoviesUseCase,
    var insertMoviesUseCase: InsertMoviesUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UIState>()
    var uiState: LiveData<UIState> = _uiState

    private val _movie = MutableLiveData<Movie>()
    var movie: LiveData<Movie> = _movie

    private val _moviesList = MutableLiveData<List<Result>>()
    var moviesList: LiveData<List<Result>> = _moviesList

    private val _moviesFilteredList = MutableLiveData<List<Result>>()
    var moviesFilteredList: LiveData<List<Result>> = _moviesFilteredList

    var lastPosition: Int = 0

    fun getMovies(page: Int? = 1, isMoreMovies: Boolean? = false) {

        viewModelScope.launch {

            _uiState.value = UIState.Loading(true)

            when (val response = getMoviesUseCase(page)) {

                is Resource.Success -> {
                    _uiState.value = UIState.Loading(false)

                    response.data?.let {
                        _movie.value = it
                    }

                    if (isMoreMovies == true) {
                        _movie.value?.page = response.data?.page
                        _moviesList.value = addMoviesInList(response.data?.results ?: emptyList())
                    } else {
                        _moviesList.value = response.data?.results ?: emptyList()
                    }

                    insertMovies(response.data?.results ?: emptyList())
                }

                is Resource.Error -> {
                    response.uiText?.let { uiText ->
                        _uiState.value = UIState.Error(uiText)
                    }
                }
            }
        }
    }

    fun insertMovies(moviesList: List<Result>) {

        viewModelScope.launch {
            insertMoviesUseCase(moviesList)
        }
    }

    private fun addMoviesInList(resultsResponse: List<Result>): MutableList<Result> {

        val moviesMutableList = mutableListOf<Result>()

        _moviesList.value?.forEach {
            moviesMutableList.add(it)
        }

        resultsResponse.forEach {
            moviesMutableList.add(it)
        }

        return moviesMutableList
    }

    fun filterMovie(title: String) {

        val moviesList = _moviesList.value

        val moviesFilteredList = moviesList?.filter { result ->
            result.title?.contains(title, true) == true
        }

        if (title.isEmpty()) {
            _moviesFilteredList.value = moviesList ?: emptyList()
        } else {
            _moviesFilteredList.value = moviesFilteredList ?: emptyList()
        }
    }
}