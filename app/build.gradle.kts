plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("kotlinx-serialization")
}

android {
    namespace = "io.choizzy.pay"
    compileSdk = 33

    defaultConfig {
        applicationId = "io.choizzy.pay"
        minSdk = 24
        targetSdk = 33
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
        kotlinCompilerExtensionVersion = "1.2.0"
    }
}

dependencies {
    implementation(project(":pay-lib"))

    val daggerCompiler = "com.google.dagger:dagger-compiler:2.46"
    val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:2.46"
    val dagger = "com.google.dagger:dagger:2.46"
    val daggerAndroid = "com.google.dagger:dagger-android:2.46"
    val daggerAndroidSupport = "com.google.dagger:dagger-android-support:2.46"

    implementation("androidx.compose.foundation:foundation:1.2.0")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.3.1")


    implementation(dagger)
    implementation(daggerAndroid)
    implementation(daggerAndroidSupport)

    kapt(daggerCompiler)
    kapt(daggerAndroidProcessor)

    val moshi = "com.squareup.moshi:moshi:1.13.0"
    val moshiKotlin = "com.squareup.moshi:moshi-kotlin:1.13.0"
    val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:1.13.0"
    val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:2.9.0"

    implementation(moshi)
    implementation(moshiKotlin)
    implementation(retrofitMoshi)
    kapt(moshiCodegen)
}