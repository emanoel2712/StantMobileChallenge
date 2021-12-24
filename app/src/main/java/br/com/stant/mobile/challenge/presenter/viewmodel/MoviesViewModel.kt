package br.com.stant.mobile.challenge.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.stant.mobile.challenge.domain.model.Movie
import br.com.stant.mobile.challenge.domain.usecases.GetMoviesUseCase
import br.com.stant.mobile.challenge.resource.utils.Resource
import br.com.stant.mobile.challenge.domain.model.Result
import kotlinx.coroutines.launch

class MoviesViewModel(var getMoviesUseCase: GetMoviesUseCase) : ViewModel() {

    //MARK: Vars and Properties
    private var _movie = MutableLiveData<Movie>()
    var movie: LiveData<Movie> = _movie

    private var _moviesFilteredList = MutableLiveData<List<Result>>()
    var moviesFilteredList: LiveData<List<Result>> = _moviesFilteredList

    fun getMovies(page: Int? = 1) {

        viewModelScope.launch {

            when (val response = getMoviesUseCase(page)) {

                is Resource.Success -> {
                    _movie.value = response.data ?: return@launch
                }

                is Resource.Error -> {

                }
            }
        }
    }

    fun filterMovie(title: String) {

        val moviesList = _movie.value?.results

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