package com.digitaldomi.colorlistapp

import android.app.Application
import com.google.firebase.FirebaseApp

class ColorApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}