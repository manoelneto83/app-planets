package com.example.appplanet.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PlanetEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun planetDao():PlanetsDao
}