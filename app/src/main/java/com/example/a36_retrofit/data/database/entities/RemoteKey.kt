package com.example.a36_retrofit.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "movie_remote_keys"
)
data class RemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val next_page: Int?,
    val last_updated: Long
)
