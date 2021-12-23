package br.com.stant.mobile.challenge.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.stant.mobile.challenge.domain.model.Movie
import br.com.stant.mobile.challenge.domain.usecases.GetMoviesUseCase
import br.com.stant.mobile.challenge.resource.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesViewModel(var getMoviesUseCase: GetMoviesUseCase) : ViewModel() {

    //MARK: Vars and Properties
    private var _movieList = MutableLiveData<List<Movie>>()
    var movieList: LiveData<List<Movie>> = _movieList

    fun getMovies() {

        viewModelScope.launch(Dispatchers.IO) {

            when (val response = getMoviesUseCase()) {

                is Resource.Success -> {
                    _movieList.value = response.data ?: return@launch
                }

                is Resource.Error -> {

                }
            }
        }
    }
}