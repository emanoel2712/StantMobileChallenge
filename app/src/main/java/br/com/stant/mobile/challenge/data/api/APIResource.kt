package br.com.stant.mobile.challenge.data.api

import br.com.stant.mobile.challenge.domain.model.Movie
import retrofit2.http.GET
import retrofit2.http.Query

interface APIResource {

    @GET("/movie/popular")
    suspend fun getMovies(@Query("api_key") apiToken: String): BaseResponse<List<Movie>>
}