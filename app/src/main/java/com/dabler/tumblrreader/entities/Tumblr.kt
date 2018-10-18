package com.dabler.tumblrreader.entities

import com.google.gson.annotations.SerializedName

data class Tumblr(@SerializedName("posts-total") val postsTotal: Int,
                  @SerializedName("posts") val feeds: List<Post>)