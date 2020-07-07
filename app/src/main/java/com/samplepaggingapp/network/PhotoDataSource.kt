package com.samplepaggingapp.network

import androidx.paging.PageKeyedDataSource
import com.samplepaggingapp.model.Photo
import com.samplepaggingapp.utils.Constants
import javax.inject.Inject

class PhotoDataSource @Inject constructor(val apiService: ApiService, val searchKey: String) :
    PageKeyedDataSource<Int, Photo>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Photo>
    ) {
        val initialResult = apiService.getSearchResult(
            Constants.METHOD_VALUE,
            Constants.API_KEY_VALUE,
            Constants.FORMAT_VALUE,
            Constants.NO_JSON_CALLBACK_VALUE,
            Constants.PAGE_SIZE,
            Constants.FIRST_PAGE,
            searchKey
        )
        callback.onResult(initialResult.photo, Constants.FIRST_PAGE, Constants.FIRST_PAGE + 1)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        val afterResult = apiService.getSearchResult(
            Constants.METHOD_VALUE,
            Constants.API_KEY_VALUE,
            Constants.FORMAT_VALUE,
            Constants.NO_JSON_CALLBACK_VALUE,
            Constants.PAGE_SIZE,
            Constants.FIRST_PAGE,
            searchKey
        )
        val totalCount: Int = afterResult.total.toInt()
        val key: Int? = if (params.key != totalCount) params.key + 1 else null
        callback.onResult(afterResult.photo, key)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        val key: Int? = if (params.key > 1) params.key - 1 else null
        val beforeResult = apiService.getSearchResult(
            Constants.METHOD_VALUE,
            Constants.API_KEY_VALUE,
            Constants.FORMAT_VALUE,
            Constants.NO_JSON_CALLBACK_VALUE,
            Constants.PAGE_SIZE,
            Constants.FIRST_PAGE,
            searchKey
        )
        callback.onResult(beforeResult.photo, key)
    }
}