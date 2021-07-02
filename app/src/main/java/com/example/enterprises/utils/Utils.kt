package com.example.enterprises.utils

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.example.enterprises.R


class Utils {
    companion object {
        fun setAlertDialog(context: Context): Dialog {
            val builder = AlertDialog.Builder(context)
            builder.setView(R.layout.progress_dialog)
            val dialog: Dialog = builder.create()
            dialog.setCancelable(false)
            dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
            return dialog
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