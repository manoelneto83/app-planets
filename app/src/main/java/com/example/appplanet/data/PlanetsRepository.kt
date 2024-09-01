package com.example.appplanet.data

import com.example.appplanet.data.api.ApiResult
import com.example.appplanet.data.api.PlanetsService
import com.example.appplanet.data.database.PlanetEntity
import com.example.appplanet.data.database.PlanetsDao
import com.example.appplanet.ui.main.PlanetData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PlanetsRepository @Inject constructor(
    private val service: PlanetsService,
    private val dao: PlanetsDao
) {

    suspend fun getPlanetsList(): ApiResult<List<PlanetData>> {
        return withContext(Dispatchers.IO) {
            try {

                val planetsDto = service.getPlanets();
                val data = planetsDto.map { PlanetData(it.id, it.name, it.description, it.url) }

                val planetEntity =
                    planetsDto.map { PlanetEntity(it.id, it.name, it.description, it.url) }
                dao.insert(planetEntity)
                ApiResult.Success(data)
            } catch (e: Exception) {
                val planets = dao.getAll()
                if (planets.isEmpty()) {
                    ApiResult.Error(e)
                } else {
                    ApiResult.Success(planets.map {
                        PlanetData(
                            it.id,
                            it.name,
                            it.description,
                            it.url
                        )
                    })
                }

            }

        }
    }

    suspend fun getPlanetDetails(id: Int): ApiResult<PlanetData> {
        return withContext(Dispatchers.IO) {
            try {

                val planetsDto = service.getPlanets(id);
                ApiResult.Success(
                    PlanetData(
                        planetsDto.id,
                        planetsDto.name,
                        planetsDto.description,
                        planetsDto.url
                    )
                )
            } catch (e: Exception) {
                ApiResult.Error(e)
            }
        }
    }
}