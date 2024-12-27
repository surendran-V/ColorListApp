package com.digitaldomi.colorlistapp.data.repository

import com.digitaldomi.colorlistapp.data.local.ColorDao
import com.digitaldomi.colorlistapp.data.model.ColorEntry
import com.digitaldomi.colorlistapp.data.remote.FirebaseService
import kotlinx.coroutines.flow.Flow
import java.util.*

class ColorRepository(
    private val colorDao: ColorDao,
    private val firebaseService: FirebaseService
) {
    val allColors: Flow<List<ColorEntry>> = colorDao.getAllColors()
    val unsyncedCount: Flow<Int> = colorDao.getUnsyncedCount()

    suspend fun addColor() {
        val randomColor = String.format("#%06X", Random().nextInt(0xFFFFFF + 1))
        val colorEntry = ColorEntry(
            color = randomColor,
            timestamp = System.currentTimeMillis()
        )
        colorDao.insertColor(colorEntry)
    }

    suspend fun syncColors() {
        val unsyncedColors = colorDao.getUnsyncedColors()
        if (unsyncedColors.isEmpty()) return

        val success = firebaseService.syncColors(unsyncedColors)
        if (success) {
            val syncedColors = unsyncedColors.map { it.copy(isSynced = true) }
            colorDao.updateColors(syncedColors)
        }
    }
}