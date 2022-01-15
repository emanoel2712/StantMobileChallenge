package br.com.stant.mobile.challenge.domain.model

import android.os.Parcelable
import br.com.stant.mobile.challenge.data.model.ResultDto
import kotlinx.parcelize.Parcelize

@Parcelize
data class Result(
    val adult: Boolean?,
    val backdrop_path: String?,
    val genre_ids: List<Int>?,
    val id: Int?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int?
) : Parcelable

fun Result.toResultDto() = ResultDto(
    id = id ?: 0,
    adult = adult ?: false,
    backdrop_path = backdrop_path ?: "",
    original_language = original_language ?: "",
    original_title = original_title ?: "",
    overview = overview ?: "",
    popularity = popularity ?: 0.0,
    poster_path = poster_path ?: "",
    release_date = release_date ?: "",
    title = title ?: "",
    video = video ?: false,
    vote_average = vote_average ?: 0.0,
    vote_count = vote_count ?: 0,

)