package com.io.testapphs.ui.fragments.countryDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.io.domain.country.state.gone
import com.io.domain.country.state.visible
import com.io.testapphs.databinding.FragmentCountryDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CountryDetailFragment : Fragment() {

    private var _binding: FragmentCountryDetailBinding? = null
    private val binding: FragmentCountryDetailBinding
        get() = _binding!!
    private val viewModel by viewModels<CountryDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCountryDetailBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.detail.collect {
                    binding.apply {
                        cvDetail.visible()
                        tvEmoji.text = it.emoji
                        tvName.text = it.name
                        tvCapital.text = it.capital
                        tvCurrency.text = it.currency
                        tvPhone.text = it.phone
                    }
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.error.collect {
                    binding.cvDetail.gone()
                    with(binding.tvError) {
                        text = it
                        visible()
                    }
                }
            }
        }
    }

    fun updateData(code: String) {
        viewModel.getDetailInfo(code, requireContext())
    }

}