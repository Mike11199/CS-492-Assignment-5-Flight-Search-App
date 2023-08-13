package com.example.flightsearch.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.flightsearch.data.ScreenType
import kotlinx.coroutines.flow.update

class FlightSearchViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(FlightSearchUIState())
    val uiState: StateFlow<FlightSearchUIState> = _uiState

    init {
        initializeUIState()
    }

    private fun initializeUIState() {

        _uiState.value =
            FlightSearchUIState(

            )
    }

    fun navigateToHomePage() {
        _uiState.update {
            it.copy(
                currentScreen = ScreenType.Home
            )
        }
    }

}