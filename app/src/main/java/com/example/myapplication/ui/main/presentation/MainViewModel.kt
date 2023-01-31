package com.example.myapplication.ui.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myapplication.ui.main.data.MarsPhotoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import com.example.myapplication.ui.main.data.NasaResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel private constructor(
    private val repository: MarsPhotoRepository
) : ViewModel() {
    constructor() : this(MarsPhotoRepository())

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    val pagedMarsInfo: Flow<PagingData<NasaResult>> = Pager(
        config = PagingConfig(pageSize = 5),
        initialKey = null,
        pagingSourceFactory = { MarsInfoPagingSource() }
    ).flow.cachedIn(viewModelScope)

    init {
        pagedMarsInfo
    }
}