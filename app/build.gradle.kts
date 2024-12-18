plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-android")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")


}

android {
    namespace = "com.dicoding.mybottomnavtest"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.dicoding.mybottomnavtest"
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

    implementation (libs.gson)
    implementation (libs.glide)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.annotation)
    implementation(libs.play.services.maps)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.activity)
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.logging.interceptor)


            //ROOM LIBRARY
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.runtime.v250)
    annotationProcessor (libs.androidx.room.compiler)
    ksp(libs.androidx.room.compiler)

    //RECYCLEVIEW
    implementation (libs.androidx.recyclerview)

    //VIEWPAGER
    implementation (libs.androidx.viewpager2)

    //DATASTORE
    implementation(libs.androidx.datastore.preferences) // For Preferences DataStore
    implementation(libs.androidx.datastore.core) // For Proto DataStore

    //CAMERAX
    // Core library for CameraX
    implementation (libs.androidx.camera.core)

    // CameraX Camera2 interop library
    implementation (libs.androidx.camera.camera2)

    // CameraX Lifecycle library
    implementation (libs.androidx.camera.lifecycle)

    // CameraX View library for PreviewView
    implementation (libs.androidx.camera.view)

    // CameraX Extensions (Optional: for HDR, Night Mode, etc.)
    implementation (libs.camera.extensions)

    // Optional - for image analysis (if needed)
    implementation (libs.androidx.camera.mlkit.vision)

    

}