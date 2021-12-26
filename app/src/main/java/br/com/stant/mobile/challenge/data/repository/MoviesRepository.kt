package br.com.stant.mobile.challenge.data.repository

import br.com.stant.mobile.challenge.data.model.Result
import br.com.stant.mobile.challenge.domain.model.Movie
import br.com.stant.mobile.challenge.resource.utils.Resource

interface MoviesRepository {

    suspend fun getMovies(page: Int? = 1): Resource<Movie>

    suspend fun insertMovies(movies: List<Result>)

}