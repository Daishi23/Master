plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    namespace = "chu.studio"
    compileSdk = 33

    defaultConfig {
        applicationId = "chu.studio"
        minSdk = 26 // Изменено на 26 для поддержки Apache POI
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }

        // Добавлено для Apache POI
        multiDexEnabled = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        compose = true
        
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "11"
}

dependencies {
    implementation(platform("androidx.compose:compose-bom:2022.10.00"))

    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.compose.ui:ui-graphics")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.activity:activity-compose:1.5.1")
    debugImplementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.ui:ui-tooling-preview")

    implementation("com.yandex.android:mobileads:7.0.0")

    implementation("org.apache.poi:poi-ooxml:5.2.3")
    implementation("androidx.annotation:annotation:1.5.0") // Добавлено для Apache POI
    implementation("androidx.multidex:multidex:2.0.1") // Добавлено для Apache POI

    implementation("androidx.navigation:navigation-compose:2.5.3")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
   
    

    // Room
    implementation("androidx.room:room-runtime:2.5.1")
    kapt("androidx.room:room-compiler:2.5.1")
    implementation("androidx.room:room-ktx:2.5.1")
    implementation("androidx.sqlite:sqlite:2.1.0")

    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    
    
    
    //
    

       implementation ("com.google.dagger:hilt-android:2.44")
       kapt ("com.google.dagger:hilt-android-compiler:2.44")
       implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")
   

}
