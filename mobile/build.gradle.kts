plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("org.jetbrains.kotlin.kapt")
    id("com.google.dagger.hilt.android")
}

repositories {
    maven { url = uri("https://jitpack.io") }
    mavenCentral()
}

android {
    compileSdk = 36
    namespace = "com.siliconlabs.bledemo"

    defaultConfig {
        minSdk = 30
        targetSdk = 36

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    packaging {
        resources {
            excludes += listOf("META-INF/INDEX.LIST", "META-INF/io.netty.versions.properties")

            jniLibs { useLegacyPackaging = true }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("debug")
        }
        debug {
            isDebuggable = true
        }
    }

    lint {
        checkReleaseBuilds = false
        // Or, if you prefer, you can continue to check for errors in release builds,
        // but continue the build even when errors are found:
        abortOnError = false
    }

    val versionDim = "version"
    flavorDimensions.add(versionDim)

    productFlavors {
        create("Si-Connect") {
            dimension = versionDim
            applicationId = "com.siliconlabs.bledemo"
            versionCode = 74
            versionName = "3.2.0"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    kapt {
        correctErrorTypes = true
    }

    sourceSets {
        getByName("main") {
            jniLibs.srcDir("libs")
        }
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    applicationVariants.all {
        outputs.all {
            val apkName = "mobile-${name}-${versionName}.apk"
            (this as? com.android.build.gradle.internal.api.BaseVariantOutputImpl)?.outputFileName = apkName
        }
    }

}

dependencies {
    implementation(fileTree("libs") {
        include("*.jar", "*.so")
    })

    // androidx
    implementation("androidx.core:core-ktx:1.17.0")
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("androidx.fragment:fragment:1.8.9")
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.9.3")
    implementation("androidx.activity:activity-ktx:1.10.1")
    implementation("com.google.android.material:material:1.12.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")

    // UI components
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.9.3")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.9.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.9.3")
    implementation("androidx.navigation:navigation-dynamic-features-fragment:2.9.3")

    // Dependency injection
    implementation("com.google.dagger:hilt-android:2.57.1")
    kapt("com.google.dagger:hilt-android-compiler:2.57.1")

    // View binding
    implementation("com.github.kirich1409:viewbindingpropertydelegate-noreflection:1.5.9")

    // Logging
    implementation("com.jakewharton.timber:timber:5.0.1")

    // Parsing
    implementation("com.google.code.gson:gson:2.13.1")
    implementation("androidx.activity:activity:1.10.1")

    // Only used for Int.pow() method in a couple of places
    implementation("com.google.guava:guava:33.4.8-android")

    // Toasts
    implementation("com.pranavpandey.android:dynamic-toasts:4.3.0")

    // instrumented tests
    testImplementation("junit:junit:4.13.2")
    androidTestUtil("androidx.test:orchestrator:1.6.1")
    androidTestImplementation("androidx.test:runner:1.7.0")
    androidTestImplementation("androidx.test:rules:1.7.0")
    androidTestImplementation("androidx.test.ext:junit:1.3.0")
}
