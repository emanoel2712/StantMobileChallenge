package br.com.stant.mobile.challenge.data.response

import br.com.stant.mobile.challenge.domain.model.Dates

data class DatesResponse(
    val maximum: String?,
    val minimum: String?
)

fun DatesResponse.toDates() = Dates(
    maximum = maximum,
    minimum = minimum
)