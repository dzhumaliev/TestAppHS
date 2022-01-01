package com.io.data.country.mapper

import com.io.data.CountriesListQuery
import com.io.data.country.entity.CountryEntity
import com.io.domain.models.Country


fun mapDBEntityToModels(itemList: List<CountryEntity>): List<Country> =
    itemList.map {
        Country(
            it.code,
            it.name,
            it.emoji
        )
    }

fun mapModelToDBEntity(itemList: List<CountriesListQuery.Country>): List<CountryEntity> =
    itemList.map {
        CountryEntity(
            0,
            it.code,
            it.name,
            it.emoji
        )
    }