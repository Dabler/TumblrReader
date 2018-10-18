package com.dabler.tumblrreader.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkModuleImpl : NetworkModule {

    override fun <T> getApi(url: String, type: Class<T>): T {
        return getRetrofit(url).create<T>(type)
    }

    private fun getRetrofit(url: String): Retrofit {
        val client = OkHttpClient.Builder().addInterceptor(TumblrInterceptor()).build()

        return Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
    }
}
