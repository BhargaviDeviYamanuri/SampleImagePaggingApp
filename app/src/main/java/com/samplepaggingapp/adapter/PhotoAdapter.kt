package com.samplepaggingapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.samplepaggingapp.R
import com.samplepaggingapp.databinding.SearchItemLayoutBinding
import com.samplepaggingapp.model.Photo

class PhotoAdapter(val context: Context) :
    PagedListAdapter<Photo, PhotoAdapter.ViewHolder>(DIFF_CALLBACK) {
    class ViewHolder(searchItemLayoutBinding: SearchItemLayoutBinding) :
        RecyclerView.ViewHolder(searchItemLayoutBinding.root) {
        val binding: SearchItemLayoutBinding = searchItemLayoutBinding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoAdapter.ViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(context)
        val layout: SearchItemLayoutBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.search_item_layout, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: PhotoAdapter.ViewHolder, position: Int) {
        holder.binding.photo = getItem(position)
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<Photo>() {
            // Concert details may have changed if reloaded from the database,
            // but ID is fixed.
            override fun areItemsTheSame(
                oldPhoto: Photo,
                newPhoto: Photo
            ) = oldPhoto.id == newPhoto.id

            override fun areContentsTheSame(
                oldPhoto: Photo,
                newPhoto: Photo
            ) = oldPhoto == newPhoto
        }
    }
}