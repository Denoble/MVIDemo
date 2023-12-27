plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.gevcorst.mvidemo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.gevcorst.mvidemo"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        val newsAPIKey =
            com.android.build.gradle.internal.cxx.configure.gradleLocalProperties(rootDir).getProperty("NEWSAPI")
        buildConfigField("String", "NEWS_API_KEY", "$newsAPIKey")
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
    buildFeatures{
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")

    //lifecycle
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0-rc02")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0-rc02")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0-rc02")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0-rc02")
    implementation("androidx.activity:activity-ktx:1.9.0-alpha01")
    //coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-bom:1.8.0-RC2")
    //okhttp
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.12")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.12")

    //moshi
    implementation("com.squareup.moshi:moshi:1.15.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("com.squareup.moshi:moshi:1.15.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.google.code.gson:gson:2.10.1")
    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")



    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}