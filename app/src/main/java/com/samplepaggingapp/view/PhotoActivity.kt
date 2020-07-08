package com.samplepaggingapp.view

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.samplepaggingapp.R
import com.samplepaggingapp.adapter.PhotoAdapter
import com.samplepaggingapp.utils.App
import com.samplepaggingapp.utils.hideSoftKeyboard
import com.samplepaggingapp.utils.value
import com.samplepaggingapp.viewmodel.PhotoViewModel
import com.samplepaggingapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class PhotoActivity : AppCompatActivity() {

    lateinit var photoViewModel: PhotoViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).appComponent.inject(this)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        photoViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(PhotoViewModel::class.java)
        rvSearchResult.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val photoAdapter = PhotoAdapter(this)
        etSearchKey.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val searchKey = etSearchKey.value
                photoViewModel.getSearchPhotos(searchKey).observe(this, Observer {
                    photoAdapter.submitList(it)
                })
                hideSoftKeyboard(this)
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }



        rvSearchResult.adapter = photoAdapter
    }
}