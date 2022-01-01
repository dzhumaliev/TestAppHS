package com.io.testapphs.ui.fragments.countryDetail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.io.domain.country.state.Resource
import com.io.domain.country.state.convertErrors
import com.io.domain.country.usecase.CountryDetailUseCase
import com.io.domain.models.CountryDetail
import com.io.testapphs.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryDetailViewModel @Inject constructor(
    private val useCase: CountryDetailUseCase
) : ViewModel() {

    private val _detail = MutableSharedFlow<CountryDetail>()
    val detail: SharedFlow<CountryDetail> = _detail

    private val _error =
        MutableStateFlow("")
    val error: Flow<String> = _error

    fun getDetailInfo(code: String, context: Context) {
        viewModelScope.launch {
            useCase.invoke(code).collect {
                when (it) {
                    is Resource.Success -> {
                        if (it.data != null) {
                            _detail.emit(it.data!!)
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