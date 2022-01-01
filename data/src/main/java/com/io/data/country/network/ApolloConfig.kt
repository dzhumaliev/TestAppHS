package com.io.data.country.network

import com.io.data.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

private val sLogLevel =
    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE


private fun getLogInterceptor() = HttpLoggingInterceptor().apply { level = sLogLevel }

private fun setTimeOutToOkHttpClient(okHttpClientBuilder: OkHttpClient.Builder) =
    okHttpClientBuilder.apply {
        readTimeout(30L, TimeUnit.SECONDS)
        connectTimeout(30L, TimeUnit.SECONDS)
        writeTimeout(30L, TimeUnit.SECONDS)
    }

val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(getLogInterceptor()).apply {
        setTimeOutToOkHttpClient(this)
    }
    .build()
