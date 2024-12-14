plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    kotlin("kapt") // Kapt Plugin für Kotlin Annotation Processing hinzufügen
}

android {
    namespace = "de.finsandcode.tauchtrainer"
    compileSdk = 35

    defaultConfig {
        applicationId = "de.finsandcode.tauchtrainer"
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx.v1150)
    implementation(libs.androidx.appcompat.v170)
    implementation(libs.material.v190)
    implementation(libs.androidx.constraintlayout.v220)
    implementation(libs.androidx.lifecycle.livedata.ktx.v287)
    implementation(libs.androidx.lifecycle.viewmodel.ktx.v287)
    implementation(libs.androidx.navigation.fragment.ktx.v285)
    implementation(libs.androidx.navigation.ui.ktx.v285)

    // Room Abhängigkeiten
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)

    // Optional: Wenn du LiveData oder Flow verwendest
    implementation(libs.androidx.room.ktx)

    // Test und Android Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit.v121)
}
