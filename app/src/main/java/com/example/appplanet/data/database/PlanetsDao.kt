package com.example.appplanet.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PlanetsDao {

    @Insert
    fun insert(planets: List<PlanetEntity>)

    @Query("SELECT * FROM planetentity")
    fun getAll(): List<PlanetEntity>
}