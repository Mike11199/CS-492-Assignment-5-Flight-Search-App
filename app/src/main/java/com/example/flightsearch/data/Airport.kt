package com.example.flightsearch.data


import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Airport data class represents a single row in the database.
 */
@Entity(tableName = "airport")
data class Airport(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val iata_code: String,
    val name: String,
    val passengers: Int
)
