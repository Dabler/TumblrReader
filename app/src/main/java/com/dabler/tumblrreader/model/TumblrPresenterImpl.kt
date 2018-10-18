package com.dabler.tumblrreader.model

import com.dabler.tumblrreader.DefaultSchedulers
import com.dabler.tumblrreader.network.NetworkModule
import com.dabler.tumblrreader.network.TumblrApi
import com.dabler.tumblrreader.ui.TumblrView
import io.reactivex.disposables.CompositeDisposable

private const val BASE_URL = "http://twitterthecomic.tumblr.com/"
private const val START_POST_INDEX = 0

open class TumblrPresenterImpl(var view: TumblrView?,
                               val defaultSchedulers: DefaultSchedulers,
                               networkModule: NetworkModule) : TumblrPresenter {

    private val service: TumblrApi = networkModule.getApi(BASE_URL, TumblrApi::class.java)
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun getPosts() {
        compositeDisposable.add(
                service.getResponse(START_POST_INDEX)
                        .subscribeOn(defaultSchedulers.ioScheduler)
                        .observeOn(defaultSchedulers.uiScheduler)
                        .subscribe(
                                { view?.updatePosts(it.feeds) },
                                { view?.showError() }
                        ))
    }

    override fun clear() {
        compositeDisposable.clear()
        view = null
    }
}