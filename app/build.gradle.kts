plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {

    namespace = "com.example.madhusiri"

    compileSdk = 34

    defaultConfig {

        applicationId = "com.example.madhusiri"

        minSdk = 21
        targetSdk = 34

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
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

        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    // ✅ CORE
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")

    // ✅ UI
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // ✅ FIREBASE
    implementation("com.google.firebase:firebase-database:20.3.0")

    // ✅ GOOGLE MAPS
    implementation("com.google.android.gms:play-services-maps:18.2.0")

    // ✅ LOCATION SERVICES
    implementation("com.google.android.gms:play-services-location:21.0.1")

    // ✅ NAVIGATION
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
}