package com.example.flightsearch.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteDao {
    @Query(
        """
        Select * from favorite
        """
    )
    fun getAll(): Flow<List<Favorite>>

}
