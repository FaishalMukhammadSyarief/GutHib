package com.zhalz.guthib.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.zhalz.guthib.R

object ImageUrl {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun ImageView.loadUrl(imageUrl: String?) {
        Glide.with(context)
            .load(imageUrl)
            .placeholder(R.drawable.ic_github)
            .into(this)
    }
}