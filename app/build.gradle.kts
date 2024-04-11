import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.zhalz.guthib"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.zhalz.guthib"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())

        buildConfigField("String", "GITHUB_API_KEY", properties.getProperty("GITHUB_API_KEY"))
        buildConfigField("String", "BASE_URL", properties.getProperty("BASE_URL"))
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
        dataBinding = true
        buildConfig = true
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.activity:activity-ktx:1.8.2")
    implementation("androidx.activity:activity:1.8.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("androidx.fragment:fragment-ktx:1.6.2")

    /*  ==  GLIDE  ==  */
    implementation("com.github.bumptech.glide:glide:4.16.0")

    /*  ==  RETROFIT  ==  */
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    /*  ==  VIEW PAGER II  ==  */
    implementation("androidx.viewpager2:viewpager2:1.0.0")

    /*  ==  LOTTIE  ==  */
    implementation ("com.airbnb.android:lottie:6.3.0")

    /*  ==  DATA STORE  ==  */
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    /*  ==  HILT  ==  */
    implementation("com.google.dagger:hilt-android:2.48")
    kapt ("com.google.dagger:hilt-compiler:2.48")

    /*  ==  LIVE DATA  ==  */
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    /*  ==  ROOM DATABASE  ==  */
    //noinspection KaptUsageInsteadOfKsp
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

}