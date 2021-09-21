package com.example.myapp.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object PostClient {

    private var BASE_URL = "https://jsonplaceholder.typicode.com/"
    private const val TWO_MINUTES = 60 * 2L

    fun getClient(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(TWO_MINUTES, TimeUnit.SECONDS)
            .connectTimeout(TWO_MINUTES, TimeUnit.SECONDS)
            .writeTimeout(TWO_MINUTES, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()
    }
}