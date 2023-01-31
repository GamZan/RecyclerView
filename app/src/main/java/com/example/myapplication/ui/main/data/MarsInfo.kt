package com.example.myapplication.ui.main.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MarsInfo(
    @SerializedName("photos")
    var photos: List<NasaResult>? = null
) : Serializable
