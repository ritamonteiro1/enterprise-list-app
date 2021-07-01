package com.example.enterprises.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.enterprises.R
import com.example.enterprises.click.listener.OnEnterpriseItemClickListener
import com.example.enterprises.constants.Constants
import com.example.enterprises.domains.enterprise.EnterpriseResponse
import java.io.Serializable

class ResultActivity : AppCompatActivity() {
    private var resultEnterpriseImageView: ImageView? = null
    private var resultDescriptionEnterpriseTextView: TextView? = null
    private var resultToolBar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        findViewsById()
        var enterpriseResponse = retrieverEnterpriseResponse()

    }

    private fun retrieverEnterpriseResponse(): Serializable? {
            return intent.getSerializableExtra(Constants.ENTERPRISE_RESPONSE_DETAILS)
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