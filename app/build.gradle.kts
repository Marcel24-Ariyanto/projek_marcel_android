plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.projek_marcel"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.projek_marcel"
        minSdk = 29
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
//    tambah binding fitur
    buildFeatures{
        viewBinding = true
    }


//
}

dependencies {
    implementation("com.github.bumptech.glide:glide:4.16.0") //tambahin untuk listview
    implementation("com.tbuonomo:dotsindicator:5.1.0") // menerapkan DotsIndicator
    implementation("androidx.gridlayout:gridlayout:1.0.0") // menerapkan GridLayout
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}