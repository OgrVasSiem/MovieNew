
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.googleServices) apply false
    alias(libs.plugins.daggerHilt) apply false
    alias(libs.plugins.parcelize) apply false
    alias(libs.plugins.kotlin.compose.compiler) apply false
}