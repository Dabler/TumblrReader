package com.dabler.tumblrreader.ui

import com.dabler.tumblrreader.entities.Post

interface TumblrView {

    fun updatePosts(posts: List<Post>)

    fun showError()
}