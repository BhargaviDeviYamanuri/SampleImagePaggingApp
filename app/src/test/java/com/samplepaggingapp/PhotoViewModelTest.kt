package com.samplepaggingapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.samplepaggingapp.model.Photo
import com.samplepaggingapp.network.ApiService
import com.samplepaggingapp.viewmodel.PhotoViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class PhotoViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var apiService: ApiService
    private lateinit var photoViewModel: PhotoViewModel

    @Before
    fun setupTest() {
        apiService = mock()
        photoViewModel = mock()
    }

    @Test
    fun testSearchResult() {
        val searchKey: String = "test"
        val mockResult: LiveData<PagedList<Photo>> = mock()
        whenever(photoViewModel.getSearchPhotos(searchKey)).thenReturn(mockResult)
        val result = photoViewModel.getSearchPhotos(searchKey)
        Assert.assertEquals(result, mockResult)
    }

}