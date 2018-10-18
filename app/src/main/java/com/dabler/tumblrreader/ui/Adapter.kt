package com.dabler.tumblrreader.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dabler.tumblrreader.R
import com.dabler.tumblrreader.entities.Post
import com.dabler.tumblrreader.entities.Type
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_holder_image.view.*
import kotlinx.android.synthetic.main.view_holder_video.view.*


class Adapter(private var posts: List<Post>, private val context: Context) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    sealed class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        class Video(view: View) : ViewHolder(view)

        class Image(view: View) : ViewHolder(view)
    }

    fun updatePosts(posts: List<Post>) {
        this.posts = posts
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        return when (viewType) {
            Type.PHOTO.ordinal -> ViewHolder.Image(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_image, parent, false))
            else -> ViewHolder.Video(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_video, parent, false))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return posts[position].type.ordinal
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder.itemViewType) {
            Type.PHOTO.ordinal -> Picasso.get().load(posts[position].photoUrl1280).fit().centerInside().into(holder.itemView.holderImageView)
            else -> {
                holder.itemView.holderTextView.text = getHtmlFormattedText(posts[position].videoCaption)
                holder.itemView.holderYoutubeLogoImageView.setOnClickListener {
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(posts[position].videoSource)))
                }
            }
        }
    }

    override fun getItemCount() = posts.size

    private fun getHtmlFormattedText(url: String): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(url, Html.FROM_HTML_MODE_COMPACT)
        } else {
            (Html.fromHtml(url))
        }
    }
}