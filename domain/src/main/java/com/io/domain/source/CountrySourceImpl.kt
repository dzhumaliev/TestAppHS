package com.io.domain.source

import com.io.domain.models.Country
import com.io.domain.models.CountryDetail

interface CountrySourceImpl {
    suspend fun load(): List<Country>

    suspend fun loadDetail(code: String): CountryDetail

}