package com.samplepaggingapp.network

import com.samplepaggingapp.model.PhotosResponse
import com.samplepaggingapp.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(Constants.GET_SEARCH_RESULT)
    fun getSearchResult(
        @Query(Constants.METHOD) method: String = Constants.METHOD_VALUE,
        @Query(Constants.API_KEY) apiKey: String = Constants.API_KEY_VALUE,
        @Query(Constants.FORMAT) format: String = Constants.FORMAT_VALUE,
        @Query(Constants.NO_JSON_CALLBACK) noJsonCallBack: Int = Constants.NO_JSON_CALLBACK_VALUE,
        @Query(Constants.PER_PAGE) perPage: Int,
        @Query(Constants.PAGE) page: Int,
        @Query(Constants.SEARCH_TEXT) searchText: String
    ): Call<PhotosResponse>
}