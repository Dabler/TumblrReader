package com.dabler.tumblrreader.network

interface NetworkModule {

    fun <T> getApi(url: String, type: Class<T>): T
}