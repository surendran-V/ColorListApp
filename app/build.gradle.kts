plugins {
    //alias(libs.plugins.android.application)
    //alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services") // For Firebase
    //id("kotlin-kapt") // For Room
    id("com.google.devtools.ksp") version "2.0.21-1.0.27"
}

android {
    namespace = "com.digitaldomi.colorlistapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.digitaldomi.colorlistapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11


    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit.v115)
    androidTestImplementation(libs.androidx.espresso.core.v351)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.activity.ktx)
    val composeBom = platform("androidx.compose:compose-bom:2024.12.01.12.01")

    implementation(platform(libs.androidx.compose.bom.v20241201))
    implementation(libs.ui)
    implementation(libs.material3)
    implementation(libs.ui.tooling.preview)

    // ViewModel
    implementation(libs.lifecycle.viewmodel.compose)

    // Room
    val roomVersion = "2.6.1"
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    annotationProcessor(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.database.ktx)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.play.services)
}