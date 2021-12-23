package br.com.stant.mobile.challenge.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {

    private val iLogging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val httpClient =
        OkHttpClient.Builder().connectTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
            .readTimeout(60, java.util.concurrent.TimeUnit.SECONDS).addInterceptor(iLogging)
            .addInterceptor { chain ->
                val originalR = chain.request()
                val request = originalR.newBuilder()
                request.addHeader("Content-Type", "application/json")
                val requestF = request.build()
                chain.proceed(requestF)
            }

    private val retroClient = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build()

    fun <T> createService(apiClass: Class<T>): T {
        return retroClient.create(apiClass)
    }
}