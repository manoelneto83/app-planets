// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    kotlin("plugin.serialization") version "2.0.20"
    id("com.google.dagger.hilt.android") version "2.47" apply false
    id("androidx.navigation.safeargs.kotlin") version "2.7.0" apply false
}