plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {

    signingConfigs {
        create("ezcook_config") {
            storeFile = file("C:\\Users\\ASUS\\ezcook_keystore.jks")
            storePassword = "21115053120"
            keyPassword = "21115053120"
            keyAlias = "ezcook"
        }
    }
    namespace = "com.example.ezcook"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.ezcook"
        minSdk = 29
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("ezcook_config")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildToolsVersion = "34.0.0"
}

dependencies {
    implementation ("com.google.android.gms:play-services-auth:20.1.0")


    implementation("com.squareup.picasso:picasso:2.8")
    implementation("com.android.volley:volley:1.2.1")

    implementation(platform("com.google.firebase:firebase-bom:32.4.0"))
    implementation("com.google.firebase:firebase-auth")

    //có thể add hoặc không
//    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-messaging")
    implementation ("com.google.firebase:firebase-database:20.3.0")


    implementation ("androidx.viewpager2:viewpager2:1.0.0")

    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation ("de.hdodenhof:circleimageview:3.1.0")
//    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}