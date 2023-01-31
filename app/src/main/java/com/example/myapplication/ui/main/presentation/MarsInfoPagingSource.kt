package com.example.myapplication.ui.main.presentation

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myapplication.ui.main.data.MarsPhotoRepository
import com.example.myapplication.ui.main.data.NasaResult

class MarsInfoPagingSource : PagingSource<Int, NasaResult>() {

    private val repository = MarsPhotoRepository()
    override fun getRefreshKey(state: PagingState<Int, NasaResult>): Int = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NasaResult> {
        val page = params.key ?: FIRST_PAGE
        return kotlin.runCatching {
            repository.getMarsInfo(page)
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    data = it!!,
                    prevKey = null,
                    nextKey = if (it.isEmpty()) null else page + FIRST_PAGE
                )
            },
            onFailure = { LoadResult.Error(it) }
        )
    }

    private companion object {
        private const val FIRST_PAGE = 1
    }
}