package com.example.megokinopoisk.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FilmDetailsDTO(
        @SerializedName("original_title") val name: String,
        @SerializedName("overview") val description: String
) : Serializable