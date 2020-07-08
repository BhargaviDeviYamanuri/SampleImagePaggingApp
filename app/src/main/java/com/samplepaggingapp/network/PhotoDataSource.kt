package com.samplepaggingapp.network

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.samplepaggingapp.model.Photo
import com.samplepaggingapp.model.PhotosResponse
import com.samplepaggingapp.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PhotoDataSource @Inject constructor(val apiService: ApiService, val searchKey: String) :
    PageKeyedDataSource<Int, Photo>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Photo>
    ) {
        apiService.getSearchResult(
            Constants.METHOD_VALUE,
            Constants.API_KEY_VALUE,
            Constants.FORMAT_VALUE,
            Constants.NO_JSON_CALLBACK_VALUE,
            Constants.PAGE_SIZE,
            Constants.FIRST_PAGE,
            searchKey
        ).enqueue(object : Callback<PhotosResponse> {
            override fun onFailure(call: Call<PhotosResponse>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<PhotosResponse>,
                response: Response<PhotosResponse>
            ) {
                if (response.isSuccessful && !response.body()!!.photos.photo.isNullOrEmpty()) {
                    Log.i("LoadInitial", "onResponse: ${response.body()!!.photos.photo}")
                    callback.onResult(
                        response.body()!!.photos.photo,
                        Constants.FIRST_PAGE,
                        Constants.FIRST_PAGE + 1
                    )
                }
            }

        })

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        apiService.getSearchResult(
            Constants.METHOD_VALUE,
            Constants.API_KEY_VALUE,
            Constants.FORMAT_VALUE,
            Constants.NO_JSON_CALLBACK_VALUE,
            Constants.PAGE_SIZE,
            Constants.FIRST_PAGE,
            searchKey
        ).enqueue(object : Callback<PhotosResponse> {
            override fun onFailure(call: Call<PhotosResponse>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<PhotosResponse>,
                response: Response<PhotosResponse>
            ) {
                if (response.isSuccessful && !response.body()!!.photos.photo.isNullOrEmpty()) {
                    Log.i("LoadAfter", "onResponse: ${response.body()!!.photos.photo}")
                    val totalCount: Int = response.body()!!.photos.total.toInt()
                    val key: Int? = if (params.key != totalCount) params.key + 1 else null
                    callback.onResult(response.body()!!.photos.photo, key)
                }
            }

        })

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        apiService.getSearchResult(
            Constants.METHOD_VALUE,
            Constants.API_KEY_VALUE,
            Constants.FORMAT_VALUE,
            Constants.NO_JSON_CALLBACK_VALUE,
            Constants.PAGE_SIZE,
            Constants.FIRST_PAGE,
            searchKey
        ).enqueue(object : Callback<PhotosResponse> {
            override fun onFailure(call: Call<PhotosResponse>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<PhotosResponse>,
                response: Response<PhotosResponse>
            ) {
                if (response.isSuccessful && !response.body()!!.photos.photo.isNullOrEmpty()) {
                    Log.i("LoadBefore", "onResponse: ${response.body()!!.photos.photo}")
                    val key: Int? = if (params.key > 1) params.key - 1 else null
                    callback.onResult(response.body()!!.photos.photo, key)
                }
            }

        })
    }
}