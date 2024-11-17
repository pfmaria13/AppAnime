plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.appanime"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.appanime"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Jetpack Compose
    implementation (libs.androidx.ui.v175)
    implementation (libs.androidx.material.v175)
    implementation (libs.androidx.navigation.compose)

    // Зависимость для Material Icons
    implementation (libs.androidx.material.icons.core) // Основные иконки
    implementation (libs.androidx.material.icons.extended) // Расширенные иконки

    // Retrofit for network requests
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation(libs.okhttp.v493)
    implementation(libs.logging.interceptor.v493)

    // Lifecycle components
    implementation (libs.androidx.lifecycle.runtime.ktx.v287)
    implementation (libs.androidx.lifecycle.viewmodel.compose.v287)

    // Kotlin Coroutines
    implementation (libs.kotlinx.coroutines.core.v164)
    implementation (libs.kotlinx.coroutines.android)
    implementation(libs.androidx.material3.android)

    // Testing dependencies
    testImplementation (libs.junit)
    androidTestImplementation (libs.androidx.junit.v115)
    androidTestImplementation (libs.androidx.espresso.core.v351)
    implementation(libs.coil)
    implementation(libs.coil.kt.coil.compose)
    implementation(libs.glide)
    debugImplementation(libs.library)
}