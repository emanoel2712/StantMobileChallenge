package br.com.stant.mobile.challenge.data.repository

import br.com.stant.mobile.challenge.BuildConfig
import br.com.stant.mobile.challenge.R
import br.com.stant.mobile.challenge.data.data_source.APIResource
import br.com.stant.mobile.challenge.data.data_source.dao.ResultDao
import br.com.stant.mobile.challenge.data.model.ResultDto
import br.com.stant.mobile.challenge.data.model.toResult
import br.com.stant.mobile.challenge.data.response.ErrorResponse
import br.com.stant.mobile.challenge.data.response.toMovie
import br.com.stant.mobile.challenge.domain.model.Movie
import br.com.stant.mobile.challenge.domain.model.Result
import br.com.stant.mobile.challenge.resource.utils.Resource
import br.com.stant.mobile.challenge.resource.utils.UIText
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection

class MoviesRepositoryImpl(private var apiResource: APIResource, private var resultDao: ResultDao) :
    MoviesRepository {

    override suspend fun getMovies(page: Int?): Resource<Movie> {

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

    override suspend fun insertMovies(movies: List<ResultDto>) {

        return try {
            resultDao.insertMovies(movies)

        } catch (e: IOException) {

        }
    }

    override suspend fun getMoviesDB(): List<Result> {

        return try {
            val result = resultDao.getMovies()

            result.map {
                it.toResult()
            }

        } catch (e: IOException) {
            emptyList()
        }
    }
}