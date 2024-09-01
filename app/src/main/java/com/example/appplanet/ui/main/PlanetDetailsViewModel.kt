package com.example.appplanet.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appplanet.data.PlanetsRepository
import com.example.appplanet.data.api.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetDetailsViewModel
@Inject constructor(private val repository: PlanetsRepository) : ViewModel() {

    val state = MutableLiveData<State>()

    fun loadPlanetDetails(id: Int) {
        state.value = State.Loading

        viewModelScope.launch {
            val result = repository.getPlanetDetails(id)
            when (result) {
                is ApiResult.Success -> {
                    state.value = State.Content(result.data)
                }

                is ApiResult.Error -> {
                    state.value = State.Error
                }

            }


        }
    }

    sealed class State {
        data class Content(val planet: PlanetData) : State()
        object Loading : State()
        object Error : State();
    }
}

