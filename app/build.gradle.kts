plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.dagger.hilt.plugin)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.serialization)
    id("com.google.gms.google-services")

}

android {
    namespace = "com.example.rayna"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.rayna"
        minSdk = 24
        targetSdk = 35
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
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.io.coil.kt.compose)

    // Dagger Hilt
    implementation(libs.dagger.hilt)
    kapt(libs.dagger.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    // Navigation compose
    implementation(libs.androidx.navigation.compose)

    // ✅ Firebase dependencies
    implementation(platform(libs.google.firebase.bom)) // BOM للتحكم بالإصدارات
    implementation(libs.google.firebase.analytics.ktx)
    implementation(libs.google.firebase.auth.ktx)
    implementation(libs.google.firebase.firestore.ktx)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
