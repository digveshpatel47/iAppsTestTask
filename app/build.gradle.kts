plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    //alias(libs.plugins.devtools.ksp)
}

android {
    namespace = "com.iapps"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.iapps"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    // ---------------------

    // koin
    implementation (libs.koin.core)
    implementation (libs.koin.android)
    implementation (libs.koin.androidx.viewmodel)
    testImplementation (libs.koin.test)
    // ---------------------

    // room
    implementation (libs.androidx.room.runtime)
    implementation (libs.androidx.room.coroutines)
    implementation (libs.androidx.room.rxjava2)
    annotationProcessor (libs.androidx.room.compiler)
    // ---------------------

    // retrofit okhttp
    implementation (libs.retrofit2.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.okhttp)
    implementation (libs.logging.interceptor)
    implementation (libs.retrofit2.kotlin.coroutines.adapter)
    implementation (libs.gson)
    // ---------------------

    // testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // ---------------------
}