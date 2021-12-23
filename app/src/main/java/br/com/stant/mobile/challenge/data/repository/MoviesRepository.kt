package br.com.stant.mobile.challenge.data.repository

import br.com.stant.mobile.challenge.BuildConfig
import br.com.stant.mobile.challenge.data.api.APIResource

class MoviesRepository(private var apiResource: APIResource) {

    suspend fun getMovies() {
        var result = apiResource.getMovies(BuildConfig.apiToken)

    }
}