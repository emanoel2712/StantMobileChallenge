package br.com.stant.mobile.challenge.resource.extension

import android.content.Context
import br.com.stant.mobile.challenge.resource.utils.UIText

fun UIText.asString(context: Context): String {

    return when (this) {

        is UIText.StringResource -> {
            context.getString(id)
        }

        is UIText.DynamicText -> {
            value
        }
    }
}