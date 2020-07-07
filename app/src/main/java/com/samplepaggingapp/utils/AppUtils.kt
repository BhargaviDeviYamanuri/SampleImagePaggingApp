package com.samplepaggingapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.samplepaggingapp.R
import com.samplepaggingapp.model.Photo

@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, photo: Photo) {
    val imageUrl: String =
        "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_m.jpg"
    Glide.with(imageView.context).load(imageUrl).error(R.drawable.ic_launcher_background)
        .into(imageView)
}

