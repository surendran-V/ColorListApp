package com.digitaldomi.colorlistapp.data.local
import androidx.room.*
import com.digitaldomi.colorlistapp.data.model.ColorEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface ColorDao {
    @Query("SELECT * FROM colors ORDER BY timestamp DESC")
    fun getAllColors(): Flow<List<ColorEntry>>

    @Query("SELECT COUNT(*) FROM colors WHERE isSynced = 0")
    fun getUnsyncedCount(): Flow<Int>

    @Query("SELECT * FROM colors WHERE isSynced = 0")
    suspend fun getUnsyncedColors(): List<ColorEntry>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertColor(color: ColorEntry)

    @Update
    suspend fun updateColors(colors: List<ColorEntry>)
}