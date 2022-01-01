package com.io.testapphs.ui.fragments.list

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.io.domain.country.state.Resource
import com.io.domain.country.state.convertErrors
import com.io.domain.country.usecase.CountryListUseCase
import com.io.domain.models.Country
import com.io.testapphs.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val useCase: CountryListUseCase
) : ViewModel() {

    private val _list = MutableStateFlow<List<Country>>(emptyList())
    val list: Flow<List<Country>> = _list

    private val _error =
        MutableStateFlow("")
    val error: Flow<String> = _error


    fun getCountryList(context: Context) {
        viewModelScope.launch {
            useCase.invoke().collect {
                when (it) {
                    is Resource.Success -> {
                        if (!it.data.isNullOrEmpty()) {
                            _list.emit(it.data!!)
                        } else {
                            Resource.Error(context.getString(R.string.data_null_or_empty), null)
                        }
                    }
                    is Resource.Error -> {
                        _error.emit(convertErrors(it.message.toString(), context))

                    }
                }
            }
        }
    }
}