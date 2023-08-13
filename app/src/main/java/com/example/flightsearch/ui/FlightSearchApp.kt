package com.example.flightsearch.ui
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel



@Composable
fun FlightSearchApp(

) {
    val viewModel: FlightSearchViewModel = viewModel()
    val flightSearchUIState = viewModel.uiState.collectAsState().value
    Box() {
    FlightSearchHomeScreen(
        flightSearchUIState = flightSearchUIState
    )
    }
}




