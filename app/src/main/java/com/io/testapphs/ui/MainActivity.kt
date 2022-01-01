package com.io.testapphs.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.io.testapphs.R
import com.io.testapphs.ui.fragments.countryDetail.CountryDetailFragment
import com.io.testapphs.ui.fragments.list.CountryListFragment
import com.io.testapphs.ui.fragments.list.adapter.ICodeListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ICodeListener {

    lateinit var countryDetail: CountryDetailFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createFragment()
    }

    private fun createFragment() {
        val countryList = CountryListFragment()
        countryDetail = CountryDetailFragment()

        val fm: FragmentManager = supportFragmentManager
        fm.beginTransaction().replace(R.id.fragment_list, countryList).commit()
        fm.beginTransaction().replace(R.id.fragment_detail, countryDetail).commit()

    }

    override fun onSend(code: String) {
        countryDetail.updateData(code)
    }
}