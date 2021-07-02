package com.example.enterprises.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.enterprises.R
import com.example.enterprises.adapter.EnterpriseListAdapter
import com.example.enterprises.api.Api
import com.example.enterprises.api.DataService
import com.example.enterprises.click.listener.OnEnterpriseItemClickListener
import com.example.enterprises.constants.Constants
import com.example.enterprises.domains.enterprise.EnterpriseListResponse
import com.example.enterprises.domains.enterprise.EnterpriseResponse
import com.example.enterprises.extensions.visibilityGone
import com.example.enterprises.extensions.visibilityVisible
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
        searchView.queryHint = getString(R.string.main_query_hint_menu_search)
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
                if (response.isSuccessful && response.body() != null) {
                    getEnterpriseResponseSuccessfully(response)
                } else {
                    mainInformationTextView?.visibilityVisible()
                    mainRecyclerView?.visibilityGone()
                    mainInformationTextView?.text = getString(R.string.occurred_error)
                }
            }

            override fun onFailure(call: Call<EnterpriseListResponse?>, t: Throwable) {
                mainInformationTextView?.visibilityVisible()
                mainRecyclerView?.visibilityGone()
                mainInformationTextView?.text = getString(R.string.occurred_error)
            }
        })
    }

    private fun getEnterpriseResponseSuccessfully(response: Response<EnterpriseListResponse?>) {
        mainRecyclerView?.visibilityVisible()
        val enterpriseListResponse: EnterpriseListResponse = response.body()!!
        treatEnterpriseListEmpty(enterpriseListResponse.enterprises)
        val enterpriseListAdapter =
            EnterpriseListAdapter(
                enterpriseListResponse.enterprises,
                this@MainActivity,
                object : OnEnterpriseItemClickListener {
                    override fun onClick(enterpriseResponse: EnterpriseResponse?) {
                        val intent = Intent(this@MainActivity, ResultActivity::class.java)
                        intent.putExtra(Constants.ENTERPRISE_RESPONSE_DETAILS, enterpriseResponse)
                        startActivity(intent)
                    }
                })
        setupRecyclerView(enterpriseListAdapter)
    }

    private fun setupRecyclerView(enterpriseListAdapter: EnterpriseListAdapter) {
        mainRecyclerView?.adapter = enterpriseListAdapter
        val layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
        mainRecyclerView?.layoutManager = layoutManager
    }

    private fun treatEnterpriseListEmpty(enterprises: List<EnterpriseResponse>) {
        if (enterprises.isEmpty()) {
            mainInformationTextView?.text = getString(R.string.empty_list_error_message)
            mainInformationTextView?.visibilityVisible()
        }
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