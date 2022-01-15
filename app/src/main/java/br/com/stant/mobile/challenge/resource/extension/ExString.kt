package br.com.stant.mobile.challenge.resource.extension

import java.text.SimpleDateFormat
import java.util.*

fun String.asDateStr(): String {
    val dateFIn = SimpleDateFormat("yyyy-mm-dd", Locale.getDefault())
    val dateFOut = SimpleDateFormat("dd/mm/yyyy", Locale.getDefault())
    dateFIn.parse(this)?.let {
        return dateFOut.format(it)
    }

    return ""
}