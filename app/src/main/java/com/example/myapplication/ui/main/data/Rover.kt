package com.example.myapplication.ui.main.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Rover(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("landing_date")
    var landingDate: String? = null,
    @SerializedName("launch_date")
    var launchDate: String? = null,
    @SerializedName("status")
    var status: String? = null
) : Serializable
