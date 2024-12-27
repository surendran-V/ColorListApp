package com.digitaldomi.colorlistapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "colors")
data class ColorEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val color: String,
    val timestamp: Long,
    val isSynced: Boolean = false
)