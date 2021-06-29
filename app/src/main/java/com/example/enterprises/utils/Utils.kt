package com.example.enterprises.utils

import android.app.ProgressDialog
import android.content.Context
import com.example.enterprises.R

class Utils {
    companion object {
        fun showProgressDialog(progressDialog: ProgressDialog?, context: Context) {
            val progressDialog = ProgressDialog(context)
            progressDialog.show()
            progressDialog.setContentView(R.layout.progress_dialog)
            progressDialog.setCanceledOnTouchOutside(false)
            progressDialog.setCancelable(false)
            progressDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }
}