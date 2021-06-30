package com.example.enterprises.activity

import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.enterprises.R
import com.example.enterprises.api.Api
import com.example.enterprises.api.DataService
import com.example.enterprises.constants.Constants
import com.example.enterprises.domains.enterprise.EnterpriseListResponse
import com.example.enterprises.extensions.visibilityGone
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var mainInformationTextView: TextView? = null
    private var mainToolBar: Toolbar? = null
    private var mainRecyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewsById()
        setupToolBar()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val menuItem = menu!!.findItem(R.id.search)
        val searchView: SearchView = menuItem.actionView as SearchView
        searchView.queryHint = getString(R.string.search)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                queryTextChangeInSearchMenu(newText)
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun queryTextChangeInSearchMenu(newText: String?) {
        val accessToken = intent.getStringExtra(Constants.HEADER_ACCESS_TOKEN)
        val uid = intent.getStringExtra(Constants.HEADER_UID)
        val client = intent.getStringExtra(Constants.HEADER_CLIENT)
        val dataService: DataService = Api.setupRetrofit()!!.create(DataService::class.java)
        val call: Call<EnterpriseListResponse?> = dataService.recoverEnterpriseListResponse(
            newText,
            accessToken, client, uid
        )
        mainInformationTextView!!.visibilityGone()
        getEnterpriseResponse(call)
    }

    private fun getEnterpriseResponse(call: Call<EnterpriseListResponse?>) {
        call.enqueue(object : Callback<EnterpriseListResponse?> {
            override fun onResponse(
                call: Call<EnterpriseListResponse?>,
                response: Response<EnterpriseListResponse?>
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<EnterpriseListResponse?>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun setupToolBar() {
        setSupportActionBar(mainToolBar)
        supportActionBar?.setLogo(R.drawable.img_logo_nav)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        supportActionBar?.setDisplayUseLogoEnabled(true)
    }

    private fun findViewsById() {
        mainInformationTextView = findViewById(R.id.mainInformationTextView)
        mainToolBar = findViewById(R.id.mainToolBar)
        mainRecyclerView = findViewById(R.id.mainRecyclerView)
    }
}