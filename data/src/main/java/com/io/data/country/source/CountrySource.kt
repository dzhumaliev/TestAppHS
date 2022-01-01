package com.io.data.country.source

import com.apollographql.apollo3.ApolloClient
import com.io.data.CountriesListQuery
import com.io.data.CountryDetailQuery
import com.io.data.country.db.AppDatabase
import com.io.data.country.mapper.mapDBEntityToModels
import com.io.data.country.mapper.mapDetailModelToDBEntity
import com.io.data.country.mapper.mapModelToDBEntity
import com.io.domain.models.Country
import com.io.domain.models.CountryDetail
import com.io.domain.source.CountrySourceImpl
import javax.inject.Inject

class CountrySource @Inject constructor(
    private val apolloClient: ApolloClient,
    private val database: AppDatabase
) : CountrySourceImpl {

    override suspend fun load(): List<Country> {

        return if (database.countryDao().getAll().isNullOrEmpty()) {
            val response =
                apolloClient.query(CountriesListQuery()).execute()

            if (!response.hasErrors() && response.data != null) {
                database.countryDao().addAll(mapModelToDBEntity(response.data?.countries!!))
            }
            mapDBEntityToModels(database.countryDao().getAll())

        } else {
            mapDBEntityToModels(database.countryDao().getAll())
        }
    }

    override suspend fun loadDetail(code: String): CountryDetail {

        return if (database.countryDao().getByName(code) == null) {
            val response =
                apolloClient.query(CountryDetailQuery(code)).execute()

            if (!response.hasErrors() && response.data != null) {
                database.countryDao().addDetail(
                    mapDetailModelToDBEntity(response.data?.country!!)
                )
            }

            mapDBEntityToModels(database.countryDao().getByName(code)!!)
        } else {
            mapDBEntityToModels(database.countryDao().getByName(code)!!)
        }
    }


}