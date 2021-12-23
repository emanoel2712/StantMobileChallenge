package br.com.stant.mobile.challenge.presenter.resource.utils

sealed class Resource<T>(val data: T?, val str: String? = null) {
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(str: String, data: T? = null): Resource<T>(data, str)
}
