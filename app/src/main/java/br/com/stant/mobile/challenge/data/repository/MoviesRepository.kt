package br.com.stant.mobile.challenge.data.repository

import br.com.stant.mobile.challenge.BuildConfig
import br.com.stant.mobile.challenge.data.api.APIResource
import br.com.stant.mobile.challenge.domain.model.Movie
import br.com.stant.mobile.challenge.presenter.resource.utils.Resource

class MoviesRepository(private var apiResource: APIResource) {

    suspend fun getMovies(): Resource<List<Movie>> {
        var result = apiResource.getMovies(BuildConfig.apiToken)
        return Resource.Success(null)
    }
}