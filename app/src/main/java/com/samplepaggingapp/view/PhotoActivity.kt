package com.samplepaggingapp.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.samplepaggingapp.R
import com.samplepaggingapp.adapter.PhotoAdapter
import com.samplepaggingapp.utils.*
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
                hideSoftKeyboard(this)
                if (isNetworkAvailable(this)) {
                    val searchKey = etSearchKey.value
                    LoadingDialog.getInstance().show(this)
                    photoViewModel.getSearchPhotos(searchKey).observe(this, Observer {
                        Log.i("Result", "onCreate: $it")
                        rvSearchResult.visibility = View.VISIBLE
                        tvNoSearchResult.visibility = View.GONE
                        photoAdapter.submitList(it)
                        if (LoadingDialog.getInstance() != null) {
                            LoadingDialog.getInstance().hide()
                        }
                    })
                } else {
                    rvSearchResult.visibility = View.GONE
                    tvNoSearchResult.visibility = View.VISIBLE
                    tvNoSearchResult.text = getString(R.string.no_internet)
                }

                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
        rvSearchResult.adapter = photoAdapter
    }
}