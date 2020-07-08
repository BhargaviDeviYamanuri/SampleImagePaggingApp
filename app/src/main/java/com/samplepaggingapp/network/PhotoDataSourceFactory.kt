package com.samplepaggingapp.network

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.samplepaggingapp.model.Photo
import javax.inject.Inject

class PhotoDataSourceFactory @Inject constructor(
    val apiService: ApiService,
    val searchKey: String
) : DataSource.Factory<Int, Photo>() {
    val photoDataSourceMutableLiveData: MutableLiveData<PageKeyedDataSource<Int, Photo>> =
        MutableLiveData()

    override fun create(): DataSource<Int, Photo> {
        val photoDataSource = PhotoDataSource(apiService, searchKey)
        photoDataSourceMutableLiveData.postValue(photoDataSource)
        return photoDataSource
    }

    fun getPhotoDataSource(): MutableLiveData<PageKeyedDataSource<Int, Photo>> {
        return photoDataSourceMutableLiveData
    }
}