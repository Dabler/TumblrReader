package com.dabler.tumblrreader.entities

import com.google.gson.annotations.SerializedName

enum class Type {
    @SerializedName("photo")
    PHOTO,
    @SerializedName("video")
    VIDEO,
    @SerializedName("text")
    TEXT,
    @SerializedName("quote")
    QUOTE,
    @SerializedName("link")
    LINK,
    @SerializedName("answer")
    ANSWER,
    @SerializedName("audio")
    AUDIO,
    @SerializedName("chat")
    CHAT
}