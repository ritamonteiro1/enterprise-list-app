package com.example.enterprises.extensions

import android.widget.EditText

class Extensions {
    fun EditText?.isEmptyField():Boolean {
        return this!!.text.toString().isEmpty()
    }
}