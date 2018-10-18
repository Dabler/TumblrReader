package com.dabler.tumblrreader.network

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException

private const val TUMBLR_API_PREFIX = "var tumblr_api_read = "
private const val TUMBLR_API_SUFFIX = ";\n"

class TumblrInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        val bodyType = response.body()?.contentType()
        val newStringBody = response.body()?.string()?.removePrefix(TUMBLR_API_PREFIX)
        val withoutSuffix = newStringBody?.removeSuffix(TUMBLR_API_SUFFIX)
        val newBody = ResponseBody.create(bodyType, withoutSuffix!!)

        return response.newBuilder().body(newBody).build()
    }
}