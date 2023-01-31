package com.example.myapplication.ui.main.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NasaResult(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("sol")
    var sol: Int? = null,
    @SerializedName("camera")
    var camera: Camera? = null,
    @SerializedName("img_src")
    var image: String? = null,
    @SerializedName("earth_date")
    var earthDate: String? = null,
    @SerializedName("rover")
    var rover: Rover? = null
) : Serializable
