package com.io.testapphs.ui.fragments.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.io.domain.country.state.visible
import com.io.testapphs.databinding.FragmentCountryListBinding
import com.io.testapphs.ui.fragments.list.adapter.CountryAdapter
import com.io.testapphs.ui.fragments.list.adapter.ICodeListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CountryListFragment : Fragment() {

    private var _binding: FragmentCountryListBinding? = null
    private val binding: FragmentCountryListBinding
        get() = _binding!!

    private val viewModel by viewModels<CountryListViewModel>()
    private var adapter: CountryAdapter? = null
    private var listenerCode: ICodeListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCountryListBinding.inflate(inflater, container, false)
        initAdapter()
        return _binding?.root
    }

    private fun initAdapter() {
        adapter = CountryAdapter()
        binding.rvCountryList.adapter = adapter

        adapter?.onItemClicked = { country ->
            listenerCode?.onSend(country.code)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCountryList(requireContext())

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.list.collect {
                    adapter?.submitList(it)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.error.collect {
                    with(binding.tvError) {
                        text = it
                        visible()
                    }

                }
            }
        }


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ICodeListener) {
            listenerCode = context
        } else {
            throw RuntimeException("$context must implement ICodeListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerCode = null
    }

}