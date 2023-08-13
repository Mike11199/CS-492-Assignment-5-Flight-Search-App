package com.example.flightsearch.data


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "departure_code")
    val departure_code: String,
    @ColumnInfo(name = "destination_code")
    val destination_code: String,
)
