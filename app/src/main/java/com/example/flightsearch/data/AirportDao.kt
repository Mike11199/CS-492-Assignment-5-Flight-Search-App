package com.example.flightsearch.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface AirportDao {
    @Query(
        """
        SELECT * FROM airport
        """
    )
    fun getAll(): Flow<List<Airport>>

    @Query(
        """
        SELECT * FROM airport 
        WHERE 
            (name LIKE '%' || :searchText || '%'
            OR iata_code LIKE '%' || :searchText || '%')
        ORDER BY passengers DESC;
        """
    )
    fun searchAirport(searchText: String): Flow<List<Airport>>

    @Query(
        """
        SELECT * FROM airport 
        WHERE airport.id != :destinationId
        ORDER BY passengers DESC;
        """
    )
    fun searchDestination(destinationId: Int): Flow<List<Airport>>
}
