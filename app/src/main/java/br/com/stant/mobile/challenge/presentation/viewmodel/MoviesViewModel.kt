package br.com.stant.mobile.challenge.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.stant.mobile.challenge.domain.model.Movie
import br.com.stant.mobile.challenge.domain.use_case.GetMoviesUseCase
import br.com.stant.mobile.challenge.resource.utils.Resource
import br.com.stant.mobile.challenge.domain.model.Result
import kotlinx.coroutines.launch

class MoviesViewModel(var getMoviesUseCase: GetMoviesUseCase) : ViewModel() {

    //MARK: Vars and Properties

    private var _movie = MutableLiveData<Movie>()
    var movie: LiveData<Movie> = _movie

    private var _moviesList = MutableLiveData<List<Result>>()
    var moviesList: LiveData<List<Result>> = _moviesList

    private var _moviesFilteredList = MutableLiveData<List<Result>>()
    var moviesFilteredList: LiveData<List<Result>> = _moviesFilteredList

    var lastPosition: Int = 0


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

    fun getMovies(page: Int? = 1, isMoreMovies: Boolean? = false) {

        viewModelScope.launch {

            when (val response = getMoviesUseCase(page)) {

                is Resource.Success -> {

                    response.data?.let {
                        _movie.value = it
                    }

                    if (isMoreMovies == true) {
                        _movie.value?.page = response.data?.page
                        _moviesList.value = addMoviesInList(response.data?.results ?: emptyList())
                    } else {
                        _moviesList.value = response.data?.results ?: emptyList()
                    }
                }

                is Resource.Error -> {

                }
            }
        }
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