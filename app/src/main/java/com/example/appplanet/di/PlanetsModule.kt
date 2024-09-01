package com.example.appplanet.di

import android.content.Context
import androidx.room.Room
import com.example.appplanet.data.PlanetsRepository
import com.example.appplanet.data.api.PlanetsService
import com.example.appplanet.data.database.AppDatabase
import com.example.appplanet.data.database.PlanetsDao
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class PlanetsModule {
    @Provides
    fun providePlanetService(
    ): PlanetsService {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(
                Json.asConverterFactory(MediaType.parse("application/json")!!))
            .baseUrl("http://1aa6-187-19-177-102.ngrok-free.app/")
            .build()

        return retrofit.create(PlanetsService::class.java)
    }

    @Provides
    fun provideRepository(planetsService: PlanetsService, dao:PlanetsDao): PlanetsRepository {
        return PlanetsRepository(planetsService, dao)
    }

    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : AppDatabase {
        return Room.
        databaseBuilder(context, AppDatabase::class.java, "planets-db").build()
    }

    @Provides
    fun provideDao(database: AppDatabase): PlanetsDao {
        return database.planetDao()
    }
}