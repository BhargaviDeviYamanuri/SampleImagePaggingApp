package com.samplepaggingapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.samplepaggingapp.model.Photo
import com.samplepaggingapp.network.ApiService
import com.samplepaggingapp.network.PhotoDataSourceFactory
import com.samplepaggingapp.utils.Constants
import javax.inject.Inject

class PhotoViewModel @Inject constructor(val apiService: ApiService) : ViewModel() {

    fun getSearchPhotos(searchKey: String): LiveData<PagedList<Photo>> {
        val photoDataSourceFactory = PhotoDataSourceFactory(apiService, searchKey)
        val pagingConfig: PagedList.Config =
            PagedList.Config.Builder().setEnablePlaceholders(false).setPageSize(Constants.PAGE_SIZE)
                .build()
        return LivePagedListBuilder(photoDataSourceFactory, pagingConfig).build()
    }
}