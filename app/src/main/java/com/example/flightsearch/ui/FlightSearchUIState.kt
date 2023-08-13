package com.example.flightsearch.ui

import com.example.flightsearch.data.ScreenType


data class FlightSearchUIState(
    val currentScreen: ScreenType = ScreenType.Home
){}