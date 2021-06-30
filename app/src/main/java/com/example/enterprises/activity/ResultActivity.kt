package com.example.enterprises.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.enterprises.R

class ResultActivity : AppCompatActivity() {
    private var resultEnterpriseImageView: ImageView? = null
    private var resultDescriptionEnterpriseTextView: TextView? = null
    private var resultToolBar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        findViewsById()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun findViewsById() {
        resultEnterpriseImageView = findViewById(R.id.resultEnterpriseImageView)
        resultDescriptionEnterpriseTextView = findViewById(R.id.resultDescriptionEnterpriseTextView)
        resultToolBar = findViewById(R.id.resultToolBar)
    }
}