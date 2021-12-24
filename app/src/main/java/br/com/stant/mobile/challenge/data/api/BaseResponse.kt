package br.com.stant.mobile.challenge.data.api

import br.com.stant.mobile.challenge.data.response.MoviesResponse
import br.com.stant.mobile.challenge.domain.model.Movie

data class BaseResponse<T>(
    val response: T?,
    val status_message: String?,
    val results: T?,
    val page: Int?,
    val dates: T?,
    var movie: MoviesResponse?
)