package br.com.stant.mobile.challenge.data.repository

import br.com.stant.mobile.challenge.BuildConfig
import br.com.stant.mobile.challenge.R
import br.com.stant.mobile.challenge.data.api.APIResource
import br.com.stant.mobile.challenge.domain.model.Movie
import br.com.stant.mobile.challenge.presenter.resource.utils.Resource
import br.com.stant.mobile.challenge.presenter.resource.utils.UIText
import com.google.gson.Gson
import retrofit2.HttpException
import retrofit2.http.HTTP
import java.io.IOException
import java.net.HttpURLConnection

class MoviesRepository(private var apiResource: APIResource) {

    suspend fun getMovies(): Resource<List<Movie>> {

        val result = apiResource.getMovies(BuildConfig.apiToken)

        return try {

            result.response?.let {
                Resource.Success(it)
            } ?: Resource.Error(UIText.DynamicText("Ops, a lista é nula"))

        } catch (e: IOException) {
            Resource.Error(UIText.StringResource(R.string.no_internet_connection))
        } catch (e: HttpException) {

            when (e.code()) {

                HttpURLConnection.HTTP_UNAUTHORIZED -> {
                    Resource.Error(UIText.DynamicText(result.status_message ?: ""))
                }

                HttpURLConnection.HTTP_NOT_FOUND -> {
                    Resource.Error(UIText.DynamicText(result.status_message ?: ""))
                }

                else -> {
                    Resource.Error(UIText.DynamicText("não encontrado"))
                }
            }
        }
    }
}