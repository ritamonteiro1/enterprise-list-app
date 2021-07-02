package com.example.enterprises.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.enterprises.R
import com.example.enterprises.constants.Constants
import com.example.enterprises.domains.enterprise.EnterpriseResponse
import com.example.enterprises.extensions.downloadImage

class ResultActivity : AppCompatActivity() {
    private var resultEnterpriseImageView: ImageView? = null
    private var resultDescriptionEnterpriseTextView: TextView? = null
    private var resultToolBar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        findViewsById()
        val enterpriseResponse = retrieverEnterpriseResponse()
        setupToolBar(enterpriseResponse.enterpriseName)
        showEnterpriseDetails(enterpriseResponse)
    }

    private fun showEnterpriseDetails(enterpriseResponse: EnterpriseResponse) {
        resultEnterpriseImageView?.downloadImage(
            Constants.BASE_IMAGE_URL + enterpriseResponse.photo,
            this
        )
        resultDescriptionEnterpriseTextView?.text = enterpriseResponse.description
    }

    private fun setupToolBar(enterpriseName: String?) {
        setSupportActionBar(resultToolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = enterpriseName
    }

    private fun retrieverEnterpriseResponse(): EnterpriseResponse {
        return intent.getSerializableExtra(Constants.ENTERPRISE_RESPONSE_DETAILS) as EnterpriseResponse
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