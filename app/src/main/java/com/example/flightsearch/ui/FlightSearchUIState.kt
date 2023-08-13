package com.example.flightsearch.ui

import com.example.flightsearch.data.ScreenType


data class FlightSearchUIState(
    val currentScreen: ScreenType = ScreenType.Home,
    val searchString: String = "",
    val selectedAirport: Int = 1,  // this is id of airport
){}