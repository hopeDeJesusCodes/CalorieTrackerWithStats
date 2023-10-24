plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("kotlin-android")
}

android {
    namespace = "com.example.caloriesCount"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.caloriesCount"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures{
        viewBinding =  true
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
}
val room_version = "2.4.2"
dependencies {
    implementation("androidx.room:room-runtime:2.4.0")
    implementation("androidx.lifecycle:lifecycle-livedata:2.x.x")
    implementation("androidx.room:room-runtime:2.4.2")
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    annotationProcessor("androidx.room:room-compiler:2.4.2")
    implementation("androidx.fragment:fragment-ktx:1.5.0")
    implementation("androidx.room:room-ktx:2.4.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0") // Use the appropriate version
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}