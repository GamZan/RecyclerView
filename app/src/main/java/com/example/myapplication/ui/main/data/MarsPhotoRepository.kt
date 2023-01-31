package com.example.myapplication.ui.main.data

import com.example.myapplication.ui.main.data.api.ApiClient

class MarsPhotoRepository {
    suspend fun getMarsInfo(page: Int): List<NasaResult>? {
        return ApiClient.getApiClient().getMarsInfo(page).photos
    }
}