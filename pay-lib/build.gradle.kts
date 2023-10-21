plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("kotlinx-serialization")
}

android {
    namespace = "io.choizzy.pay_lib"
    compileSdk = 33

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.3.1")

    implementation("androidx.compose.foundation:foundation:1.2.0")
    implementation("io.coil-kt:coil-compose:1.4.0")
    implementation("androidx.compose.material:material:1.2.0")

    val lifecycleViewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1"
    implementation("androidx.activity:activity-ktx:1.3.1")

    implementation(lifecycleViewmodel)

    val daggerCompiler = "com.google.dagger:dagger-compiler:2.46"
    val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:2.46"
    val dagger = "com.google.dagger:dagger:2.46"
    val daggerAndroid = "com.google.dagger:dagger-android:2.46"
    val daggerAndroidSupport = "com.google.dagger:dagger-android-support:2.46"

    kapt(daggerCompiler)
    kapt(daggerAndroidProcessor)
    implementation(dagger)
    implementation(daggerAndroid)
    implementation(daggerAndroidSupport)
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")

    val moshi = "com.squareup.moshi:moshi:1.13.0"
    val moshiKotlin = "com.squareup.moshi:moshi-kotlin:1.13.0"
    val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:1.13.0"
    val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:2.9.0"

    implementation(moshi)
    implementation(moshiKotlin)
    implementation(retrofitMoshi)
    kapt(moshiCodegen)

    implementation("com.valentinilk.shimmer:compose-shimmer:1.0.5")

}