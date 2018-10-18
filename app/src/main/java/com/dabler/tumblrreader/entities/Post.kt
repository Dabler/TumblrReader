package com.dabler.tumblrreader.entities

import com.google.gson.annotations.SerializedName

data class Post(@SerializedName("id") val id: Long,
                @SerializedName("url") val url: String,
                @SerializedName("type") val type: Type,
                @SerializedName("video-source") val videoSource: String,
                @SerializedName("video-caption") val videoCaption: String,
                @SerializedName("photo-url-1280") val photoUrl1280: String)