package br.com.stant.mobile.challenge.data.response

import br.com.stant.mobile.challenge.domain.model.Movie
import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    val dates: DatesResponse?,
    val page: Int?,
    val results: List<ResultResponse>?,
    val total_pages: Int?,
    val total_results: Int?
)

fun MoviesResponse.toMovie() = Movie(
    dates = dates?.toDates(),
    page = page,
    results = results?.map { resultResponse ->
        resultResponse.toResult()
    },
    total_pages = total_pages,
    total_results = total_results
)