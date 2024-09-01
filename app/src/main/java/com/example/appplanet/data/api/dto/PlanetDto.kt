package com.example.appplanet.data.api.dto

import kotlinx.serialization.Serializable

@Serializable
data class PlanetDto (
    val id : Int,
    val name: String,
    val description: String,
    val url: String,
)