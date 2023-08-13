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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.flightsearch.data.Airport

@Composable
fun FlightSearchHomeScreen(
    flightSearchUIState: FlightSearchUIState,
    modifier: Modifier = Modifier,
    airports: List<Airport>,
    ) {
    Box(){
        FlightSearchAppContent(
            flightSearchUIState = flightSearchUIState,
            airports = airports
        )
    }
}

@Composable
fun FlightSearchInputBox() {
    var text by remember { mutableStateOf("") }
    Row(
        Modifier
            .padding(start = 1.dp, end = 1.dp, top = 25.dp, bottom = 25.dp)
            .fillMaxWidth()
    ){
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter departure airport") },
            modifier = Modifier
                .padding(start = 25.dp, end = 25.dp, top = 2.dp, bottom = 2.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(50),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon"
                )
            },
        )
    }
}

@Composable
private fun FlightSearchAppContent(
    flightSearchUIState: FlightSearchUIState,
    airports: List<Airport>,
){
    FlightSearchInputBox()

    LazyColumn (
        Modifier.padding(top = 120.dp)
    ) {
        itemsIndexed(airports) { index, airport ->
            FlightItemCard(
                airport = airport,
            )
            Spacer(modifier = Modifier.height(5.dp))
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
            .padding(start = 20.dp, end = 20.dp),
        shape = RoundedCornerShape(10),
        colors = CardDefaults.cardColors(
            containerColor = Color(145,153,255, 250),
        )) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
            .padding(start = 15.dp, end = 15.dp, top = 5.dp, bottom = 5.dp)
                .fillMaxWidth()
                .height(30.dp)
        ){
            Row(
                Modifier
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(end=15.dp),
                ) {
                    Text(
                        text = airport.iata_code,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                    )
                }
                Row(
                    modifier = Modifier
                ) {
                    Text(
                        text = airport.name,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}

