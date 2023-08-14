package com.example.flightsearch.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.ScreenType

@Composable
fun FlightSearchHomeScreen(
    flightSearchUIState: FlightSearchUIState,
    modifier: Modifier = Modifier,
    airports: List<Airport>,
    destinationAirports: List<Airport>,
    onClickNavigateToScreen: (screenType: ScreenType) -> Unit,
    updateUIForSearchText: (searchString: String) -> Unit,
    updateUiStateForSelectedAirport: (selectedAirport: Airport) -> Unit,
    ) {
    Box(){
        FlightSearchAppContent(
            flightSearchUIState = flightSearchUIState,
            airports = airports,
            destinationAirports = destinationAirports,
            onClickNavigateToScreen = onClickNavigateToScreen,
            updateUIForSearchText = updateUIForSearchText,
            updateUiStateForSelectedAirport = updateUiStateForSelectedAirport
        )
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FlightSearchInputBox(
    flightSearchUIState: FlightSearchUIState,
    updateUIForSearchText: (searchString: String) -> Unit,
    onClickNavigateToScreen: (screenType: ScreenType) -> Unit,
) {
    var text by remember { mutableStateOf("") }
    Row(
        Modifier
            .padding(start = 1.dp, end = 1.dp, top = 75.dp, bottom = 55.dp)
            .fillMaxWidth()
    ){
        val focusManager = LocalFocusManager.current
        TextField(
            maxLines = 1,
            value = text,
            //keyboard actions to hide keyboard when clicking enter
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password),
            onValueChange = {
                text = it.replace("\n", "")
                updateUIForSearchText(it.replace("\n", ""))
                if (text == ""){
                    onClickNavigateToScreen(ScreenType.Home)
                } else{
                    onClickNavigateToScreen(ScreenType.AutoComplete)
                }
                },
//            value = text,
//            onValueChange = { text = it },
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
    destinationAirports: List<Airport>,
    updateUIForSearchText: (searchString: String) -> Unit,
    onClickNavigateToScreen: (screenType: ScreenType) -> Unit,
    updateUiStateForSelectedAirport: (selectedAirport: Airport) -> Unit,
){
    FlightSearchTopAppBar(
        flightSearchUIState = flightSearchUIState,
        onClickNavigateToScreen = onClickNavigateToScreen
    )
    FlightSearchInputBox(
        flightSearchUIState = flightSearchUIState,
        updateUIForSearchText = updateUIForSearchText,
        onClickNavigateToScreen = onClickNavigateToScreen
    )
    if (flightSearchUIState.currentScreen == ScreenType.Home) {
            //TODO: Show favorites list
    }
    else if (flightSearchUIState.currentScreen == ScreenType.AutoComplete) {
        AutoCompleteList(
            onClickNavigateToScreen = onClickNavigateToScreen,
            airports = airports,
            updateUiStateForSelectedAirport = updateUiStateForSelectedAirport
        )
    }
    else if (flightSearchUIState.currentScreen == ScreenType.AirportDetail){
        DestinationList(
            onClickNavigateToScreen = onClickNavigateToScreen,
            airports = destinationAirports,
            updateUiStateForSelectedAirport = updateUiStateForSelectedAirport,
            flightSearchUIState = flightSearchUIState
            )
    }

}

@Composable
fun AutoCompleteList(
    onClickNavigateToScreen: (screenType: ScreenType) -> Unit,
    airports: List<Airport>,
    updateUiStateForSelectedAirport: (selectedAirport: Airport) -> Unit
){
    LazyColumn(
        Modifier.padding(top = 160.dp),
        verticalArrangement = Arrangement.spacedBy(.1.dp) // Adjust the spacing here

    ) {
        itemsIndexed(airports) { index, airport ->
            FlightItemCard(
                airport = airport,
                onClickNavigateToScreen = onClickNavigateToScreen,
                updateUiStateForSelectedAirport = updateUiStateForSelectedAirport
            )
//                Spacer(modifier = Modifier.height(1.dp))
        }
    }
}


@Composable
fun DestinationList(
    onClickNavigateToScreen: (screenType: ScreenType) -> Unit,
    airports: List<Airport>,
    updateUiStateForSelectedAirport: (selectedAirport: Airport) -> Unit,
    flightSearchUIState: FlightSearchUIState,
){
    LazyColumn(
        Modifier.padding(top = 160.dp),
        verticalArrangement = Arrangement.spacedBy(.1.dp) // Adjust the spacing here

    ) {
        itemsIndexed(airports) { index, airport ->
            DestinationCard(
                airport = airport,
                flightSearchUIState = flightSearchUIState
            )
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightItemCard(
    airport: Airport,
    modifier: Modifier = Modifier,
    onClickNavigateToScreen: (screenType: ScreenType) -> Unit,
    updateUiStateForSelectedAirport: (selectedAirport: Airport) -> Unit,
) {
    Card(
        onClick = {
            onClickNavigateToScreen(ScreenType.AirportDetail)
            updateUiStateForSelectedAirport(airport)
            },

        modifier
            .padding(start = 20.dp, end = 20.dp),
        shape = RoundedCornerShape(4),
        colors = CardDefaults.cardColors(
//            containerColor = Color(145,153,255, 250),
            containerColor = Color.DarkGray
        )
        )
        {
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
}


@Composable
private fun FlightSearchTopAppBar(
    flightSearchUIState: FlightSearchUIState,
    modifier: Modifier = Modifier,
    onClickNavigateToScreen: (screenType: ScreenType) -> Unit,
) {

    val currentPage = flightSearchUIState.currentScreen

    Box(
        modifier = Modifier
            .background(Color.Black)
            .height(55.dp)
            .fillMaxWidth()
    ){
        if (currentPage != ScreenType.Home) {
            IconButton(
                onClick = {
                    onClickNavigateToScreen(ScreenType.Home)
                },
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back",
                    tint = Color.White,
                    modifier = Modifier
                        .padding(top = 9.dp),
                )
            }
        }
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 12.dp),
        ) {
            Text(
                text = "Flight Sear️ch App ✈️",
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Row(){
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "OSU CS 492 - © Michael Iwanek",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 9.sp
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DestinationCard(
    airport: Airport,
    modifier: Modifier = Modifier,
    flightSearchUIState: FlightSearchUIState,
) {

    val selectedAirport = flightSearchUIState.selectedAirportObject

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
                .padding(start = 15.dp, end = 15.dp, top = 5.dp, bottom = 5.dp)
                .fillMaxWidth()
//                .height(100.dp)

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
//                    .padding(start = 15.dp, end = 15.dp, top = 5.dp, bottom = 5.dp)
                    .fillMaxWidth(.9f)
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
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "favorite",
                    tint = Color.Gray,
                    modifier = Modifier
                        .fillMaxSize(.9f)
                )
            }
        }

    }
}

