package com.example.appplanet.data.api

import com.example.appplanet.data.api.dto.PlanetDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PlanetsService {

    @GET("planets")
    suspend fun getPlanets() : List<PlanetDto>

    @GET("planets/{id}")
    suspend fun getPlanets(@Path("id") planetId: Int) : PlanetDto

}