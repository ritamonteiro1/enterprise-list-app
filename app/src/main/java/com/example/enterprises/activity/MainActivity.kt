package com.example.enterprises.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.enterprises.R

class MainActivity : AppCompatActivity() {
    private var mainInformationTextView: TextView? = null
    private var mainToolBar: Toolbar? = null
    private var mainRecyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewsById()
    }

    private fun findViewsById() {
        mainInformationTextView = findViewById(R.id.mainInformationTextView)
        mainToolBar = findViewById(R.id.mainToolBar)
        mainRecyclerView = findViewById(R.id.mainRecyclerView)
    }
}