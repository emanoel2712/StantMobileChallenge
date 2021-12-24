package br.com.stant.mobile.challenge.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dates(
    val maximum: String?,
    val minimum: String?
) : Parcelable