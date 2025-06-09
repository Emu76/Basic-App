plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.hilt)
}

android {
    compileSdk = 35
    namespace = "com.peteremo.basicapp"
}

dependencies {

    implementation(libs.hilt.android)
    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
    ksp(libs.hilt.compiler)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}