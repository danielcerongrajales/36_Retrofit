package com.example.a36_retrofit.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.a36_retrofit.data.database.entities.RemoteKey

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKeys(keys: List<RemoteKey>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKey(key: RemoteKey)

    @Query("select * from movie_remote_keys where id=:key")
    suspend fun getKeyByMovie(key: String): RemoteKey?

    @Query("delete from movie_remote_keys")
    suspend fun clearKeys()
}