package com.example.flightsearch.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.Favorite
import com.example.flightsearch.data.ScreenType


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DestinationCard(
    airport: Airport,
    modifier: Modifier = Modifier,
    flightSearchUIState: FlightSearchUIState,
    onClickAddFavorite: (favorite: Favorite) -> Unit,
    favorites: List<Favorite>,
    onClickRemoveFavorite: (favorite: Favorite) -> Unit,
) {

    val selectedAirport = flightSearchUIState.selectedAirportObject
    val isAirportFavorited = favorites.find {
            favorite -> favorite.departure_code == selectedAirport.iata_code &&
            favorite.destination_code == airport.iata_code
    }


    Card(
        onClick = {
        },
        modifier
            .padding(start = 20.dp, end = 20.dp),
        shape = RoundedCornerShape(10),
        colors = CardDefaults.cardColors(
            containerColor = Color(5,50,115, 250),
        )
    )
    {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 15.dp, end = 1.dp, top = 5.dp, bottom = 5.dp)
                .fillMaxWidth()
//                .height(100.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
//                    .padding(start = 15.dp, end = 15.dp, top = 5.dp, bottom = 5.dp)
                    .fillMaxWidth(.75f)
//                    .height(100.dp)
            ){
                Column() {
                    Row(
                        Modifier
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(end=15.dp),
                        ) {
                            Text(
                                text = "DEPART",
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .padding(bottom = 15.dp),
                    ) {
//                    Spacer(modifier = Modifier.width(15.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(end=15.dp),
                        ) {
                            Text(
                                text = selectedAirport.iata_code,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = Color.White
                            )
                        }
                        Row(
                            modifier = Modifier
                        ) {
                            Text(
                                text = selectedAirport.name,
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp,
                                overflow = TextOverflow.Ellipsis,
                                color = Color.White
                            )
                        }
                    }
                    Row(
                        Modifier
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(end=15.dp),
                        ) {
                            Text(
                                text = "ARRIVE",
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                    }
                    Row(
                        Modifier
                    ) {
//                    Spacer(modifier = Modifier.width(15.dp))
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
                                color = Color.White
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
                                color = Color.White
                            )
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
            ) {
                if (isAirportFavorited != null){
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Transparent
                        ),
                        onClick = {
                            onClickRemoveFavorite(isAirportFavorited)
                        },
                    ){
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "favorite",
                            tint = Color(244,214,79, 250),
                            modifier = Modifier
                                .fillMaxSize(1f)
                                .background(
                                    color = Color.Transparent,
                                )
                        )
                    }
                }
                else {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Transparent
                        ),
                        onClick = {
                            onClickAddFavorite(
                                Favorite(
                                    departure_code = selectedAirport.iata_code,
                                    destination_code = airport.iata_code
                                )
                            )
                        },
                    ){
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "favorite",
                            tint = Color.DarkGray,
                            modifier = Modifier
                                .fillMaxSize(1f)
                                .background(
                                    color = Color.Transparent,
                                )
                        )
                    }
                }
            }
        }

    }
}


@Composable
fun DestinationList(
    onClickNavigateToScreen: (screenType: ScreenType) -> Unit,
    airports: List<Airport>,
    updateUiStateForSelectedAirport: (selectedAirport: Airport) -> Unit,
    flightSearchUIState: FlightSearchUIState,
    onClickAddFavorite: (favorite: Favorite) -> Unit,
    favorites: List<Favorite>,
    onClickRemoveFavorite: (favorite: Favorite) -> Unit,
){
    LazyColumn(
        Modifier.padding(top = 160.dp),
        verticalArrangement = Arrangement.spacedBy(.1.dp) // Adjust the spacing here

    ) {
        itemsIndexed(airports) { index, airport ->
            DestinationCard(
                onClickAddFavorite = onClickAddFavorite,
                onClickRemoveFavorite = onClickRemoveFavorite,
                airport = airport,
                flightSearchUIState = flightSearchUIState,
                favorites = favorites
            )
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}
