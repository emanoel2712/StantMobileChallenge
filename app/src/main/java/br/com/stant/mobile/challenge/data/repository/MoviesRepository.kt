package br.com.stant.mobile.challenge.data.repository

import br.com.stant.mobile.challenge.BuildConfig
import br.com.stant.mobile.challenge.R
import br.com.stant.mobile.challenge.data.api.APIResource
import br.com.stant.mobile.challenge.data.response.ErrorResponse
import br.com.stant.mobile.challenge.data.response.toMovie
import br.com.stant.mobile.challenge.domain.model.Movie
import br.com.stant.mobile.challenge.resource.utils.Resource
import br.com.stant.mobile.challenge.resource.utils.UIText
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection

class MoviesRepository(private var apiResource: APIResource) {

    suspend fun getMovies(page: Int? = 1): Resource<Movie> {

        return try {
            val result = apiResource.getMovies(BuildConfig.apiToken, page)

            result?.let { movieResponse ->
                Resource.Success(movieResponse.toMovie())
            } ?: Resource.Error(UIText.StringResource(R.string.unknow_error))

        } catch (e: IOException) {
            Resource.Error(UIText.StringResource(R.string.no_internet_connection))
        } catch (e: HttpException) {
            val errorResponse =
                Gson().fromJson(e.response()?.errorBody()?.charStream(), ErrorResponse::class.java)

            when (e.code()) {

                HttpURLConnection.HTTP_UNAUTHORIZED -> {
                    errorResponse.status_message?.let { status_message ->
                        Resource.Error(UIText.DynamicText(status_message))
                    } ?: Resource.Error(UIText.StringResource(R.string.unknow_error))
                }

                HttpURLConnection.HTTP_NOT_FOUND -> {
                    errorResponse.status_message?.let { status_message ->
                        Resource.Error(UIText.DynamicText(status_message))
                    } ?: Resource.Error(UIText.StringResource(R.string.unknow_error))
                }

                else -> {
                    Resource.Error(UIText.StringResource(R.string.oops_something_happened))
                }
            }
        }
    }
}