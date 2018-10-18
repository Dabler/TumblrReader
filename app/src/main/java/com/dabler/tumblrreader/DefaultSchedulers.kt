package com.dabler.tumblrreader

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

data class DefaultSchedulers(
        val ioScheduler: Scheduler = Schedulers.io(),
        val uiScheduler: Scheduler = AndroidSchedulers.mainThread())