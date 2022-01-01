package com.io.domain.repository

import com.io.domain.models.Country
import com.io.domain.models.CountryDetail

interface CountryRepository {

    suspend fun getCountriesList(): List<Country>

    suspend fun getCountryDetail(code: String): CountryDetail
}