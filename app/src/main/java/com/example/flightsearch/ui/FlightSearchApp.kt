package com.example.flightsearch.ui
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.Favorite
import com.example.flightsearch.data.ScreenType
import com.example.flightsearch.ui.FlightSearchViewModel
import kotlinx.coroutines.launch


@Composable
fun FlightSearchApp(
    viewModel: FlightSearchViewModel = viewModel(factory = FlightSearchViewModel.factory)

) {
    val flightSearchUIState = viewModel.uiState.collectAsState().value
//    val allAirports by viewModel.getAllAirports().collectAsState(emptyList())
    val searchedAirports by viewModel.searchAirports(flightSearchUIState.searchString).collectAsState(emptyList())
    val destinationAirports by viewModel.searchDestinations(flightSearchUIState.selectedAirport).collectAsState(emptyList())
    val coroutineScope = rememberCoroutineScope()


    Box() {
        FlightSearchHomeScreen(
            flightSearchUIState = flightSearchUIState,
            airports = searchedAirports,
            destinationAirports = destinationAirports,
            onClickNavigateToScreen = { screenType: ScreenType ->
                viewModel.navigateToScreen(screenType)
            },
            updateUIForSearchText = {searchString: String ->
                viewModel.updateUiStateForSearchedAirport(searchString)
            },
            updateUiStateForSelectedAirport = {selectedAirport: Airport ->
                viewModel.updateUiStateForSelectedAirport(selectedAirport)
            },
            onClickAddFavorite = {favorite: Favorite ->
                coroutineScope.launch {
                    viewModel.saveItem(favorite)
                }
            },
        )
    }
}




