package com.example.flightsearch.ui


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.graphics.RectangleShape
import com.example.flightsearch.data.Airport

@Composable
fun FlightSearchHomeScreen(
    flightSearchUIState: FlightSearchUIState,
    modifier: Modifier = Modifier,
    ) {
    Box(){
        FlightSearchAppContent(
            flightSearchUIState = flightSearchUIState
        )
    }

}


@Composable
private fun FlightSearchAppContent(
    flightSearchUIState: FlightSearchUIState,
){
    val airports = listOf(
        Airport(1, "SMF", "Sacramento", 2343),
        Airport(2, "LAX", "Los Angeles", 4567),
        Airport(3, "JFK", "New York", 7890),
        Airport(4, "ORD", "Chicago", 5678)
    )

    LazyColumn (
        Modifier.padding(top = 80.dp)
    ) {
        itemsIndexed(airports) { index, airport ->
            FlightItemCard(
                airport = airport,
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightItemCard(
    airport: Airport,
    modifier: Modifier = Modifier,
) {
    Card(
        {  }, modifier
            .padding(start = 15.dp, end = 15.dp),
        shape= RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = Color(145,153,255, 250),
        )) {
        Row(
            Modifier
                .padding(start = 1.dp, end=1.dp, top=5.dp, bottom=5.dp)
                .fillMaxWidth()
        ){
            Row(
                Modifier
            ) {
                Row(
                    modifier = Modifier
                        .padding(start=5.dp, end=15.dp),
                ) {
                    Text(
                        text = airport.iata_code,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                    )
                }
                Row(
                    modifier = Modifier

                ) {
                    Text(
                        text = airport.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                    )
                }
            }
        }
    }
}

