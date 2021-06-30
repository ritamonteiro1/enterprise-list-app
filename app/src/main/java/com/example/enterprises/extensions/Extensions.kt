package com.example.enterprises.extensions

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.enterprises.R
import com.example.enterprises.constants.Constants
import com.google.android.material.textfield.TextInputLayout

fun EditText.isEmptyField(textInputLayout: TextInputLayout?, context: Context): Boolean {
    return if (this.text.toString().isEmpty()) {
        textInputLayout?.error = context.getString(R.string.fill_the_field)
        true
    } else {
        textInputLayout?.error = Constants.EMPTY
        false
    }
}

fun ImageView.downloadImage(baseImageUrl: String, context: Context) {
    Glide.with(context).load(baseImageUrl).into(this)
}

fun View.visibilityGone() {
    this.visibility = View.GONE
}