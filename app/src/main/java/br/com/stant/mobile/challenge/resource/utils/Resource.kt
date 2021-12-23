package br.com.stant.mobile.challenge.resource.utils

sealed class Resource<T>(val data: T?, val str: UIText? = null) {
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(str: UIText, data: T? = null): Resource<T>(data, str)
}
