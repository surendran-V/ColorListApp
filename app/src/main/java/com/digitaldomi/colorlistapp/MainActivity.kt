package com.digitaldomi.colorlistapp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.digitaldomi.colorlistapp.data.local.ColorDatabase
import com.digitaldomi.colorlistapp.data.remote.FirebaseService
import com.digitaldomi.colorlistapp.ui.screens.ColorListScreen
import com.digitaldomi.colorlistapp.ui.theme.ColorListAppTheme
import com.digitaldomi.colorlistapp.viewmodel.ColorViewModel
import com.digitaldomi.colorlistapp.data.repository.ColorRepository
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
class MainActivity : ComponentActivity() {
    private lateinit var viewModel: ColorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewModel
        val database = ColorDatabase.getDatabase(applicationContext)
        val firebaseService = FirebaseService()
        val repository = ColorRepository(database.colorDao(), firebaseService)
        viewModel = ColorViewModel(repository)

        setContent {
            ColorListAppTheme(dynamicColor = false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background)
                {
                    val viewModel: ColorViewModel = viewModel
                    ColorListScreen(viewModel)
                }
            }
        }
    }
}