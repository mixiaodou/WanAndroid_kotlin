package com.brucej.wanandroid_kotlin.core.net

import com.brucej.wanandroid_kotlin.app.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitHelper private constructor() {
    private var TAG = "RetrofitHelper--"
    private var map = HashMap<String, Retrofit>()
    private fun createRetrofit(url: String): Retrofit {
        url?.let {
            var okHttpClient = OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
            return Retrofit.Builder()
                .baseUrl(Constants.host)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
                .build()
        }
    }

    companion object {
        val instance = RetrofitHelper()
    }

    fun getRestApi() = getApi(Constants.host, RestApi::class.java)

    fun <T> getApi(url: String, service: Class<T>): T {
        if (map[url] == null) {
            map[url] = createRetrofit(url)
        }
        map[url].let {
            return it!!.create(service)
        }
    }
}