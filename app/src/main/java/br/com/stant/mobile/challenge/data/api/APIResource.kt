package br.com.stant.mobile.challenge.data.api

import br.com.stant.mobile.challenge.domain.model.Movie
import retrofit2.http.GET

interface APIResource {

    @GET("/movie/popular")
    suspend fun getMovies(): BaseResponseAPI<List<Movie>>
}