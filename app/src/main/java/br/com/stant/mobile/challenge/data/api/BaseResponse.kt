package br.com.stant.mobile.challenge.data.api

data class BaseResponse<T>(
    val response: T?,
    val status_message: String?
)