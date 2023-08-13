package com.example.flightsearch.ui
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearch.ui.FlightSearchViewModel


@Composable
fun FlightSearchApp(
    viewModel: FlightSearchViewModel = viewModel(factory = FlightSearchViewModel.factory)
) {
    val flightSearchUIState = viewModel.uiState.collectAsState().value
    val allAirports by viewModel.getAllAirports().collectAsState(emptyList())
    Box() {
        FlightSearchHomeScreen(
            flightSearchUIState = flightSearchUIState
        )
    }
}




