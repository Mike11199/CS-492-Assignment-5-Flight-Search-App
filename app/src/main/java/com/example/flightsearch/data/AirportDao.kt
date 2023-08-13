package com.example.flightsearch.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface AirportDao {
    @Query(
        """
        Select * from airport
        """
    )
    fun getAll(): Flow<List<Airport>>

}
