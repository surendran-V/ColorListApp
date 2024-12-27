package com.digitaldomi.colorlistapp.data.remote

import com.digitaldomi.colorlistapp.data.model.ColorEntry
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class FirebaseService {
    private val database = FirebaseDatabase.getInstance()
    private val colorsRef = database.getReference("colors")

    suspend fun syncColors(colors: List<ColorEntry>): Boolean {
        return try {
            colors.forEach { color ->
                colorsRef.child(color.id.toString()).setValue(
                    mapOf(
                        "color" to color.color,
                        "timestamp" to color.timestamp
                    )
                ).await()
            }
            true
        } catch (e: Exception) {
            false
        }
    }
}