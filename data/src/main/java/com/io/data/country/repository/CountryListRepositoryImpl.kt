package com.io.data.country.repository

import com.io.data.country.source.CountrySource
import com.io.domain.models.Country
import com.io.domain.models.CountryDetail
import com.io.domain.repository.CountryRepository
import dagger.hilt.components.SingletonComponent
import it.czerwinski.android.hilt.annotations.BoundTo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


@BoundTo(supertype = CountryRepository::class, component = SingletonComponent::class)
class CountryListRepositoryImpl @Inject constructor(
    private val source: CountrySource
) : CountryRepository {

    override suspend fun getCountriesList(): List<Country> = source.load()

    override suspend fun getCountryDetail(code: String): CountryDetail = source.loadDetail(code)


}