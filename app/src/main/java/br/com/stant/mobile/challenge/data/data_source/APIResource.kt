package br.com.stant.mobile.challenge.data.data_source

import br.com.stant.mobile.challenge.data.response.MoviesResponse
import br.com.stant.mobile.challenge.domain.model.Movie
import retrofit2.http.GET
import retrofit2.http.Query

interface APIResource {

    @GET("movie/now_playing")
    suspend fun getMovies(@Query("api_key") apiToken: String, @Query("page") page: Int? = 1): MoviesResponse?
}