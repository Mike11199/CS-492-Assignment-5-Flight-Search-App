package com.example.flightsearch.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.flightsearch.data.ScreenType
import kotlinx.coroutines.flow.update
import com.example.flightsearch.FlightSearchApplication
import com.example.flightsearch.data.AirportDao
import kotlinx.coroutines.flow.Flow
import com.example.flightsearch.data.Airport
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import com.example.flightsearch.data.Favorite
import com.example.flightsearch.data.FavoriteDao


class FlightSearchViewModel (private val airportDao: AirportDao, private val favoriteDao: FavoriteDao) : ViewModel() {

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




    fun getAllAirports(): Flow<List<Airport>> = airportDao.getAll()
    fun getAllFavorites(): Flow<List<Favorite>> = favoriteDao.getAll()
    fun searchAirports(
        searchString: String): Flow<List<Airport>> = airportDao.searchAirport(searchString)


    fun updateUiStateForSearchedAirport(searchString: String) {
        _uiState.update {
            it.copy(
                searchString = searchString
            )
        }
    }

    fun navigateToScreen(screenType: ScreenType) {
        _uiState.update {
            it.copy(
                currentScreen = screenType
            )
        }
    }

    companion object {
        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FlightSearchApplication)
                FlightSearchViewModel(application.database.airportDao(), application.database.favoriteDao())
            }
        }
    }

}