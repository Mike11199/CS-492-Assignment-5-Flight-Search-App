package com.example.flightsearch.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
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



    @Insert(onConflict = OnConflictStrategy.ABORT)
     suspend fun addFavorite(favorite: Favorite)

    @Delete
    suspend fun removeFavorite(favorite: Favorite)

}
