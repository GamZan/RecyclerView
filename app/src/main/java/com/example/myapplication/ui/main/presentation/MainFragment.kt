package com.example.myapplication.ui.main.presentation


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.paging.LoadState
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MainViewModel>()

    private val pagedAdapter = PagingAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = pagedAdapter.withLoadStateFooter(MarsLoadStateAdapter())

        viewModel.pagedMarsInfo.onEach {
            pagedAdapter.submitData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        binding.recyclerView.setOnClickListener {
            it.findNavController().navigate(R.id.secondFragment)
        }

        pagedAdapter.loadStateFlow.onEach {
            binding.swipeRefresh.isRefreshing = it.refresh == LoadState.Loading
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        binding.swipeRefresh.setOnRefreshListener {
            pagedAdapter.loadStateFlow.onEach {
                val state = it.refresh
                binding.swipeRefresh.isRefreshing = state == LoadState.Loading
            }.launchIn(viewLifecycleOwner.lifecycleScope)
        }

        viewModel.isLoading.onEach {
            binding.swipeRefresh.isRefreshing = it
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        pagedAdapter.loadStateFlow.onEach {
            val state = it.refresh
            binding.swipeRefresh.isRefreshing = state == LoadState.Loading
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}