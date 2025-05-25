// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.compose.compiler) apply false
}
val localProperties = java.util.Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localProperties.load(localPropertiesFile.inputStream())
    val apiKey = localProperties["TMDB_API_KEY"]?.toString()
        ?: throw GradleException("TMDB_API_KEY is missing in local.properties")
    extra["TMDB_API_KEY"] = apiKey
} else {
    throw GradleException("local.properties file is missing")
}
