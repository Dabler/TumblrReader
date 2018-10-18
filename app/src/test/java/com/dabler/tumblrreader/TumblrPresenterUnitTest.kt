package com.dabler.tumblrreader

import com.dabler.tumblrreader.entities.Post
import com.dabler.tumblrreader.entities.Tumblr
import com.dabler.tumblrreader.entities.Type
import com.dabler.tumblrreader.model.TumblrPresenterImpl
import com.dabler.tumblrreader.network.NetworkModule
import com.dabler.tumblrreader.network.TumblrApi
import com.dabler.tumblrreader.ui.TumblrView
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Test
import org.mockito.Mockito


private const val BASE_URL = "http://twitterthecomic.tumblr.com/"

class TumblrPresenterUnitTest {

    val samplePostsList = listOf(
            Post(1,
                    "http://sample.com",
                    Type.PHOTO, "",
                    "",
                    "http://sample.image.com"))

    private val sampleTumblrResponse = Tumblr(20, samplePostsList)
    private val api = mock<TumblrApi>()
    private val viewMock = mock<TumblrView> {}
    private val schedulersMock = DefaultSchedulers(Schedulers.trampoline(), Schedulers.trampoline())
    private val networkModuleMock = mock<NetworkModule> { whenever(it.getApi(BASE_URL, TumblrApi::class.java)).thenReturn(api) }
    private val presenterMock = Mockito.spy(TumblrPresenterImpl(viewMock, schedulersMock, networkModuleMock))

    @Test
    fun checkApiReturnsList() {
        //before
        whenever(api.getResponse(any())).thenReturn(Single.just(sampleTumblrResponse))

        //when
        presenterMock.getPosts()

        //then
        verify(viewMock).updatePosts(samplePostsList)
    }

    @Test
    fun checkErrorShown() {
        //before
        whenever(api.getResponse(any())).thenReturn(Single.error(Exception()))

        //when
        presenterMock.getPosts()

        //then
        verify(viewMock).showError()
    }
}
