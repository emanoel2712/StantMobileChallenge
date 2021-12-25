package br.com.stant.mobile.challenge.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    var dates: Dates?,
    var page: Int?,
    var results: List<Result>?,
    var total_pages: Int?,
    var total_results: Int?
) : Parcelable