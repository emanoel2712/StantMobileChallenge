package br.com.stant.mobile.challenge.resource.utils

import androidx.annotation.StringRes

sealed class UIText {
    data class DynamicText(val value: String) : UIText()
    data class StringResource(@StringRes val id: Int) : UIText()
}
