package com.example.enterprises.utils

import android.app.ProgressDialog
import android.content.Context
import androidx.appcompat.app.AlertDialog
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

        fun createErrorDialog(message: String?, context: Context) {
            val builder = AlertDialog.Builder(context)
            builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton(context.getString(R.string.alert_dialog_text), null)
            val alert = builder.create()
            alert.show()
        }
    }
}