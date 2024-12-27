package com.digitaldomi.colorlistapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitaldomi.colorlistapp.data.model.ColorEntry
import com.digitaldomi.colorlistapp.data.repository.ColorRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ColorViewModel(private val repository: ColorRepository) : ViewModel() {
    val colors: StateFlow<List<ColorEntry>> = repository.allColors
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val unsyncedCount: StateFlow<Int> = repository.unsyncedCount
        .stateIn(viewModelScope, SharingStarted.Lazily, 0)

    fun addColor() {
        viewModelScope.launch {
            repository.addColor()
        }
    }

    fun syncColors() {
        viewModelScope.launch {
            repository.syncColors()
        }
    }

    fun formatTimestamp(timestamp: Long): String {
        val date = Date(timestamp)
        val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        return formatter.format(date)
    }
}