package com.dabler.tumblrreader.network

import com.dabler.tumblrreader.entities.Tumblr
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TumblrApi {
    @GET("api/read/json")
    fun getResponse(@Query("start") startIndex: Int): Single<Tumblr>
}