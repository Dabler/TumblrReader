package com.dabler.tumblrreader.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.dabler.tumblrreader.DefaultSchedulers
import com.dabler.tumblrreader.R
import com.dabler.tumblrreader.entities.Post
import com.dabler.tumblrreader.model.TumblrPresenter
import com.dabler.tumblrreader.model.TumblrPresenterImpl
import com.dabler.tumblrreader.network.NetworkModuleImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TumblrView {

    private lateinit var presenter: TumblrPresenter
    private lateinit var viewAdapter: Adapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = TumblrPresenterImpl(this, DefaultSchedulers(), NetworkModuleImpl())
        initializeRecyclerView()
        presenter.getPosts()
    }

    override fun showError() {
        Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_SHORT).show()
    }

    override fun updatePosts(posts: List<Post>) {
        viewAdapter.updatePosts(posts)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.clear()
    }

    private fun initializeRecyclerView() {
        viewManager = LinearLayoutManager(this)
        viewAdapter = Adapter(emptyList(), this)

        recyclerView.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}
