package com.digitaldomi.colorlistapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh // Using Refresh instead of Sync
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.digitaldomi.colorlistapp.data.model.ColorEntry // Make sure this import is present
import com.digitaldomi.colorlistapp.viewmodel.ColorViewModel
import android.graphics.Color as AndroidColor // Alias to avoid name clash

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorListScreen(viewModel: ColorViewModel) {
    val colors by viewModel.colors.collectAsState()
    val unsyncedCount by viewModel.unsyncedCount.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar( // Use CenterAlignedTopAppBar or TopAppBar
                title = { Text("Colors") },
                actions = {
                    IconButton(onClick = { viewModel.syncColors() }) {
                        BadgedBox(
                            badge = {
                                if (unsyncedCount > 0) {
                                    Badge { Text(text = unsyncedCount.toString()) } // Correct Badge usage
                                }
                            }
                        ) {
                            Icon(Icons.Filled.Refresh, contentDescription = "Sync colors")
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            Button(
                onClick = { viewModel.addColor() },
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    "Add Color",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(colors) { colorEntry ->
                ColorItem(colorEntry = colorEntry, viewModel = viewModel)
            }
        }
    }
}

@Composable
fun ColorItem(
    colorEntry: ColorEntry,
    viewModel: ColorViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .background(
                Color.White,
                RoundedCornerShape(8.dp)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Color square
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    try{
                        Color(AndroidColor.parseColor(colorEntry.color))}
                    catch (e:IllegalArgumentException){
                        Color.Gray
                    }, // Using Color.parse
                    RoundedCornerShape(4.dp)
                )
        )

        // Timestamp
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f)
        ) {
            Text(
                colorEntry.color,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                viewModel.formatTimestamp(colorEntry.timestamp),
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}