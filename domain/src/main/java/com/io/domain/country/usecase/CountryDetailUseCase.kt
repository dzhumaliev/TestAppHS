package com.io.domain.country.usecase

import com.io.domain.country.state.Resource
import com.io.domain.models.Country
import com.io.domain.models.CountryDetail
import com.io.domain.repository.CountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class CountryDetailUseCase @Inject constructor(private val countryListRepo: CountryRepository) {

    suspend operator fun invoke(code: String): Flow<Resource<CountryDetail>> = flow {
        emit(Resource.Loading())
        try {
            emit(
                Resource.Success(
                    countryListRepo.getCountryDetail(code)
                )
            )
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString(), null))
        }
    }

}