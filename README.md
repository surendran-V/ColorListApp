# Color List App

A modern Android application built with Jetpack Compose that manages and synchronizes color data between local storage and cloud database. The app demonstrates offline-first architecture, real-time synchronization, and modern Android development practices.

## Features

🎨 **Color Management**
- Display list of colors with timestamps
- Generate and add random colors
- Offline support with local storage
- Real-time sync with cloud database

🏗️ **Technical Features**
- MVVM Architecture
- Jetpack Compose UI
- Room Database for local storage
- Firebase Realtime Database integration
- Offline-first approach
- Material 3 Design

## Tech Stack

- **Language:** Kotlin
- **UI Framework:** Jetpack Compose
- **Architecture:** MVVM
- **Local Database:** Room
- **Cloud Database:** Firebase Realtime Database
- **Dependency Injection:** Manual DI
- **Concurrency:** Kotlin Coroutines & Flow

## Project Structure

```
com.example.colorlistapp/
├── data/
│   ├── local/
│   │   ├── ColorDao.kt
│   │   └── ColorDatabase.kt
│   ├── remote/
│   │   └── FirebaseService.kt
│   ├── model/
│   │   └── ColorEntry.kt
│   └── repository/
│       └── ColorRepository.kt
├── ui/
│   ├── theme/
│   │   ├── Color.kt
│   │   ├── Theme.kt
│   │   └── Type.kt
│   └── screens/
│       └── ColorListScreen.kt
├── viewmodel/
│   └── ColorViewModel.kt
└── MainActivity.kt
```

## Setup Instructions

### Prerequisites
- Android Studio Arctic Fox or later
- Minimum SDK: API 24 (Android 7.0)
- Firebase Account
- Git

### Firebase Setup
1. Go to [Firebase Console](https://console.firebase.google.com/)
2. Create a new project
3. Add an Android app with package name `com.example.colorlistapp`
4. Download `google-services.json` and place it in the app/ directory
5. Enable Realtime Database in Firebase Console

### Local Setup
1. Clone the repository:
```bash
git clone https://github.com/surendran-V/ColorListApp
```

2. Open project in Android Studio

3. Sync project with Gradle files

4. Run the app on an emulator or physical device

### Running on Physical Device
1. Enable Developer Options:
   - Go to Settings > About phone
   - Tap Build number 7 times
   - Developer options will appear in Settings

2. Enable USB Debugging:
   - Go to Settings > Developer options
   - Enable USB debugging

3. Connect device via USB and authorize debugging

4. Run the app from Android Studio

## Features Explanation

### Color List
- Displays colors in a scrollable list
- Each color item shows:
  - Color preview
  - Hex code
  - Timestamp
- Colors are stored locally using Room Database

### Sync Functionality
- Sync button in top-right corner
- Badge shows number of unsynced items
- Automatically syncs when online
- Stores data locally when offline

### Add Color
- Generates random color
- Stores locally first
- Queues for sync to cloud
- Updates UI immediately

## Architecture Components

### Data Layer
- **Room Database:** Handles local storage
- **Firebase:** Manages cloud sync
- **Repository:** Coordinates data operations

### UI Layer
- **Jetpack Compose:** Builds modern UI
- **Material 3:** Follows design guidelines
- **ViewModel:** Manages UI state

### Business Logic
- **Use Cases:** Handle business rules
- **Repository Pattern:** Manages data sources
- **MVVM Architecture:** Separates concerns
