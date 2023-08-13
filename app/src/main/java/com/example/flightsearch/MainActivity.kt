package com.example.flightsearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.flightsearch.ui.FlightSearchApp
import com.example.flightsearch.ui.theme.MyApplicationTheme

/**************************************************************************************************
 * OSU - Oregon State University
 *      CS 492 - Mobile Development
 *
 * Assignment #5:   Data Persistence
 * Name:            Michael Iwanek
 * Date:            8/12/2023
 *
 *************************************************************************************************/

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.DarkGray
                ) {
                    FlightSearchApp()
                }
            }
        }
    }
}
