package br.com.stant.mobile.challenge.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import br.com.stant.mobile.challenge.domain.model.Result

@Entity(tableName = "result")
data class ResultDto(
    @PrimaryKey
    val id: Int,
    val adult: Boolean,
    val backdrop_path: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

fun ResultDto.toResult() = Result(
    id = id,
    adult = adult,
    backdrop_path = backdrop_path,
    original_language = original_language,
    original_title = original_title,
    overview = overview,
    popularity = popularity,
    poster_path = poster_path,
    release_date = release_date,
    title = title,
    video = video,
    vote_average = vote_average,
    vote_count = vote_count,
    genre_ids = emptyList()
)
