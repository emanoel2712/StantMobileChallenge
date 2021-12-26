package br.com.stant.mobile.challenge.resource.utils

sealed class UIState {
    data class Loading(var isLoading: Boolean): UIState()
    data class Error(var uiText: UIText): UIState()
}

//data class UIState(val isLoading: Boolean = false) {
//}