package com.samplepaggingapp.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.samplepaggingapp.R
import com.samplepaggingapp.model.Photo


@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, photo: Photo?) {
    val imageUrl =
        "https://farm${photo!!.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_m.jpg"
    Glide.with(imageView.context).load(imageUrl).error(R.drawable.ic_launcher_background)
        .into(imageView)
}

var EditText.value
    get() = this.text.toString()
    set(text) = this.setText(text)

fun hideSoftKeyboard(activity: Activity) {
    val inputMethodManager: InputMethodManager = activity.getSystemService(
        Activity.INPUT_METHOD_SERVICE
    ) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(
        activity.currentFocus!!.windowToken, 0
    )
}

/**
 * This method is used for to check is network is connected or not
 */
fun isNetworkAvailable(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val n = cm.activeNetwork
        if (n != null) {
            val nc = cm.getNetworkCapabilities(n)
            //It will check for both wifi and cellular network
            return nc!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(
                NetworkCapabilities.TRANSPORT_WIFI
            )
        }
        return false
    } else {
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}